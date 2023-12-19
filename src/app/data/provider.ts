export type Provider = {
  id: number;
  cod: string;
  razSocial: string;
  email: string;
  rubro: string;
  calle: string;
  nro: string;
  pais: string;
  provincia: string;
  localidad: string;
  cuit: string;
  condIva: string;
  nombre: string;
  apellido: string;
  telefono: string;
  rol: string;
}


export const providers: Provider[] = [
    {
      id: 1,
      cod: 'C001',
      razSocial: 'Proveedor Uno S.A.',
      email: 'proveedor1@example.com',
      rubro: 'Electrónica',
      calle: 'Calle Uno',
      nro: '123',
      pais: 'Argentina',
      provincia: 'Buenos Aires',
      localidad: 'La Plata',
      cuit: '30-12345678-9',
      condIva: 'Responsable Inscripto',
      nombre: 'Juan',
      apellido: 'Pérez',
      telefono: '123456789',
      rol: 'Rol Uno',
    },
    {
      id: 2,
      cod: 'C002',
      razSocial: 'Proveedor Dos S.A.',
      email: 'proveedor2@example.com',
      rubro: 'Ropa',
      calle: 'Calle Dos',
      nro: '456',
      pais: 'Argentina',
      provincia: 'Córdoba',
      localidad: 'Córdoba Capital',
      cuit: '30-98765432-1',
      condIva: 'Responsable Inscripto',
      nombre: 'María',
      apellido: 'López',
      telefono: '987654321',
      rol: 'Rol Dos',
    },
    {      
      id: 3,
      cod: 'C003',
      razSocial: 'Proveedor Tres S.A.',
      email: 'proveedor3@example.com',
      rubro: 'Herramientas',
      calle: 'Calle Tres',
      nro: '789',
      pais: 'Argentina',
      provincia: 'Santa Fe',
      localidad: 'Rosario',
      cuit: '30-13579246-0',
      condIva: 'Responsable Inscripto',
      nombre: 'Carlos',
      apellido: 'Gutiérrez',
      telefono: '111223344',
      rol: 'user',
    },
    {      
      id: 4,
      cod: 'C004',
      razSocial: 'Proveedor Cuatro S.A.',
      email: 'proveedor4@example.com',
      rubro: 'Muebles',
      calle: 'Calle Cuatro',
      nro: '101',
      pais: 'Argentina',
      provincia: 'Mendoza',
      localidad: 'Mendoza Capital',
      cuit: '30-24680135-2',
      condIva: 'Responsable Inscripto',
      nombre: 'Laura',
      apellido: 'Martínez',
      telefono: '222334455',
      rol: 'Super admin',
    },
    {      
      id: 5,
      cod: 'C005',
      razSocial: 'Proveedor Cinco S.A.',
      email: 'proveedor5@example.com',
      rubro: 'Juguetes',
      calle: 'Calle Cinco',
      nro: '121',
      pais: 'Argentina',
      provincia: 'Tucumán',
      localidad: 'San Miguel de Tucumán',
      cuit: '30-56789012-3',
      condIva: 'Responsable Inscripto',
      nombre: 'Eduardo',
      apellido: 'Ramírez',
      telefono: '333445566',
      rol: 'Dueño',
    },
    {      
      id: 6,
      cod: 'C006',
      razSocial: 'Proveedor Seis S.A.',
      email: 'proveedor6@example.com',
      rubro: 'Electrodomésticos',
      calle: 'Calle Seis',
      nro: '141',
      pais: 'Argentina',
      provincia: 'Salta',
      localidad: 'Salta Capital',
      cuit: '30-67890123-4',
      condIva: 'Responsable Inscripto',
      nombre: 'Gabriela',
      apellido: 'Fernández',
      telefono: '444556677',
      rol: 'User',
    },
    {
      
      id: 7,
      cod: 'C007',
      razSocial: 'Proveedor Siete S.A.',
      email: 'proveedor7@example.com',
      rubro: 'Librería',
      calle: 'Calle Siete',
      nro: '161',
      pais: 'Argentina',
      provincia: 'Jujuy',
      localidad: 'San Salvador de Jujuy',
      cuit: '30-89012345-6',
      condIva: 'Responsable Inscripto',
      nombre: 'Fernando',
      apellido: 'Sánchez',
      telefono: '555667788',
      rol: 'Admin',
    },
]