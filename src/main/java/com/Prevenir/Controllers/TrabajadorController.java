package com.Prevenir.Controllers;

import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Trabajador;
import com.Prevenir.Service.TrabajadorService;
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

/**
 *
 * @author Manuel
 */
@RestController
@RequestMapping("/api/trabajador")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Trabajador>>> list() {
        try {
            ApiResponse<List<Trabajador>> response = new ApiResponse<>(null, HttpStatus.OK.value(), trabajadorService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Trabajador> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
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
    public ResponseEntity<?> post(@RequestBody Object input) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
