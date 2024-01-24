export interface Provincia {
    id: string;
    nombre: string;
    localidades: Localidad[];
}

interface Localidad {
    id: string;
    nombre: string;
}