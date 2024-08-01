package com.Prevenir.Service;

import com.Prevenir.DTO.MedicoDTO.CreateMedicoDTO;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import com.Prevenir.DTO.RegistroDTO.RegistroMedicoDTO;
import com.Prevenir.DTO.Trabajador.CreateTrabajadorDTO;
import com.Prevenir.Entity.Especialidad;
import com.Prevenir.Entity.Estado;
import com.Prevenir.Entity.Medico;
import com.Prevenir.Entity.Persona;
import com.Prevenir.Entity.Rol;
import com.Prevenir.Entity.Trabajador;
import com.Prevenir.Repository.MedicoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepo;
    private final RolService rolService;
    private final EstadoService estadoService;
    private final EspecialidadService especialidadService;
    private final PacienteService pacienteService;

    public MedicoService(MedicoRepository medicoRepo, RolService rolService, EstadoService estadoService, EspecialidadService especialidadService, PacienteService pacienteService) {
        this.medicoRepo = medicoRepo;
        this.rolService = rolService;
        this.estadoService = estadoService;
        this.especialidadService = especialidadService;
        this.pacienteService = pacienteService;
    }

    @Transactional
    public Medico createMedico(RegistroMedicoDTO registroDTO) {
        Persona persona = personaDTOtoEntity(registroDTO.getPersona());
        Trabajador trabajador = trabajadorDTOtoEntity(registroDTO.getTrabajador());
        trabajador.setPersona(persona);
        Medico medico = medicoDTOtoEntity(registroDTO.getMedico());
        medico.setTrabajador(trabajador);
        pacienteService.createPacienteTrabajador(persona);
        return medicoRepo.save(medico);
    }

    public Optional<Medico> getById(Long id) {
        return medicoRepo.findById(id);
    }

    public List<Medico> getAll() {
        return medicoRepo.findAll();
    }

    private Medico medicoDTOtoEntity(CreateMedicoDTO medicoDTO) {
        //Seteamos especialidad
        Especialidad especialidad = especialidadService.getById(medicoDTO.getIdEspecialidad()).orElseThrow();
        return Medico.builder().especialidad(especialidad).build();
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
