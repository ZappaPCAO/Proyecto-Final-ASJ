import { Injectable } from '@angular/core';
import { ProviderService } from './provider.service';
import { ArticleService } from './article.service';
import { PurchaseOrderService } from './purchase-order.service';
import { Provider } from '../models/provider';
import { Article } from '../models/article';
import { PurchaseOrder } from '../models/purchase-order';

@Injectable({
  providedIn: 'root'
})
export class AdministrarServicesService {

  constructor(private serviceProvider: ProviderService,
    private serviceArticle: ArticleService,
    private servicePurchaseOrder: PurchaseOrderService) { }

    // Administro los get por medio de parametros.
    get(tipo: string){
      let datos!: Provider[] | Article[] | PurchaseOrder[];

      switch (tipo) {        
        case 'article':
          datos = this.serviceArticle.get();
          break;
        case 'provider':
          datos = this.serviceProvider.get()
          break;
        case 'purchase-order':
          datos = this.servicePurchaseOrder.get()
          break;

        default:
          break;
      }
      return datos;
    }

    delete(dato:any, tipo:string){
      switch (tipo) {        
        case 'article':
          this.serviceArticle.delete(dato);
          break;
        case 'provider':
          this.serviceProvider.delete(dato);
          break;
        case 'purchase-order':
          this.servicePurchaseOrder.delete(dato)
          break;

        default:
          break;
      }
    }
}
