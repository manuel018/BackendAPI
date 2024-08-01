package com.Prevenir.DTO.CitaDTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateCitaDTO {

    @FutureOrPresent(message = "La fecha debe ser hoy o en el futuro.")
    @NotNull(message = "La fecha no puede ser nula.")
    private LocalDateTime fecha;

    private String observaciones;

    private String estado;

    @NotNull(message = "El ID del paciente no puede ser nulo.")
    private Long idPaciente;

    @NotNull(message = "El ID del médico no puede ser nulo.")
    private Long idMedico;
}
