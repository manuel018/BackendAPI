package com.Prevenir.Controllers;

import com.Prevenir.DTO.RegistroDTO.RegistroAdministrativoDTO;
import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Administrativo;
import com.Prevenir.Service.AdministrativoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/administrativo")
public class AdministrativoController {

    private final AdministrativoService adminService;

    public AdministrativoController(AdministrativoService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Administrativo>>> list() {
        try {
            ApiResponse<List<Administrativo>> response = new ApiResponse<>(null, HttpStatus.OK.value(), adminService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Administrativo> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Administrativo>> get(@PathVariable Long id) {
        try {
            ApiResponse<Administrativo> response = new ApiResponse<>(null, HttpStatus.OK.value(), adminService.getById(id).orElseThrow(() -> new BadRequestException("No encontrado")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Administrativo> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<Administrativo>> getUsername(@PathVariable String username) {
        try {
            ApiResponse<Administrativo> response = new ApiResponse<>(null, HttpStatus.OK.value(), adminService.getByUsername(username).orElseThrow(() -> new BadRequestException("No encontrado")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Administrativo> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Administrativo>> post(@Valid @RequestBody RegistroAdministrativoDTO registroDTO) {
        try {
            ApiResponse<Administrativo> response = new ApiResponse<>(null, HttpStatus.OK.value(), adminService.createAdministrativo(registroDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Administrativo> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")//Cambio de estado
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
