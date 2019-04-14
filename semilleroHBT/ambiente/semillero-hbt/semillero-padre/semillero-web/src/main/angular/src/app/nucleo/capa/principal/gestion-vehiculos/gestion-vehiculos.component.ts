import { VehiculoDTO } from './modelo/vehiculoDTO';
import { Component, OnInit } from '@angular/core';
import { LineaDTO } from './modelo/lineaDTO';
import { MarcaDTO } from './modelo/marcaDTO';
import { ConsultaVehiculosService } from '../../../servicios/consulta-vehiculo-servicio/consulta.vehiculo.service';

@Component({
  selector: 'app-gestion-vehiculos',
  templateUrl: './gestion-vehiculos.component.html',
  styleUrls: ['./gestion-vehiculos.component.css']
})
export class GestionVehiculosComponent implements OnInit {

  public header: string = "Gestionar vehículos";
  public listaMarcasDTO: MarcaDTO[];
  public listaLineasDTO: LineaDTO[];
  public listaVehiculoDTO: VehiculoDTO[];
  public marca: string;
  public linea: string;
  public marcaDTO: MarcaDTO;
  constructor(private vehiculosService: ConsultaVehiculosService) { }

  ngOnInit() {
    this.consultarMarcas();
  }


  public consultarMarcas(){
    this.vehiculosService.consultarMarcas().subscribe(
      marcas => {
        this.listaMarcasDTO = marcas;
      },
      error => {
        console.log(error);
      } 
    );
    console.log('resultado servicio.... ' + this.listaMarcasDTO);
  }

  /**
   * consultarLineas
   */
  public consultarLineas() {
    let idMarca = this.marca;
    this.vehiculosService.consultarLineasPorMarca(idMarca).subscribe(
      lineas => {
        this.listaLineasDTO = lineas;
      },
      error => {
        console.log(error);
      } 
    );
    console.log('resultado servicio.... ' + this.listaMarcasDTO);
  }

  
  /**
   * onMarcaSelected
val:any   */
  onMarcaSelected(val:any) {
    this.consultarLineas();
  }
}
