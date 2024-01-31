import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Country } from '../models/geoLocation';
import { IvaCondition } from '../models/ivaCondition';

@Injectable({
  providedIn: 'root'
})
export class InicializadorService {

  private url = "http://localhost:8080/";

  constructor(private http: HttpClient) {}
  
  getGeoLocations() : Observable<Country[]>{
    return this.http.get<Country[]>(`${this.url}geoLocations`);
  }
  getIvaConditions() : Observable<IvaCondition[]> {
    return this.http.get<IvaCondition[]>(`${this.url}iva-condition`);
  }
}