import { Injectable } from '@angular/core'; 
import { Provider } from '../models/provider';
import { agregarObjetoSiExiste, pisarDatosByTipo } from '../utils/localStorage';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  // private dataProviders = providers || [];

  private url = 'http://localhost:8080/proveedores';

  constructor(private http: HttpClient) { }

  get() : Observable<Provider[]>{
    return this.http.get<Provider[]>(this.url);
    // return this.dataProviders.sort((a:Provider,b:Provider) => a.razSocial.localeCompare(b.razSocial));
  }

  getById(id: number) : any {
    // let provider!: Provider;

    // if(this.dataProviders.length > 0){
    //   provider = this.dataProviders.find( provider => provider.id == id )!; 
    // }

    return null;
  }

  post(provider: Provider) : Observable<Provider>{
    // provider.id = (this.dataProviders && this.dataProviders.length > 0) ? this.dataProviders[this.dataProviders.length-1].id + 1 : 1; // Controlo la id

    // this.dataProviders.push(provider);
    
    // pisarDatosByTipo('provider', this.dataProviders);

    return this.http.post<Provider>(this.url, provider);
  }

  put(provider: Provider){
    let auxProvider!: Provider;

    // auxProvider = this.dataProviders.find(provi => provi.id = provider.id)!;
    
    auxProvider = provider;

    agregarObjetoSiExiste('provider', provider);
  }

  delete(provider: Provider){
    let index: number;
    // index = this.dataProviders.findIndex(provi => provi.id === provider.id);
    
    // this.dataProviders.splice(index, 1);

    // pisarDatosByTipo('provider', this.dataProviders);
  }
}
