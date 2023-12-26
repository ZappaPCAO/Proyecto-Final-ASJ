import { Injectable } from '@angular/core'; 
import { providers, Provider } from '../models/provider';

const dataProviders = providers || [];

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor() { }

  get(){
    return dataProviders;
  }

  getById(id: number): Provider {
    let provider!: Provider;

    if(dataProviders.length > 0){
      provider = dataProviders.find( provider => provider.id == id )!; 
    }

    return provider
  }

  post(provider: Provider){

    provider.id = (dataProviders && dataProviders.length > 0) ? dataProviders[dataProviders.length-1].id + 1 : 1; // Controlo la id
    
    dataProviders.push(provider);

    console.log(dataProviders);
  }

  put(provider: Provider){
    let auxProvider!: Provider;

    auxProvider = dataProviders.find(provi => provi.id = provider.id)!;
    
    auxProvider = provider;
  }

  delete(provider: Provider){

  }
}
