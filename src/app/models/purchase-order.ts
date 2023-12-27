import { Article } from "./article";
import { Provider } from "./provider";

export interface PurchaseOrder {
  id: number,
  nroOC: string,
  fecEmision: string,
  fecEntrega: string,
  email: string,
  descri: string,
  detalle: Detalle[],
  estado: 'activa' | 'cancelada',
  total: number,
}

export interface Detalle{
  proveedor: Provider,
  articulo: Article,
  cantidad: number,
  subtotal: number,
}

export const purchasesOrders: PurchaseOrder[] = [{
  id: 0,
  nroOC: '',
  fecEmision: '',
  fecEntrega: '',
  email: '',
  descri: '',
  detalle: [{
    proveedor: {
      id: 0,
      cod: '',
      razSocial: '',
      rubro: '',
      sitioWeb: '',
      email: '',
      telefono: 0,
      direccion: {
        calle: '',
        nro: 0,
        cp: '',
        localidad: '',
        provincia: '',
        pais: '',
      },
      datosFiscales: {
        cuit: '',
        condIva: '',
      },
      datosContacto: {
        nombre: '',
        apellido: '',
        telefono: 0,
        email: '',
        rol: '',
      }
    },
    articulo: {
      id: 0,
      proveedor: {
        id: 0,
        cod: '',
        razSocial: '',
        rubro: '',
        sitioWeb: '',
        email: '',
        telefono: 0,
        direccion: {
          calle: '',
          nro: 0,
          cp: '',
          localidad: '',
          provincia: '',
          pais: '',
        },
        datosFiscales: {
          cuit: '',
          condIva: '',
        },
        datosContacto: {
          nombre: '',
          apellido: '',
          telefono: 0,
          email: '',
          rol: '',
        }
      },
      cod: '',
      categoria: '',
      producto: '',
      descri: '',
      precio: 0,
    },
    cantidad: 0,
    subtotal: 0,
  }],
  estado: 'activa',
  total: 0,
}];
