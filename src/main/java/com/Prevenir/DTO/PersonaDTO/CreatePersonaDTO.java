package com.Prevenir.DTO.PersonaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreatePersonaDTO {

    @NotNull
    @Length(min = 3, max = 30)
    private String nombrePrimer;

    @Length(max = 30)
    private String nombreSegundo;

    @NotNull
    @Length(min = 2, max = 30)
    private String apellidoMaterno;

    @NotNull
    @Length(min = 2, max = 30)
    private String apellidoPaterno;

    @NotNull
    @Size(min = 8)
    private String dni;

    @Size(min = 6, max = 30)
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números")
    private String telefono;

    @NotNull
    private byte sexo;

    @Email
    private String correo;

    private String foto;

    @Valid
    @Past
    private LocalDate fechaNacimiento;

    private LocalDate fechaRegistro = LocalDate.now();

}
