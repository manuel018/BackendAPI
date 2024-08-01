package com.Prevenir.DTO.PacienteDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePacienteDTO {

    @NotNull
    private String grupoSanguineo;

    private String observaciones;

}
