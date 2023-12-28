import { Injectable } from '@angular/core'; 
import { providers, Provider } from '../models/provider';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  private dataProviders = providers || [];

  constructor() { }

  get(){
    return this.dataProviders;
  }

  getById(id: number): Provider {
    let provider!: Provider;

    if(this.dataProviders.length > 0){
      provider = this.dataProviders.find( provider => provider.id == id )!; 
    }

    return provider
  }

  post(provider: Provider){

    provider.id = (this.dataProviders && this.dataProviders.length > 0) ? this.dataProviders[this.dataProviders.length-1].id + 1 : 1; // Controlo la id
    
    console.log("idddddd " + provider.id);

    this.dataProviders.push(provider);

    console.log(this.dataProviders);
  }

  put(provider: Provider){
    let auxProvider!: Provider;

    auxProvider = this.dataProviders.find(provi => provi.id = provider.id)!;
    
    auxProvider = provider;
  }

  delete(provider: Provider){
    let index: number;
    index = this.dataProviders.findIndex(provi => provi.id === provider.id);
    
    this.dataProviders.splice(index, 1);
  }
}
