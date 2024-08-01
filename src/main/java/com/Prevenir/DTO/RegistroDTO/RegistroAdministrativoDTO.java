package com.Prevenir.DTO.RegistroDTO;

import com.Prevenir.DTO.AdministrativoDTO.CreateAdministrativoDTO;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import com.Prevenir.DTO.Trabajador.CreateTrabajadorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroAdministrativoDTO {

    @Valid
    @NotNull(message = "Los datos del administrativo son obligatorios")
    private CreateAdministrativoDTO admin;

    @Valid
    @NotNull(message = "Los datos del trabjador son obligatorios")
    private CreateTrabajadorDTO trabajador;

    @Valid
    @NotNull(message = "Los datos de la persona son obligatorios")
    private CreatePersonaDTO persona;

}
