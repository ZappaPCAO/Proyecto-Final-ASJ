import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { PurchaseOrder } from '../../../models/purchase-order';
import { PurchaseOrderService } from '../../../services/purchase-order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrl: './add-purchase-order.component.css'
})
export class AddPurchaseOrderComponent implements OnInit {
  purchaseOrder: PurchaseOrder = {
    id: 0, nroOC: '', fecEmi: '', fecRecep: '', email: '', proveedor: '', articulo: '', cantidad: 0,
  }
  arrProviders: string[] = []; arrArticles: string[] = [];

  constructor(private router: Router,
    private providerService: ProviderService, private articleService: ArticleService, private purchaseOrderService: PurchaseOrderService){}

  ngOnInit(): void {
    for (const provedor of this.providerService.get()) {
      this.arrProviders.push(provedor.nombre);
    }
    for (const article of this.articleService.get()) {
      this.arrArticles.push(article.producto);
    }
  }

  agregarPurchaseOrder(){
    this.purchaseOrderService.post(this.purchaseOrder);
    this.router.navigate(['purchase-order/list', "purchase-orders"]);
  }
}
