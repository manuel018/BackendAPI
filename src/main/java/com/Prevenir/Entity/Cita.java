package com.Prevenir.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "fechaRegistro")
    private final LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "observaciones")
    private String observaciones;

    //Reemplazar por tabla de estados para cita en la version 2.0
    //@ManyToOne
    //@JoinColum(name="idEstadoCita") private EstadoCita estadoCita;???
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "idMedico", nullable = false)
    private Medico medico;
}
