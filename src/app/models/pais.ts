export interface Pais{
    id: string;
    nombre: string;
    provincias: Provincia[];
}

interface Provincia {
    id: string;
    nombre: string;
    localidades: Localidad[];
}

interface Localidad {
    id: string;
    nombre: string;
}