import { Component, OnInit } from "@angular/core";
import { personaDTO } from "../apoyo/modelo/personaDTO";


@Component({
  selector: "app-creacion-personas",
  templateUrl: "./creacion-personas.component.html"
})
export class CreacionPersonasComponent implements OnInit {
  public header: string = "Crear personas";
  public formHidden: boolean = false;
  public persona: personaDTO;
  public personas: personaDTO[]; //Borrar
  public personaIsMale: boolean = true;
  public personaAge: number;

  constructor() { }

  ngOnInit() {
    this.createPersona();
    this.personas = [];
 
  }

  public guardar() {
    // this.calculateAge(this.persona.fechaNacimiento);
    
    this.personas.push(this.persona);  // Borrar
    
    console.log(
      this.persona.id +
      "\nTipo identificacion:  " +
      this.persona.tipoIdentificacion +
      "\nNumero identificacion:  " +
      this.persona.numeroIdentificacion +
      "\nNombre: " +
      this.persona.nombre +
      "\nApellido:  " +
      this.persona.apellido +
      "\nNumero de telefono: " +
      this.persona.numeroTelefonico +
      "\nFecha de Nacimiento " +
      this.persona.fechaNacimiento +
      "\nMayor de edad" +
      this.persona.mayorEdad +
      "\nSexo " +
      this.persona.sexo +
      "\nComprador " +
      this.persona.comprador +
      "\nVendedor " + this.persona.vendedor

    );
    this.createPersona();
  }

  /**
   * getAge
   */
  public getAge(birthday) {
    var datePerson = new Date(birthday).getFullYear();
    var currentYear = new Date().getFullYear();    
     return currentYear - datePerson;
  
  }

  public validateMayoriaEdad(age){
    if (age >= 18){
      this.persona.mayorEdad = true;
    }
  }

  public mostrar() {
    this.formHidden = false;

  }

  public ocultar() {
    this.formHidden = true;

  }

  public borrar(per: personaDTO) {
    this.personas.splice(this.personas.indexOf(per), 1);
  }

  
  public editar(per: personaDTO) {
    this.header = "Editar Personas";
    this.mostrar();
    this.persona = this.personas[this.personas.indexOf(per)];
    this.personas.splice(this.personas.indexOf(per), 1);
  }

  private createPersona() {
    this.persona= {
      id: "0",
      nombre: "",
      apellido: "",
      tipoIdentificacion: "Cedula de ciudadania",
      numeroIdentificacion: "",
      numeroTelefonico: "0",
      fechaNacimiento: new Date(),
      mayorEdad: false,
      sexo: "Masculino",
      comprador:false,
      vendedor:false,
    };
  }
  
  // public onChange() {
  //   console.log('Lamé al on change')
  //   this.validateMayoriaEdad(this.getAge(this.persona.fechaNacimiento));
  // } Intenté realizar la validacion si una persona es mayor de edad y se chequeara automaticamente
}