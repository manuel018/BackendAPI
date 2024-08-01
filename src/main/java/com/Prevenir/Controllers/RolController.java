/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.Prevenir.Controllers;

import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Entity.Rol;
import com.Prevenir.Service.RolService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Manuel
 */
@RestController
@RequestMapping("/api/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Rol>>> list() {
        try {
            ApiResponse<List<Rol>> response = new ApiResponse<>(null, HttpStatus.OK.value(), rolService.getAll());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Rol> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
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
    public ResponseEntity<?> post(@RequestBody Rol rol) {
        try {
            ApiResponse<Rol> response = new ApiResponse<>(null, HttpStatus.OK.value(), rolService.createRol(rol));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Rol> response = new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
