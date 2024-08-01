package com.Prevenir.Service;

import com.Prevenir.DTO.PacienteDTO.CreatePacienteDTO;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import com.Prevenir.DTO.RegistroDTO.RegistroPacienteDTO;
import com.Prevenir.Entity.Estado;
import com.Prevenir.Entity.Paciente;
import com.Prevenir.Entity.Persona;
import com.Prevenir.Repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepo;
    private final EstadoService estadoService;

    public PacienteService(PacienteRepository pacienteRepo, EstadoService estadoService) {
        this.pacienteRepo = pacienteRepo;
        this.estadoService = estadoService;
    }

    @Transactional
    public Paciente createPaciente(RegistroPacienteDTO registroDTO) {
        Persona persona = personaDTOtoEntity(registroDTO.getPersona());
        Paciente paciente = pacienteDTOtoEntity(registroDTO.getPaciente());
        paciente.setPersona(persona);
        return pacienteRepo.save(paciente);
    }

    public void createPacienteTrabajador(Persona persona) {
        Paciente pacienteTrabajador = Paciente.builder().observaciones("").grupoSanguineo("No seleccionado").persona(persona).build();
        pacienteRepo.save(pacienteTrabajador);
    }

    public List<Paciente> getAll() {
        return pacienteRepo.findAll();
    }

    public Optional<Paciente> getById(Long id) {
        return pacienteRepo.findById(id);
    }

    private Persona personaDTOtoEntity(CreatePersonaDTO personaDTO) {
        //Por defecto estado Activo al crearse
        Estado estado = estadoService.getEstadoActivo().orElseThrow();
        //Se instancia con builder y los atributos del DTO
        return Persona.builder().apellidoPaterno(personaDTO.getApellidoPaterno()).apellidoMaterno(personaDTO.getApellidoMaterno()).correo(personaDTO.getCorreo()).dni(personaDTO.getDni()).estado(estado).fechaNacimiento(personaDTO.getFechaNacimiento()).foto(personaDTO.getFoto()).telefono(personaDTO.getTelefono()).nombrePrimer(personaDTO.getNombrePrimer()).nombreSegundo(personaDTO.getNombreSegundo()).sexo(personaDTO.getSexo()).build();
    }

    private Paciente pacienteDTOtoEntity(CreatePacienteDTO pacienteDTO) {
        //Se instancia con builder y los atributos del DTO
        return Paciente.builder().grupoSanguineo(pacienteDTO.getGrupoSanguineo()).observaciones(pacienteDTO.getObservaciones()).build();
    }
}
