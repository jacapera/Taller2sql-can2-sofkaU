package com.sofkau.can2.dao;

import com.sofkau.can2.domain.Contacto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface ContactoDao extends CrudRepository<Contacto, Long> {

    @Modifying
    @Query( "update Contacto con  set con.nombreCompleto = :nombreCompleto where con.idContacto = :idContacto" )
    public void updateName(
            @Param( value = "idContacto" ) Long idContacto,
            @Param( value = "nombreCompleto") String nombreCompleto
    );

    @Modifying
    @Query( "update Contacto con  set con.telefono = :telefono where con.idContacto = :idContacto" )
    public void updatePhone(
            @Param( value = "idContacto" ) Long idContacto,
            @Param( value = "telefono") String telefono
    );

    @Modifying
    @Query( "update Contacto con  set con.correoElectronico = :correoElectronico where con.idContacto = :idContacto" )
    public void updateEmail(
            @Param( value = "idContacto" ) Long idContacto,
            @Param( value = "correoElectronico") String correoElectronico
    );

    @Modifying
    @Query( "update Contacto con  set con.fechaNacimiento = :fechaNacimiento where con.idContacto = :idContacto" )
    public void updateByrthday(
            @Param( value = "idContacto" ) Long idContacto,
            @Param( value = "fechaNacimiento") LocalDate fechaNacimiento
    );
}
