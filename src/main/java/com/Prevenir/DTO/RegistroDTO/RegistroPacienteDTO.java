package com.Prevenir.DTO.RegistroDTO;

import com.Prevenir.DTO.PacienteDTO.CreatePacienteDTO;
import com.Prevenir.DTO.PersonaDTO.CreatePersonaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroPacienteDTO {

    @Valid
    @NotNull(message = "Los datos del administrativo son obligatorios")
    private CreatePacienteDTO paciente;

    @Valid
    @NotNull(message = "Los datos de la persona son obligatorios")
    private CreatePersonaDTO persona;
}
