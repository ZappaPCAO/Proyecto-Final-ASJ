import { Injectable } from '@angular/core';
import { purchaseOrders } from '../data/purchase-order';

const dataPurchaseOrders = purchaseOrders;

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderService {

  constructor() { }
}
