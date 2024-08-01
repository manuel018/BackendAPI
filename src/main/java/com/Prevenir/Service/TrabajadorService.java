package com.Prevenir.Service;

import com.Prevenir.Entity.Trabajador;
import com.Prevenir.Repository.TrabajadorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepo;

    public TrabajadorService(TrabajadorRepository trabajadorRepo) {
        this.trabajadorRepo = trabajadorRepo;
    }

    public List<Trabajador> getAll() {
        return trabajadorRepo.findAll();
    }

}
