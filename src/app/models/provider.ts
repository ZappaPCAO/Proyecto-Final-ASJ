export interface Provider {
  id: string;
  cod: string;
  razSocial: string;
  email: string;
  sitioWeb: string;
  telefonoEmpresa: number;
  emailEmpresa: string;
  rubro: string;
  calle: string;
  nroCalle: number;
  pais: string;
  provincia: string;
  localidad: string;
  cuit: string;
  condIva: string;
  nombre: string;
  apellido: string;
  telefono: number;
  rol: string;
}

export const providers: Provider[] = Array.from({ length: 20 }, (_, index) => ({
  id: `id${index + 1}`,
  cod: `cod${index + 1}`,
  razSocial: `Razón Social ${index + 1}`,
  email: `email${index + 1}@example.com`,
  sitioWeb: `http://sitio${index + 1}.com`,
  telefonoEmpresa: 123456789 + index,
  emailEmpresa: `empresa${index + 1}@example.com`,
  rubro: `Rubro ${index + 1}`,
  calle: `Calle ${index + 1}`,
  nroCalle: index + 1,
  pais: `País ${index + 1}`,
  provincia: `Provincia ${index + 1}`,
  localidad: `Localidad ${index + 1}`,
  cuit: `CUIT${index + 1}`,
  condIva: `Condición IVA ${index + 1}`,
  nombre: `Nombre ${index + 1}`,
  apellido: `Apellido ${index + 1}`,
  telefono: 987654321 + index,
  rol: `Rol ${index + 1}`,
}));