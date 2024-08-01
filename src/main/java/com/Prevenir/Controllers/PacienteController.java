package com.Prevenir.Controllers;

import com.Prevenir.DTO.RegistroDTO.RegistroPacienteDTO;
import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Paciente;
import com.Prevenir.Service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Paciente>>> list() {
        try {
            ApiResponse<List<Paciente>> response = new ApiResponse<>(null, HttpStatus.OK.value(), pacienteService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Paciente> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Paciente>> get(@PathVariable Long id) {
        try {
            ApiResponse<Paciente> response = new ApiResponse<>(null, HttpStatus.OK.value(), pacienteService.getById(id).orElseThrow(() -> new BadRequestException("No encontrado")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Paciente> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Paciente>> post(@Valid @RequestBody RegistroPacienteDTO registroDTO) {
        try {
            ApiResponse<Paciente> response = new ApiResponse<>(null, HttpStatus.OK.value(), pacienteService.createPaciente(registroDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Paciente> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
