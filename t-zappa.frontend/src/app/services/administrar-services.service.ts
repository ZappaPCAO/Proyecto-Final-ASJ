import { Injectable } from '@angular/core';
import { ProviderService } from './provider.service';
import { ArticleService } from './article.service';
import { PurchaseOrderService } from './purchase-order.service';
import { Provider } from '../models/provider';
import { Article } from '../models/article';
import { PurchaseOrder } from '../models/purchase-order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdministrarServicesService {

  constructor(private serviceProvider: ProviderService,
    private serviceArticle: ArticleService,
    private servicePurchaseOrder: PurchaseOrderService) { }

    // Administro los get por medio de parametros.
    get(tipo: string) : Observable<Provider[] | Article[] | PurchaseOrder[]>{

      switch (tipo) {        
        case 'article':
          return this.serviceArticle.get();
          break;
        case 'provider':
          return this.serviceProvider.get();
          break;
        case 'purchase-order':
          // datos = this.servicePurchaseOrder.get();
          break;

        default:
          break;
      }
      return new Observable();
    }
    
    getBySector(id:number, tipo: string) : Observable<Provider[] | Article[] | PurchaseOrder[]>{

      switch (tipo) {        
        case 'article':
          // return <Article[]>this.serviceArticle.get();
          break;
        case 'provider':
          return this.serviceProvider.getBySector(id);
          break;
        case 'purchase-order':
          // datos = this.servicePurchaseOrder.get();
          break;

        default:
          break;
      }
      return new Observable();
    }

    rescue(id:number, tipo:string) : Observable<Provider | Article | PurchaseOrder>{
      

      switch (tipo) {        
        case 'article':
          return this.serviceArticle.rescue(id);
          break;
        case 'provider':
          return this.serviceProvider.rescue(id);
          break;
        case 'purchase-order':
          
          break;

        default:
          break;
      }
      return new Observable();
    }

    delete(id:number, tipo:string) : Observable<Provider | Article | PurchaseOrder>{

      switch (tipo) {        
        case 'article':
          return this.serviceArticle.delete(id);
          break;
        case 'provider':
          return this.serviceProvider.delete(id);
          break;
        case 'purchase-order':
          
          break;

        default:
          break;
      }
      return new Observable();
    }

    put(dato:any, tipo:string){
      console.log(JSON.stringify(dato,null,1) + 'holasdsadas' + tipo)
      switch (tipo) {        
        case 'article':
          this.serviceArticle.put(dato);
          break;
        case 'provider':
          this.serviceProvider.put(dato);
          break;
        case 'purchase-order':
          this.servicePurchaseOrder.put(dato)
          break;

        default:
          break;
      }
    }
}
