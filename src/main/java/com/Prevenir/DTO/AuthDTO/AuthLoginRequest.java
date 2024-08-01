package com.Prevenir.DTO.AuthDTO;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank
        String username, @NotBlank
        String password) {

}
