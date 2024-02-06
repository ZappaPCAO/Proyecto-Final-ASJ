import { Injectable } from '@angular/core';
import { ProviderService } from './provider.service';
import { ArticleService } from './article.service';
import { PurchaseOrderService } from './purchase-order.service';
import { Provider } from '../models/provider';
import { Article } from '../models/article';
import { PurchaseOrder } from '../models/purchase-order';
import { Observable } from 'rxjs';
import { CategoryService } from './category.service';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class AdministrarServicesService {

  constructor(private serviceProvider: ProviderService,
    private serviceArticle: ArticleService,
    private servicePurchaseOrder: PurchaseOrderService,
    private serviceCategory: CategoryService) { }

    // Administro los get por medio de parametros.
    get(tipo: string) : Observable<Provider[] | Article[] | PurchaseOrder[] | Category[]>{

      switch (tipo) {        
        case 'article':
          return this.serviceArticle.get();
          break;
        case 'provider':
          return this.serviceProvider.get();
          break;
        case 'purchase-order':
          return this.servicePurchaseOrder.get();
          break;
        case 'category':
          return this.serviceCategory.get();
          break;
        default:
          break;
      }
      return new Observable();
    }
    
    getBy(id:number, tipo: string) : Observable<Provider[] | Article[] | PurchaseOrder[]>{

      switch (tipo) {        
        case 'category':
          return this.serviceArticle.getByCategory(id);
          break;
        case 'sector':
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

    rescue(id:number, tipo:string) : Observable<Provider | Article | PurchaseOrder | Category>{
      switch (tipo) {        
        case 'article':
          return this.serviceArticle.rescue(id);
          break;
        case 'provider':
          return this.serviceProvider.rescue(id);
          break;
        case 'purchase-order':
          return this.servicePurchaseOrder.activate(id);
          break;
        case 'category':
          return this.serviceCategory.rescue(id);
          break;

        default:
          break;
      }
      return new Observable();
    }

    delete(id:number, tipo:string) : Observable<Provider | Article | PurchaseOrder | Category>{

      switch (tipo) {        
        case 'article':
          return this.serviceArticle.delete(id);
          break;
        case 'provider':
          return this.serviceProvider.delete(id);
          break;
        case 'purchase-order':
          return this.servicePurchaseOrder.cancel(id);
          break;
        case 'category':
          return this.serviceCategory.delete(id);
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
