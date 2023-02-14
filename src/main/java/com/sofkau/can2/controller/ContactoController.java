package com.sofkau.can2.controller;

import com.sofkau.can2.domain.Contacto;
import com.sofkau.can2.service.ContactoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(
        origins =  "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = {"Content-Type", "Authorization"}
)
@RequestMapping("api/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping( "/")
    public Map<String, String> index() {
        var respuesta = new HashMap<String, String>();
        respuesta.put( "message", "Hola Bienvenido a mi agenda de contactos telefónicos");
        return respuesta;
    }

    @GetMapping("/all")
    public List<Contacto> listado(){
        //var contacto = contactoService.list();
        //return contacto;
        return contactoService.list();
    }

//    @PostMapping( "/save" )
//    public ResponseEntity<Contacto> crear(@RequestBody Contacto contacto) {
//        try {
//            log.info("Contacto a crear: {}", contacto);
//            contactoService.save(contacto);
//            return new ResponseEntity<>( contacto, HttpStatus.CREATED );
//        } catch (Exception exc) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping ( "/save" )
    public ResponseEntity<ApiResponse> save( @RequestBody Contacto contacto ) {
        try {
            log.info("Contacto a crear: {}", contacto);
            Contacto savedContacto = contactoService.save(contacto);
            return ResponseEntity.ok(new ApiResponse(true, "Contacto guardado con éxito", savedContacto));
        } catch ( DataIntegrityViolationException exc ) {
            return ResponseEntity.badRequest().body( new ApiResponse(false, "El correo electrónico que quieres guardar ya está registrado", null) );
        } catch ( Exception exc ) {
            return ResponseEntity.badRequest().body( new ApiResponse(false, "Error al guardar el contacto", null) );
        }
    }

    @PutMapping( "/update/{idContacto}" )
    public ResponseEntity<ApiResponse> actualizar( @RequestBody Contacto contacto, @PathVariable( "idContacto") Long idContacto) {
        try {
            log.info( "Contacto a modificar: {}", contacto );
            Contacto updateContacto = contactoService.update( idContacto, contacto );
            return ResponseEntity.ok( new ApiResponse(true, "Contacto actualizado con éxito", updateContacto) );
        } catch ( DataIntegrityViolationException exc ) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "El correo electrónico que quieres actualizar ya está registrado", null));
        } catch ( Exception exc) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Error al actualizar el contacto", null));
        }
    }

    @PatchMapping("/name/{idContacto}" )
    public ResponseEntity<ApiResponse> actualizarNombre( @RequestBody Contacto contacto, @PathVariable( "idContacto") Long idContacto ) {
        try {
            log.info( "Contacto a modificar: {}", contacto);
            Contacto updateNombre = contactoService.updateName(idContacto, contacto);
            return ResponseEntity.ok( new ApiResponse(true, "Nombre de contacto actualizado con éxito", updateNombre) );
        } catch ( Exception exc) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Error al actualizar el contacto", null));
        }

    }

    @PatchMapping("/phone/{idContacto}" )
    public ResponseEntity<Contacto> actualizarTelefono( @RequestBody Contacto contacto, @PathVariable( "idContacto") Long idContacto ) {
        log.info( "Contacto a modificar: {}", contacto);
        contactoService.updatePhone(idContacto, contacto);
        return new ResponseEntity<>( contacto, HttpStatus.OK );
    }

    @PatchMapping("/email/{idContacto}" )
    public ResponseEntity<Contacto> actualizarCorreo( @RequestBody Contacto contacto, @PathVariable( "idContacto") Long idContacto) {
        log.info( "Contacto a modificar: {}", contacto);
        contactoService.updateEmail(idContacto, contacto);
        return new ResponseEntity<>( contacto, HttpStatus.OK );
    }

    @PatchMapping("/birthday/{idContacto}")
    public ResponseEntity<Contacto> actualizarFechaNacimiento( @RequestBody Contacto contacto, @PathVariable( "idContacto") Long idContacto) {
        log.info( "Contacto a modificar: {}", contacto);
        contactoService.updateByrthday(idContacto, contacto);
        return new ResponseEntity<>( contacto, HttpStatus.OK );
    }

    @DeleteMapping( "/{idContacto}" )
    public ResponseEntity<Contacto> borrar( Contacto contacto ) {
        log.info( "Contacto a borrar: {}", contacto );
        contactoService.delete( contacto );
        return new ResponseEntity<>( contacto, HttpStatus.OK );

    }


}
