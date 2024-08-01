package com.Prevenir.Service;

import com.Prevenir.Entity.Especialidad;
import com.Prevenir.Repository.EspecialidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepo;

    public EspecialidadService(EspecialidadRepository especialidadRepo) {
        this.especialidadRepo = especialidadRepo;
    }

    public Especialidad createEspecialidad(Especialidad especialidad) {
        return especialidadRepo.save(especialidad);
    }

    public List<Especialidad> getAll() {
        return especialidadRepo.findAll();
    }

    public Optional<Especialidad> getById(Long id) {
        return especialidadRepo.findById(id);
    }
}
