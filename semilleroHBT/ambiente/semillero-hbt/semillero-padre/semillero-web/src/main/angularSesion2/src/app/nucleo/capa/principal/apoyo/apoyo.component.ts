import { Component, OnInit } from '@angular/core';
import { personaDTO } from './modelo/personaDTO';
import { from } from 'rxjs';

@Component({
  selector: 'app-apoyo',
  templateUrl: './apoyo.component.html',
})
export class ApoyoComponent implements OnInit {
  public persona: personaDTO;
  public personas: personaDTO[];

  public mostrarFormulario: boolean;
  constructor() { }

  ngOnInit() {

    // this.persona = {
      // id: '0',
      // nombre: '',
      // apellido: '',
      // tipoIdentificacion: '',
      // numeroIdentificacion: '',
      // mayorEdad: false,
      // sexo: ''
    // };

    this.personas = [];
    this.mostrarFormulario = true;
  }

  public mostrar(){

  }

  public ocultar(){

  }

  public guardar(){
    this.personas.push(this.persona);
    console.log('guardar()' + this.persona.nombre);
  }
  private borrar() {
    
  }
}
