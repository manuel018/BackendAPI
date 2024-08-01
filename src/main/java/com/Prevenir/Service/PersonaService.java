package com.Prevenir.Service;

import com.Prevenir.Repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepo;

    public PersonaService(PersonaRepository personaRepo) {
        this.personaRepo = personaRepo;
    }
}
