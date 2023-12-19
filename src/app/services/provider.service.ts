import { Injectable } from '@angular/core';
import { providers } from '../data/provider';

const dataProviders = providers || [];

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor() { }

  get(){
    return dataProviders;
  }

  post(provider: any){

    provider.id = (dataProviders && dataProviders.length > 0) ? dataProviders[dataProviders.length-1].id + 1 : 1; // Controlo la id
    
    dataProviders.push(provider);

    console.log(dataProviders);
  }
}
