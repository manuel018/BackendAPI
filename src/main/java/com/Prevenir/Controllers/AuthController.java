package com.Prevenir.Controllers;

import com.Prevenir.DTO.AuthDTO.AuthLoginRequest;
import com.Prevenir.DTO.AuthDTO.AuthResponse;
import com.Prevenir.DTO.RepsonseApi.ApiResponse;
import com.Prevenir.Service.AdministrativoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdministrativoService adminService;

    public AuthController(AdministrativoService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthLoginRequest authRequest) {
        try {
            ApiResponse<AuthResponse> response = new ApiResponse<>(null, HttpStatus.OK.value(), this.adminService.loginUser(authRequest));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<AuthResponse> response = new ApiResponse<>(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
