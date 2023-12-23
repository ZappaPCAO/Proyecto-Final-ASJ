import { Injectable } from '@angular/core';
import { purchaseOrders } from '../models/purchase-order';

const dataPurchaseOrders = purchaseOrders || [];

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderService {

  constructor() { }

  get(){
    return dataPurchaseOrders;
  }

  post(purchaseOrder: any){
    purchaseOrder.id = (dataPurchaseOrders && dataPurchaseOrders.length > 0) ? dataPurchaseOrders[dataPurchaseOrders.length-1].id + 1 : 1; // Controlo la id
    
    dataPurchaseOrders.push(purchaseOrder);
  }
}
