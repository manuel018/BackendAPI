package com.Prevenir.DTO.MedicoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMedicoDTO {

    @Valid
    @NotNull
    private Long idEspecialidad;

}
