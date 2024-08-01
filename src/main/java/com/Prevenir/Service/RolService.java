package com.Prevenir.Service;

import com.Prevenir.Entity.Rol;
import com.Prevenir.Repository.RolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private final RolRepository rolRepo;

    public RolService(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    public Rol createRol(Rol rol) {
        return rolRepo.save(rol);
    }

    public List<Rol> getAll() {
        return rolRepo.findAll();
    }

    public Optional<Rol> getRolbyId(Long id) {
        return rolRepo.findById(id);
    }
}
