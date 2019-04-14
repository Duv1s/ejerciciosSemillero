import { ConsultaVehiculosService } from './../../../../servicios/consulta-vehiculo-servicio/consulta.vehiculo.service';
import { Component, Input, OnInit } from '@angular/core';
import { VehiculoDTO } from '../modelo/vehiculoDTO';
import { PersonaServicioDTO } from '../modelo/personaServicioDTO';
import { LineaDTO } from '../modelo/lineaDTO';
import { MarcaDTO } from '../modelo/marcaDTO';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';


@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {

  @Input() public marca: string;
  @Input() public linea: string;

  public listaVehiculoDTO: VehiculoDTO[];
  public listaMarcasDTO: MarcaDTO[];
  public listaLineasDTO: LineaDTO[];
  public vehiculo: VehiculoDTO;
  public header: string = "Crear vehiculo";
  public showFormCrearVehiculo: boolean = false;



  constructor(private vehiculosService: ConsultaVehiculosService) { }

  ngOnInit() {
    this.consultarMarcas();
    this.crearVehiculoFromulario();
  }

  /**
   * Función que permite consumir el servicio de consultar los vehiculos.
   */
  public consultarVehiculos() {
    this.vehiculosService.consultarVehiculo(this.marca, this.linea).subscribe(
      vehiculos => {
        this.listaVehiculoDTO = vehiculos;
      },
      error => {
        console.log(error);
      }
    );
  }

  /**
  * Función que permite consumir el servicio de consultar las marcas.
  */
  public consultarMarcas() {
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
  * Función que permite consumir el servicio de consultar las lineas
  */
  public consultarLineas() {
    let idMarca: string = this.vehiculo.marca
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
  * Función que permite consumir el servicio de crear vehiculos
  */
  public crearVehiculo(vehiculo: VehiculoDTO) {
    var vehiculoPost = this.vehiculoAsVehiculoPostCrear(vehiculo);
    // console.log(vehiculoPost);
    this.vehiculosService.crearVehiculo(vehiculoPost).subscribe(
      vehiculoCreado => {
        this.crearVehiculoFromulario();
      },
      error => {
        console.log(error);
      }
    );
  }

  /**
   * Función que permite consumir el servicio de editar un vehiculo.
   * @param vehiculo 
   */
  public editarVehiculo(vehiculo: VehiculoDTO) {
    var vehiculoPost = this.vehiculoAsVehiculoPost(vehiculo);
    this.vehiculosService.editarVehiculo(vehiculoPost).subscribe(
      vehiculoCreado => {
        this.crearVehiculoFromulario();
      },
      error => {
        console.log(error);
      }
    );
  }

  /**
   * Función que permite consumir el servicio de eliminar un vehiculo por Id.
   * @param idVehiculo 
   */
  public eliminarVehiculo(idVehiculo) {
    this.vehiculosService.eliminarVehiculo(idVehiculo).subscribe(
      vehiculoEliminado => {
        // this.crearVehiculoFromulario();
      },
      error => {
        console.log(error);
      }
    );
  }

  /**
   * Función que convierte un VehiculoDTO en un Json de edicion de vehiculos.
   */
  public vehiculoAsVehiculoPost(vehiculo: VehiculoDTO) {
    return JSON.stringify({ idVehiculo: vehiculo.idVehiculo, linea: { idLinea: Number(vehiculo.linea) }, modelo: Number(vehiculo.modelo), placa: vehiculo.placa });
  }

  /**
   * Función que convierte un VehiculoDTO en un Json de creacion de vehiculos.
   */
  public vehiculoAsVehiculoPostCrear(vehiculo: VehiculoDTO) {
    return JSON.stringify({ linea: { idLinea: Number(vehiculo.linea) }, modelo: Number(vehiculo.modelo), placa: vehiculo.placa });
  }

  /**
   * Función que toma los datos del vehiculo y los crea o edita, ademas realzia funcioones de actualizacion de la vista 
   */
  public guardar(vehiculo: VehiculoDTO) {
    console.log('Marca: ' + this.vehiculo.marca +
      '\nLinea: ' + this.vehiculo.linea +
      '\nPlaca: ' + this.vehiculo.placa +
      '\nModelo: ' + this.vehiculo.modelo +
      '\nColor: ' + this.vehiculo.color);
    if (this.header == "Crear vehiculo") {
      this.crearVehiculo(this.vehiculo);
    } else {
      this.editarVehiculo(vehiculo);
    }
    this.ocultarForulario();
    this.listaVehiculoDTO = [];
    this.consultarVehiculos();
    this.consultarVehiculos();
    this.crearVehiculoFromulario();
  }
  /**
   * Función que elimina un vehiculo tanto del back-ed como del front-end
   */
  public eliminar(vehiculo: VehiculoDTO) {
    // console.log("Quiero eliminar el vehiculo con el id" +  this.listaVehiculoDTO[this.listaVehiculoDTO.indexOf(vehiculo)].idVehiculo);
    this.eliminarVehiculo(this.listaVehiculoDTO[this.listaVehiculoDTO.indexOf(vehiculo)].idVehiculo);
    this.listaVehiculoDTO.splice(this.listaVehiculoDTO.indexOf(vehiculo), 1);
  }

  /**
   * Función que edita un vehiculo tanto en el back-ed como el front-end
   */
  public editar(vehiculo: VehiculoDTO) {
    this.header = "Editar Vehiculo";
    this.vehiculo = this.listaVehiculoDTO[this.listaVehiculoDTO.indexOf(vehiculo)];
    this.mostrarFormulario();
  }

  /**
   * Función que actualiza las lineas según a marca seleccionada.
   */
  onMarcaSelected(val: any) {
    this.consultarLineas();
  }

  /**
   * Función que inicializa las variables del vehiculo.
   */
  public crearVehiculoFromulario() {
    this.vehiculo = {
      idVehiculo: '',
      marca: '',
      placa: '',
      linea: '',
      modelo: '',
      color: ''
    };
  }

  /**
   * Función que permite mostrar el fromulario de creacion de vehiculo.
   */
  public mostrarFormulario() {
    this.showFormCrearVehiculo = true;
  }

  /**
     * Función que permite ocultar el fromulario de creacion de vehiculo.
     */
  public ocultarForulario() {
    this.showFormCrearVehiculo = false;
  }
}