package com.Prevenir.DTO.AdministrativoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAdministrativoDTO {

    @NotBlank
    @Size(min = 4, max = 20)
    private String usuario;

    @NotBlank
    @Size(min = 9, max = 20)
    private String password;

}
