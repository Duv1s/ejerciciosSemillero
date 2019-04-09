export interface personaDTO {
    id: string;
    nombre: string;
    apellido: string;
    tipoIdentificacion: string;
    numeroIdentificacion: number | string;
    numeroTelefonico : string;
    fechaNacimiento : Date;
    mayorEdad: boolean;
    sexo: string; 
    comprador : boolean;
    vendedor : boolean;
}