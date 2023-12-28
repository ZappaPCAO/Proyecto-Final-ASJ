// import { Article } from "../models/article";
// import { Provider } from "../models/provider";
// import { PurchaseOrder } from "../models/purchase-order";

export function agregarObjetoSiExiste(tipo:string, el: any) {
    const arregloExistente = JSON.parse(localStorage.getItem(tipo)!) || [];

    console.log("este es el objeto que llega.. " + JSON.stringify(el, null, 1));
    
    console.log("localstorage.. " + JSON.stringify(arregloExistente, null, 1));
    let objetoExistente = arregloExistente.find((objeto:any) => objeto.id == el.id);

    console.log("este es el objeto que esta en el localstorage.. " + JSON.stringify(objetoExistente, null, 1));

    if (objetoExistente) {
      objetoExistente = el; 
    } else {
      arregloExistente.push(el);
    }

    console.log('en agregar ' + arregloExistente);
    // Guardar el arreglo actualizado en el localStorage
    localStorage.setItem(tipo, JSON.stringify(arregloExistente));
}

export function pisarDatosByTipo(tipo: string, objeto: any){ // Si elimina o agrega, piso todo. De momento
  localStorage.setItem(tipo, JSON.stringify(objeto)); 
}

export function obtenerObjetoByTipo(tipo: any):any{
    const arregloExistente = JSON.parse(localStorage.getItem(tipo)!) || [];

    console.log('en obtener ' + JSON.stringify(arregloExistente,null, 1));

    return arregloExistente;
}