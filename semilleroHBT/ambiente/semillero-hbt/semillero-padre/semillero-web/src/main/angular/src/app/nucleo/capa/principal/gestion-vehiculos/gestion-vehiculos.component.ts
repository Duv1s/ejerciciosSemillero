import { Component, OnInit } from '@angular/core';
import { VehiculoDTO } from './modelo/vehiculoDTO';

@Component({
  selector: 'app-gestion-vehiculos',
  templateUrl: './gestion-vehiculos.component.html',
  styleUrls: ['./gestion-vehiculos.component.css']
})
export class GestionVehiculosComponent implements OnInit {

  public vehiculo: VehiculoDTO;
  constructor() { }

  ngOnInit() {
    this.vehiculo = {
      marca: 'Mazda',
      placa: '123ABC',
      linea: '',
      modelo: '',
      color: ''
    };
  }

}
