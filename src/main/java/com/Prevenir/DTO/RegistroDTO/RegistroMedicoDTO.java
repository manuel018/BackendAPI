package com.Prevenir.DTO.RegistroDTO;

import com.Prevenir.DTO.MedicoDTO.CreateMedicoDTO;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import com.Prevenir.DTO.Trabajador.CreateTrabajadorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroMedicoDTO {

    @Valid
    @NotNull(message = "Los datos del medico son obligatorios")
    private CreateMedicoDTO medico;

    @Valid
    @NotNull(message = "Los datos del trabjador son obligatorios")
    private CreateTrabajadorDTO trabajador;

    @Valid
    @NotNull(message = "Los datos de la persona son obligatorios")
    private CreatePersonaDTO persona;

}
