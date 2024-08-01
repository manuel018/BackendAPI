package com.Prevenir.Controllers;

import com.Prevenir.DTO.RegistroDTO.RegistroMedicoDTO;
import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Medico;
import com.Prevenir.Service.MedicoService;
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
@RequestMapping("/api/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Medico>>> list() {
        try {
            ApiResponse<List<Medico>> response = new ApiResponse<>(null, HttpStatus.OK.value(), medicoService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Medico> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Medico>> get(@PathVariable Long id) {
        try {
            ApiResponse<Medico> response = new ApiResponse<>(null, HttpStatus.OK.value(), medicoService.getById(id).orElseThrow(() -> new BadRequestException("No encontrado")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Medico> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Medico>> post(@Valid @RequestBody RegistroMedicoDTO registroDTO) {
        try {
            ApiResponse<Medico> response = new ApiResponse<>(null, HttpStatus.OK.value(), medicoService.createMedico(registroDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Medico> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
