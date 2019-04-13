import { ConsultaVehiculosService } from './../../../../servicios/consulta-vehiculo-servicio/consulta.vehiculo.service';
import { Component, Input, OnInit } from '@angular/core';
import { VehiculoDTO } from '../modelo/vehiculoDTO';
import { PersonaServicioDTO } from '../modelo/personaServicioDTO';


@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {

  @Input() public marca: string;
  @Input() public placa: string;

  public listaVehiculoDTO: VehiculoDTO[];
  public vehiculoDTO: VehiculoDTO;

  public listaPersonasDTO: PersonaServicioDTO[];
  public personaDTO: PersonaServicioDTO;

  constructor(private vehiculosService: ConsultaVehiculosService) { }

  ngOnInit() {
  }

  public consultarPersonas() {
    let tipoID: string = 'CC';
    let numID: string = '12345'
    this.vehiculosService.consultarPersonas(tipoID, numID).subscribe(
      personas => {
          this.listaPersonasDTO = personas;
        },
        error => {
          console.log(error);
        } 
  );    
  console.log('resultado servicio.... ' + this.listaPersonasDTO)
  }

}