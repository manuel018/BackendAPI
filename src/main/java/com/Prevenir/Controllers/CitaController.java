package com.Prevenir.Controllers;

import com.Prevenir.DTO.CitaDTO.CreateCitaDTO;
import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Cita;
import com.Prevenir.Service.CitaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/cita")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Cita>>> list() {
        try {
            ApiResponse<List<Cita>> response = new ApiResponse<>(null, HttpStatus.OK.value(), citaService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Cita> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cita>> get(@PathVariable Long id) {
        try {
            ApiResponse<Cita> response = new ApiResponse<>(null, HttpStatus.OK.value(), citaService.getById(id).orElseThrow());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Cita> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cita>> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cita>> post(@Valid @RequestBody CreateCitaDTO citaDTO) {
        try {
            ApiResponse<Cita> response = new ApiResponse<>(null, HttpStatus.OK.value(), citaService.createCita(citaDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Cita> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return null;
    }

}
