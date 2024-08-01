package com.Prevenir.Controllers;

import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Especialidad;
import com.Prevenir.Service.EspecialidadService;
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
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Especialidad>>> list() {
        try {
            ApiResponse<List<Especialidad>> response = new ApiResponse<>(null, HttpStatus.OK.value(), especialidadService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Especialidad> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Especialidad>> post(@RequestBody Especialidad especialidad) {
        try {
            ApiResponse<Especialidad> response = new ApiResponse<>(null, HttpStatus.OK.value(), especialidadService.createEspecialidad(especialidad));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Especialidad> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
