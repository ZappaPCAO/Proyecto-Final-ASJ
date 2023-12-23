import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProvLocArgentinasService {
  private apiUrl = 'https://apis.datos.gob.ar/georef/api/localidades-censales?aplanar&max=5000';

  constructor(private http: HttpClient) {}

  obtenerLocalidades(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
