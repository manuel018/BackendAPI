package com.Prevenir.Service;

import com.Prevenir.DTO.AdministrativoDTO.CreateAdministrativoDTO;
import com.Prevenir.DTO.AuthDTO.AuthLoginRequest;
import com.Prevenir.DTO.AuthDTO.AuthResponse;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import com.Prevenir.DTO.RegistroDTO.RegistroAdministrativoDTO;
import com.Prevenir.DTO.Trabajador.CreateTrabajadorDTO;
import com.Prevenir.Entity.Administrativo;
import com.Prevenir.Entity.Estado;
import com.Prevenir.Entity.Persona;
import com.Prevenir.Entity.Rol;
import com.Prevenir.Entity.Trabajador;
import com.Prevenir.Repository.AdministrativoRepository;
import com.Prevenir.Utils.JwtUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdministrativoService implements UserDetailsService {

    private final AdministrativoRepository administrativoRepo;
    private final RolService rolService;
    private final EstadoService estadoService;
    private final PacienteService pacienteService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AdministrativoService(AdministrativoRepository administrativoRepo, RolService rolService, EstadoService estadoService, PacienteService pacienteService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.administrativoRepo = administrativoRepo;
        this.rolService = rolService;
        this.estadoService = estadoService;
        this.pacienteService = pacienteService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public Administrativo createAdministrativo(RegistroAdministrativoDTO adminDTO) {
        //Con las entidades configuradas con cascade.ALL hare 1 insert y creara en administrativo y tanto en trabajador como persona
        Persona p = personaDTOtoEntity(adminDTO.getPersona());
        Trabajador t = trabajadorDTOtoEntity(adminDTO.getTrabajador());
        t.setPersona(p);
        Administrativo a = adminDTOtoEntity(adminDTO.getAdmin());
        a.setTrabajador(t);
        pacienteService.createPacienteTrabajador(p);
        administrativoRepo.save(a);
        return a;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Se recupera el usuario con el metodo que se implementa de la interfaz
        Administrativo admin = administrativoRepo.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario en la base de datos!"));
        //Se setean roles y permisos en un objeto GrantedAuthority debe ser una lista del mismo
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_".concat(admin.getTrabajador().getRol().getNombre()));
        return new User(admin.getUsuario(), admin.getPassword(), List.of(authority));
    }

    public List<Administrativo> getAll() {
        return administrativoRepo.findAll();
    }

    public Optional<Administrativo> getByUsername(String username) {
        return administrativoRepo.findByUsuario(username);
    }

    public Optional<Administrativo> getById(Long id) {
        return administrativoRepo.findById(id);
    }

    //Método del login, se recibe un objeto que es un DTO con los 2 parametors user y pass
    public AuthResponse loginUser(AuthLoginRequest authRequest) {
        String username = authRequest.username();
        String pass = authRequest.password();

        //Autenticacion del usuario con spring y bd
        Authentication authentication = authenticate(username, pass);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, pass, accessToken, true);

        return authResponse;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetail = this.loadUserByUsername(username);

        if (userDetail == null) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        if (!passwordEncoder.matches(password, userDetail.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
    }

    private Administrativo adminDTOtoEntity(CreateAdministrativoDTO adminDTO) {
        //Pass se debe encryptar para almacenarse en la BD
        String password = passwordEncoder.encode(adminDTO.getPassword());
        //Devolvemos objeto Administrativo
        return Administrativo.builder().usuario(adminDTO.getUsuario()).password(password).build();
    }

    private Trabajador trabajadorDTOtoEntity(CreateTrabajadorDTO trabajadorDTO) {
        //Rol recuperado con el id del DTO
        Rol rol = rolService.getRolbyId(trabajadorDTO.getIdRol()).orElseThrow();
        //Devolvemos objeto Trabajador
        return Trabajador.builder().rol(rol).build();
    }

    private Persona personaDTOtoEntity(CreatePersonaDTO personaDTO) {
        //Por defecto estado Activo al crearse
        Estado estado = estadoService.getEstadoActivo().orElseThrow();
        //Se instancia con builder y los atributos del DTO
        return Persona.builder().apellidoPaterno(personaDTO.getApellidoPaterno()).apellidoMaterno(personaDTO.getApellidoMaterno()).correo(personaDTO.getCorreo()).dni(personaDTO.getDni()).estado(estado).fechaNacimiento(personaDTO.getFechaNacimiento()).foto(personaDTO.getFoto()).telefono(personaDTO.getTelefono()).nombrePrimer(personaDTO.getNombrePrimer()).nombreSegundo(personaDTO.getNombreSegundo()).sexo(personaDTO.getSexo()).build();
    }
}
