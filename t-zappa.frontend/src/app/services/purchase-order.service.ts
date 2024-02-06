import { Injectable } from '@angular/core';
import { PurchaseOrder } from '../models/purchase-order';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderService {

  private url = 'http://localhost:8080/purchase-orders';

  constructor(private http: HttpClient) {}

  get () : Observable<PurchaseOrder[]>{
    return this.http.get<PurchaseOrder[]>(this.url);
  }

  getById(id: number) : Observable<PurchaseOrder> {
    return this.http.get<PurchaseOrder>(`${this.url}/${id}`);
  }

  getByProvider(id: number) : Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.url}/provider/${id}`);
  }

  getByActive() : Observable<PurchaseOrder[]> { // Traigo solo las activas
    return this.http.get<PurchaseOrder[]>(`${this.url}/active`);
  }

  getNewNumOrder() : Observable<string>{ // Traigo el numero de orden correspondiente.
    return this.http.get<string>(`${this.url}/num-purchase-order`, { responseType: 'text' as 'json' });
  }

  post(purchasOrder: PurchaseOrder) : Observable<PurchaseOrder>{
    return this.http.post<PurchaseOrder>(this.url, purchasOrder);
  }

  put(purchasOrder: PurchaseOrder) : Observable<PurchaseOrder>{
    return this.http.put<PurchaseOrder>(`${this.url}/${purchasOrder.id}`, purchasOrder);
  }

  cancel(id: number) : Observable<PurchaseOrder>{
    return this.http.delete<PurchaseOrder>(`${this.url}/cancel/${id}`);
  }

  activate(id: number) : Observable<PurchaseOrder>{
    return this.http.delete<PurchaseOrder>(`${this.url}/activate/${id}`);
  }
}
