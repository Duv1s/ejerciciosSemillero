export interface personaDTO {
    id: string;
    nombre: string;
    apellido: string;
    tipoIdentificacion: string;
    numeroIdentificacion: number | string;
    numeroTelefonico : string;
    fechaNacimiento : number;
    mayorEdad: boolean;
    sexo: string;
}