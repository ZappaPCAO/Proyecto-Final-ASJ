import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Provincia } from '../models/provincias';

@Injectable({
  providedIn: 'root'
})
export class ProvLocArgentinasService {
  private apiUrl = 'https://apis.datos.gob.ar/georef/api/localidades-censales?aplanar&max=5000';
  private _provincias: Provincia[] = [];

  public get provincias() : Provincia[] {
    return this._provincias;
  }

  obtenerLocalidades(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  private loadProvincias(){
    this.obtenerLocalidades().subscribe((res) => {
      for (const localidad of res.localidades_censales) {
        if(localidad.nombre == null)
          continue;
        
        const provincia = this.provincias.find((provincia: any) => provincia.id === localidad.provincia_id);
        
        if (provincia) {
          const localidadExistente = provincia.localidades.find((loc: any) => loc.id === localidad.provincia_id);
          if(!localidadExistente){
            provincia.localidades.push({
              id: localidad.id,
              nombre: localidad.nombre,
            });
          }          
        } else {
          this.provincias.push({
            id: localidad.provincia_id,
            nombre: localidad.provincia_nombre,
            localidades: [{
              id: localidad.id,
              nombre: localidad.nombre,
            }],
          });
        }        
      }
      console.log(this.provincias);
    });
  }

  constructor(private http: HttpClient) {
    this.loadProvincias();
  }
}
