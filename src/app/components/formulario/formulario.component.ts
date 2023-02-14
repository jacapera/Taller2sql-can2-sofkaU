import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss']
})
export class FormularioComponent {

  frmContactos: FormGroup;

  constructor( private http: HttpClient ) {
    this.frmContactos = new FormGroup({
      nombreCompleto: new FormControl(null, [Validators.required]),
      telefono: new FormControl(null, [Validators.required]),
      correoElectronico: new FormControl(null, [Validators.required, Validators.email]),
      fechaNacimiento: new FormControl(null, [Validators.required])
    });
  }

  crearContacto(): void {
    // console.log(this.frmContactos);
    // console.log(this.frmContactos.status);
    // console.log(this.frmContactos.getRawValue());
    // console.log(this.frmContactos.get('nombreCompleto')?.hasError('required'));
    //console.log(this.frmContactos.pristine);
    if ( this.frmContactos.valid ) {
      this.http.post<{success:boolean, message:string, data:any}>('http://localhost:8080/api/contactos/save', this.frmContactos.value)
      .pipe(catchError(this.handleError))
      .subscribe( res => {
        console.log(res);
        alert(res.message)
      this.frmContactos.reset();
      });
    }
  }

  //manejador de errores en las peticiones http
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
