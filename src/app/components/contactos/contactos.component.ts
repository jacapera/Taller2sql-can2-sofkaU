import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Contacto } from './contacto.model';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';


@Component({
  selector: 'app-contactos',
  templateUrl: './contactos.component.html',
  styleUrls: ['./contactos.component.scss'],
})

export class ContactosComponent {

  contactos: Contacto[];
  modalOpen: boolean = false;
  modalEliminarOpen: boolean = false;
  contactoSeleccionado: Contacto;
  contactoSeleccionadoEliminar: Contacto;

  constructor( private http: HttpClient ) {
    this.contactos = [];
    this.contactoSeleccionado = {} as Contacto;
    this.contactoSeleccionadoEliminar = {} as Contacto;
  }

  ngOnInit() {
    this.consultarContactos();
  }

  consultarContactos() {
    this.http.get<Contacto[]>( 'http://localhost:8080/api/contactos/all', { responseType: 'json'} )
    .subscribe( response => {
      this.contactos = response;
      console.log(response)
    })
  }

  openModalEliminar(contacto: Contacto){
    this.modalEliminarOpen = true;
    this.contactoSeleccionadoEliminar = contacto;
  }

  closeModal(){
    this.modalOpen = false;
  }

  closeModalEliminar(){
    this.modalEliminarOpen = false;
  }

  editarContacto(contacto: Contacto) {
    this.contactoSeleccionado = contacto;
    this.modalOpen = true;
  }

  eliminarContacto(contactoSeleccionadoEliminar: Contacto ){
    this.http.delete( 'http://localhost:8080/api/contactos' + '/' + contactoSeleccionadoEliminar.idContacto)
    .subscribe( response => {
      console.log(response);
      this.consultarContactos();
      this.modalEliminarOpen = false;
    })
    console.log(contactoSeleccionadoEliminar);
  }

  guardarCambios() {
    this.http.put<{success:boolean, message:string, data:any}>('http://localhost:8080/api/contactos/update' + '/' + this.contactoSeleccionado.idContacto, this.contactoSeleccionado)
      .pipe(catchError(this.handleError))
      .subscribe(response => {
        console.log(response.message);
        alert(response.message);
        this.consultarContactos();
        this.modalOpen = false;
      })
    console.log(this.contactoSeleccionado)
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error(error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(error.error.message);
      alert(error.error.message);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

}
