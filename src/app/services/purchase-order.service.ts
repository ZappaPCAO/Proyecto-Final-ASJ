import { Injectable } from '@angular/core';
import { PurchaseOrder, purchasesOrders } from '../models/purchase-order';
import { agregarObjetoSiExiste, pisarDatosByTipo } from '../utils/localStorage'; 

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderService {
  dataPurchaseOrders: PurchaseOrder[] = purchasesOrders || [];

  constructor() { }

  get (){
    return this.dataPurchaseOrders.sort((a:PurchaseOrder,b:PurchaseOrder) => a.fecEmision.localeCompare(b.fecEmision));
  }

  getById(id: number): PurchaseOrder {
    let purchaseOrder!: any;

    if(this.dataPurchaseOrders.length > 0){
      purchaseOrder = this.dataPurchaseOrders.find( purchaseOrder => purchaseOrder.id == id ); 
    }

    return purchaseOrder;
  }
  
  post(purchaseOrder: PurchaseOrder){
    purchaseOrder.id = (this.dataPurchaseOrders && this.dataPurchaseOrders.length > 0) ? this.dataPurchaseOrders[this.dataPurchaseOrders.length-1].id + 1 : 1; // Controlo la id
    this.dataPurchaseOrders.push(purchaseOrder);
    pisarDatosByTipo('purchase-order', this.dataPurchaseOrders);
  }

  put(purchaseOrder: PurchaseOrder) {
    let auxPurchaseOrder: PurchaseOrder = this.dataPurchaseOrders.find(purOrder => purOrder.id == purchaseOrder.id)!;
    
    auxPurchaseOrder = purchaseOrder;
    agregarObjetoSiExiste('purchase-order', purchaseOrder);
  }

  delete(purchaseOrder: PurchaseOrder) {
    let index: number = this.dataPurchaseOrders.findIndex(purOrder => purOrder.id === purchaseOrder.id);
    
    this.dataPurchaseOrders.splice(index, 1);
    
    pisarDatosByTipo('purchase-order', this.dataPurchaseOrders);
  }
}
