import { personaDTO } from './../../../../../../angularSesion2/src/app/nucleo/capa/principal/apoyo/modelo/personaDTO';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from '../template.service';
import { VehiculoDTO } from '../../capa/principal/gestion-vehiculos/modelo/vehiculoDTO';
import { PersonaServicioDTO } from '../../capa/principal/gestion-vehiculos/modelo/personaServicioDTO';

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
}