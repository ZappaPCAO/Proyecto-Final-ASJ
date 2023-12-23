export type PurchaseOrder = {
  id: number,
  nroOC: string,
  fecEmi: string,
  fecRecep: string,
  email: string,
  proveedor: string,
  articulo: string,
  cantidad: number,
}

export const purchaseOrders: PurchaseOrder[] = [
    {
      id: 1,
      nroOC: 'OC001',
      fecEmi: '2023-01-01',
      fecRecep: '2023-01-10',
      email: 'correo1@example.com',
      proveedor: 'ProveedorUno',
      articulo: 'Producto Uno',
      cantidad: 5,
    },
    {
      id: 2,
      nroOC: 'OC002',
      fecEmi: '2023-02-01',
      fecRecep: '2023-02-15',
      email: 'correo2@example.com',
      proveedor: 'ProveedorDos',
      articulo: 'Producto Dos',
      cantidad: 10,
    },
    {
      id: 3,
      nroOC: 'OC003',
      fecEmi: '2023-03-01',
      fecRecep: '2023-03-20',
      email: 'correo3@example.com',
      proveedor: 'ProveedorTres',
      articulo: 'Producto Tres',
      cantidad: 8,
    },
    {
      id: 4,
      nroOC: 'OC004',
      fecEmi: '2023-04-01',
      fecRecep: '2023-04-25',
      email: 'correo4@example.com',
      proveedor: 'ProveedorCuatro',
      articulo: 'Producto Cuatro',
      cantidad: 15,
    },
    {
      id: 5,
      nroOC: 'OC005',
      fecEmi: '2023-05-01',
      fecRecep: '2023-05-30',
      email: 'correo5@example.com',
      proveedor: 'ProveedorCinco',
      articulo: 'Producto Cinco',
      cantidad: 3,
    },
    {
      id: 6,
      nroOC: 'OC006',
      fecEmi: '2023-06-01',
      fecRecep: '2023-06-05',
      email: 'correo6@example.com',
      proveedor: 'ProveedorSeis',
      articulo: 'Producto Seis',
      cantidad: 12,
    },
    {
      id: 7,
      nroOC: 'OC007',
      fecEmi: '2023-07-01',
      fecRecep: '2023-07-10',
      email: 'correo7@example.com',
      proveedor: 'ProveedorSiete',
      articulo: 'Producto Siete',
      cantidad: 6,
    },
    {
      id: 8,
      nroOC: 'OC008',
      fecEmi: '2023-08-01',
      fecRecep: '2023-08-15',
      email: 'correo8@example.com',
      proveedor: 'ProveedorOcho',
      articulo: 'Producto Ocho',
      cantidad: 9,
    },
    {
      id: 9,
      nroOC: 'OC009',
      fecEmi: '2023-09-01',
      fecRecep: '2023-09-20',
      email: 'correo9@example.com',
      proveedor: 'ProveedorNueve',
      articulo: 'Producto Nueve',
      cantidad: 7,
    },
    {
      id: 10,
      nroOC: 'OC010',
      fecEmi: '2023-10-01',
      fecRecep: '2023-10-25',
      email: 'correo10@example.com',
      proveedor: 'ProveedorDiez',
      articulo: 'Producto Diez',
      cantidad: 4,
    },
  ];