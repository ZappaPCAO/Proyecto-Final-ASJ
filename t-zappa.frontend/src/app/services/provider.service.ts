import { Injectable } from '@angular/core'; 
import { Provider } from '../models/provider';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  private url = 'http://localhost:8080/providers';

  constructor(private http: HttpClient) { }

  get() : Observable<Provider[]>{
    return this.http.get<Provider[]>(this.url);
  }

  getById(id: number) : Observable<Provider> {
    return this.http.get<Provider>(`${this.url}/${id}`);
  }

  getBySector(id: number) : Observable<Provider[]> {
    return this.http.get<Provider[]>(`${this.url}/sector/${id}`);
  }

  getByActives() : Observable<Provider[]> {
    return this.http.get<Provider[]>(`${this.url}/actives`);
  }

  getWithMostOrders() : Observable<Provider> {
    return this.http.get<Provider>(`${this.url}/purchase-orders/top-1`);
  }

  post(provider: Provider) : Observable<Provider>{
    return this.http.post<Provider>(this.url, provider);
  }

  put(provider: Provider) : Observable<Provider>{
    return this.http.put<Provider>(`${this.url}/${provider.id}`, provider);
  }

  delete(id: number) : Observable<Provider>{
    return this.http.delete<Provider>(`${this.url}/delete/${id}`);
  }

  rescue(id: number) : Observable<Provider> {
    return this.http.delete<Provider>(`${this.url}/rescue/${id}`);
  }
}
