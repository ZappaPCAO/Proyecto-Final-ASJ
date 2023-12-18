import { Injectable } from '@angular/core';
import { providers } from '../data/provider';

const dataProviders = providers;

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor() { }

  get(){
    return dataProviders;
  }

  post(provider: any){
    console.log(provider);
    dataProviders.push(provider);
    console.log(dataProviders);
  }
}
