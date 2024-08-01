package com.Prevenir.Service;

import com.Prevenir.DTO.CitaDTO.CreateCitaDTO;
import com.Prevenir.Entity.Cita;
import com.Prevenir.Entity.Medico;
import com.Prevenir.Entity.Paciente;
import com.Prevenir.Repository.CitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

    private final CitaRepository citaRepo;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public CitaService(CitaRepository citaRepo, MedicoService medicoService, PacienteService pacienteService) {
        this.citaRepo = citaRepo;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    public List<Cita> getAll() {
        return citaRepo.findAll();
    }

    public Optional<Cita> getById(Long id) {
        return citaRepo.findById(id);
    }

    public Cita createCita(CreateCitaDTO citaDTO) {
        Cita cita = citaDTOtoEntity(citaDTO);
        return citaRepo.save(cita);
    }

    private Cita citaDTOtoEntity(CreateCitaDTO citaDTO) {
        //Pass se debe encryptar para almacenarse en la BD
        Medico medico = medicoService.getById(citaDTO.getIdMedico()).orElseThrow();
        Paciente paciente = pacienteService.getById(citaDTO.getIdPaciente()).orElseThrow();
        //Devolvemos objeto Administrativo
        return Cita.builder().estado("Activa").fecha(citaDTO.getFecha()).medico(medico).paciente(paciente).observaciones(citaDTO.getObservaciones()).build();
    }

}
