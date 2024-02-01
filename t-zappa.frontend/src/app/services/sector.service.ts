import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sector } from '../models/sector';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SectorService {
  private url = "http://localhost:8080/sectors";

  constructor(private http: HttpClient) {}
  
  getSectors() : Observable<Sector[]>{
    return this.http.get<Sector[]>(this.url);
  }
}
