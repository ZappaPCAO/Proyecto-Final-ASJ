import { Injectable } from '@angular/core';
import { PurchaseOrder, purchasesOrders } from '../models/purchase-order';

const dataPurchaseOrders = purchasesOrders || [];

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderService {


  constructor() { }

  get (){
    return dataPurchaseOrders;
  }

  getById(id: number): PurchaseOrder {
    let purchaseOrder!: any;

    if(dataPurchaseOrders.length > 0){
      purchaseOrder = dataPurchaseOrders.find( purchaseOrder => purchaseOrder.id == id ); 
    }

    return purchaseOrder;
  }
  
  post(purchaseOrder: PurchaseOrder){
    purchaseOrder.id = (dataPurchaseOrders && dataPurchaseOrders.length > 0) ? dataPurchaseOrders[dataPurchaseOrders.length-1].id + 1 : 1; // Controlo la id
    dataPurchaseOrders.push(purchaseOrder);
  }

  put(purchaseOrder: PurchaseOrder) {
    let auxPurchaseOrder: PurchaseOrder = dataPurchaseOrders.find(purOrder => purOrder.id == purchaseOrder.id)!;
    
    auxPurchaseOrder = purchaseOrder;
  }

  delete(purchaseOrder: PurchaseOrder) {
    let index: number = dataPurchaseOrders.findIndex(purOrder => purOrder.id === purchaseOrder.id);
    
    dataPurchaseOrders.splice(index, 1);
  }
}
