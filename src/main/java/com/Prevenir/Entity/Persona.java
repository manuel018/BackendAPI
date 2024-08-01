package com.Prevenir.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombrePrimer", nullable = false)
    private String nombrePrimer;

    @Column(name = "nombreSegundo")
    private String nombreSegundo;

    @Column(name = "apellidoMaterno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "apellidoPaterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "sexo")
    private byte sexo;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "foto")
    private String foto;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fechaRegistro") //Seteamos siempre la hora actual o default en bd
    private final LocalDate fechaRegistro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false, referencedColumnName = "id")
    private Estado estado;

}
