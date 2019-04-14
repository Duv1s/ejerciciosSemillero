import { VehiculoDTO } from './../../capa/principal/gestion-vehiculos/modelo/vehiculoDTO';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from '../template.service';
import { PersonaServicioDTO } from '../../capa/principal/gestion-vehiculos/modelo/personaServicioDTO';
import { MarcaDTO } from '../../capa/principal/gestion-vehiculos/modelo/marcaDTO';
import { LineaDTO } from '../../capa/principal/gestion-vehiculos/modelo/lineaDTO';

@Injectable({
    providedIn: 'root'
})

export class ConsultaVehiculosService extends AbstractService {

    public vehiculo: VehiculoDTO;
    public vehiculos: VehiculoDTO[];



    constructor(injector: Injector) {
        super(injector);
    }

    public consultarPersonas(tipoID: string, numID: string): Observable<PersonaServicioDTO[]> {
        {
            return this.get<PersonaServicioDTO[]>("/semillero-servicios", "/ConsultasRest/consultarPersonas",
                {
                    "tipoID": tipoID,
                    "numID": numID,
                });
        }
    }

    public consultarMarcas(): Observable<MarcaDTO[]> {
        {
            return this.get<MarcaDTO[]>("/semillero-servicios", "/ConsultasRest/consultarMarcas", {});
        }
    }

    public consultarLineasPorMarca(marca: string): Observable<LineaDTO[]> {
        {
            return this.get<LineaDTO[]>("/semillero-servicios", "/ConsultasRest/consultarLineasPorMarca",
                {
                    "idMarca": marca,
                });
        }
    }

    public crearVehiculo(vehiculo: string): Observable<any> {
        
        {
            return this.post<string>("/semillero-servicios", "/ConsultasRest/crearVehiculo", vehiculo);
        }
    }

    public editarVehiculo(vehiculo: string): Observable<any> {
        
        {
            return this.post<string>("/semillero-servicios", "/ConsultasRest/editarVehiculo", vehiculo);
        }
    }

    public consultarVehiculo(idMarca: string, idLinea: string): Observable<VehiculoDTO[]> {
        {
            return this.get<VehiculoDTO[]>("/semillero-servicios", "/ConsultasRest/consultarVehiculos",
                {
                    "idMarca": idMarca,
                    "idLinea": idLinea,
                });
        }
    }

    public eliminarVehiculo(idVehiculo: string): Observable<VehiculoDTO[]> {
        {
            return this.get<VehiculoDTO[]>("/semillero-servicios", "/ConsultasRest/eliminarVehiculo",
                {
                    "idVehiculo": idVehiculo,
                });
        }
    }
}