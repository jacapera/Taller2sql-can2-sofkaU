import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormularioComponent } from './components/formulario/formulario.component';
import { ContactosComponent } from './components/contactos/contactos.component';

const routes: Routes = [
  { path:'', redirectTo: '/', pathMatch: 'full'},
  { path:'crearContacto', component: FormularioComponent },
  { path: 'traerContactos', component: ContactosComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
