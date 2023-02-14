package com.sofkau.can2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table( name = "contacto" )
@Data
@NoArgsConstructor //genera constructor sin argumentos
@AllArgsConstructor //genera constructor con todos los argumentos
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "con_id" )
    private Long idContacto;

    @Column( name = "con_nombreCompleto" )
    private String nombreCompleto;

    @Column( name = "con_telefono" )
    private String telefono;

    @Column( name = "con_correo_electronico", unique = true)
    private String correoElectronico;

    @Column( name = "con_fecha_nacimiento" )
    private LocalDate fechaNacimiento;

}
