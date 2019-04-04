import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-creacion-persona',
    templateUrl: './creacion-persona.component.html',
    styleUrls: ['./creacion-persona.component.css']
})
export class CreacionPersonaComponent implements OnInit {

    persona = {
        id: '',
        nombre: '',
        apellido: '',
        tipoIdentificacion: '',
        numeroIdentificacion: ''
    };

    identificacion = ['a', 'b'];

    constructor() { }

    ngOnInit() {
    }
}
