package com.Prevenir.Service;

import com.Prevenir.Entity.Estado;
import com.Prevenir.Repository.EstadoRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepo;

    public EstadoService(EstadoRepository estadoRepo) {
        this.estadoRepo = estadoRepo;
    }

    public Estado createEstado(Estado estado) {
        return this.estadoRepo.save(estado);
    }

    public Optional<Estado> getEstadoActivo() {
        //id = 1 Estado.Activo
        return estadoRepo.findById(1L);
    }
}
