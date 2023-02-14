package com.sofkau.can2.service;

import com.sofkau.can2.dao.ContactoDao;
import com.sofkau.can2.domain.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoDao contactoDao;

    @Override
    @Transactional( readOnly = true )
    public List<Contacto> list() {
        return (List<Contacto>) contactoDao.findAll();
    }

    @Override
    @Transactional
    public Contacto save(Contacto contacto) {
        return contactoDao.save(contacto);
    }

    @Override
    @Transactional
    public Contacto update(Long idContacto, Contacto contacto) {
        contacto.setIdContacto(idContacto);
        if (contacto.getIdContacto() != null) {
            Optional<Contacto> contactoAux = contactoDao.findById(contacto.getIdContacto());
            if ( contactoAux.isPresent() ) {
                if( ( (contacto.getNombreCompleto() != null) && !("".equals(contacto.getNombreCompleto())) ) ) {
                    contactoAux.get().setNombreCompleto(contacto.getNombreCompleto());
                }
                if( contacto.getTelefono() != null && !"".equals(contacto.getTelefono()) ) {
                    contactoAux.get().setTelefono(contacto.getTelefono());
                }
                if( contacto.getCorreoElectronico() != null && !"".equals(contacto.getCorreoElectronico()) && contacto.getCorreoElectronico().contains("@") ) {
                    contactoAux.get().setCorreoElectronico(contacto.getCorreoElectronico());
                }
                if( contacto.getFechaNacimiento() != null ) {
                    contactoAux.get().setFechaNacimiento(contacto.getFechaNacimiento());
                }
                contactoDao.save(contactoAux.get());
                return contactoAux.get();
            } else {
                return contacto;
            }
        } else {
            return contacto;
        }
    }

    @Transactional
    public Contacto updateName(Long idContacto, Contacto contacto) {
        contactoDao.updateName(idContacto, contacto.getNombreCompleto());
        return contacto;
    }

    @Transactional
    public void updatePhone( Long idContacto, Contacto contacto) {
        contactoDao.updatePhone(idContacto, contacto.getTelefono());
    }

    @Transactional
    public void updateEmail( Long idContacto, Contacto contacto) {
        contactoDao.updateEmail(idContacto, contacto.getCorreoElectronico());
    }

    @Transactional
    public void updateByrthday( Long idContacto, Contacto contacto) {
        contactoDao.updateByrthday(idContacto, contacto.getFechaNacimiento());
    }

    @Override
    @Transactional
    public void delete(Contacto contacto) {
        contactoDao.delete(contacto);
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<Contacto> findContacto(Contacto contacto ) {
        return contactoDao.findById( contacto.getIdContacto() );
    }
}
