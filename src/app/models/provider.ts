import { obtenerObjetoByTipo } from "../utils/localStorage";

export interface Provider {
  id: number;
  cod: string;
  razSocial: string;
  rubro: string;
  sitioWeb: string;
  email: string;  
  telefono: number;
  direccion:{
    calle: string;
    nro: number;
    cp: string;
    localidad: string;
    provincia: string;
    pais: string;
  }
  datosFiscales:{
    cuit: string;
    condIva: string;
  }
  datosContacto:{
    nombre: string;
    apellido: string;
    telefono: number;
    email: string;
    rol: string;
  } 
}

export const providers: Provider[] = obtenerObjetoByTipo("provider");
// Array.from({ length: 20 }, (_, index) => ({
//   id: index + 1,
//   cod: `cod${index + 1}`,
//   razSocial: `Razón Social ${index + 1}`,
//   rubro: `Rubro ${index + 1}`,
//   sitioWeb: `http://sitio${index + 1}.com`,
//   email: `email${index + 1}@example.com`,
//   telefono: 123456789 + index,
//   direccion: {
//     calle: `Calle ${index + 1}`,
//     nro: index + 1,
//     cp: `E310 ${index + 1}`,
//     localidad: `Localidad ${index + 1}`,
//     provincia: `Provincia ${index + 1}`,
//     pais: `País ${index + 1}`,
//   },
//   datosFiscales: {
//     cuit: `CUIT${index + 1}`,
//     condIva: `Condición IVA ${index + 1}`,
//   },
//   datosContacto: {
//     nombre: `Nombre ${index + 1}`,
//     apellido: `Apellido ${index + 1}`,
//     telefono: 987654321 + index,
//     email: `empresa${index + 1}@example.com`,
//     rol: `Rol ${index + 1}`,
//   },
// }));