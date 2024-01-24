import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Pais } from '../models/pais';
import { Provincia } from '../models/provincias';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {

  // constructor(private http: HttpClient) {
  //   this.cargarPaises();
  // }
  // pais!: Pais[];
  // url: string = `../../assets/json/`;

  // cargarPaises(){
  //   this.http.get(this.url + 'countries.json').subscribe((res: any) => {
  //     this.pais.push({
  //       id: res.id,
  //       nombre: res.name,
  //       provincias: this.cargarProvincias(res.id),
  //     })
  //   });
  //   console.log(this.pais);
  // }
  // cargarProvincias(id:any){
  //   let provincias!: Provincia[];

  //   this.http.get(this.url + 'states.json').subscribe((res: any) => {
  //     provincias = res.filter( (r:any) => r.country_id == id ).map(
  //       (prov: any) => ({ id: prov.id, nombre: prov.name } as Provincia)
  //     )
  //   })
  //   return provincias;
  // }
  // cargarLocalidades(){

  // }
}