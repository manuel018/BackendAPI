package com.Prevenir.Repository;

import com.Prevenir.Entity.Administrativo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

    public Optional<Administrativo> findByUsuario(String username);
}
