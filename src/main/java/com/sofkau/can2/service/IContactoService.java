package com.sofkau.can2.service;

import com.sofkau.can2.domain.Contacto;

import java.util.List;
import java.util.Optional;

public interface IContactoService {

    public List<Contacto> list();

    public Contacto save(Contacto contacto);

    public Contacto update(Long idContacto, Contacto contacto);

    public void delete( Contacto contacto);

    public Optional<Contacto> findContacto(Contacto contacto);


}
