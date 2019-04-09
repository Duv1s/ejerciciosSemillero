import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CapaComponent } from './capa/capa.component';
import { EncabezadoComponent } from './capa/encabezado/encabezado.component';
import { PrincipalComponent } from './capa/principal/principal.component';
import { PiePaginaComponent } from './capa/pie-pagina/pie-pagina.component';
import { CreacionPersonasComponent } from './capa/principal/creacion-personas/creacion-personas.component';
import { ApoyoComponent } from './capa/principal/apoyo/apoyo.component';
import { FormsModule } from '@angular/forms';


//Nucleo representa al modulo que va a contener los componentes que van a orquestar la aplicacion 
//Debe ser importado en el componente principal AppModule
@NgModule({
  declarations: [CapaComponent, EncabezadoComponent, PrincipalComponent, PiePaginaComponent, CreacionPersonasComponent, ApoyoComponent],
  imports: [
    CommonModule,RouterModule,FormsModule
  ],
  exports: [
  	CapaComponent
  ]
})
export class NucleoModule { }
