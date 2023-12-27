import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Detalle, PurchaseOrder } from '../../../models/purchase-order';
import { PurchaseOrderService } from '../../../services/purchase-order.service';
import { Router } from '@angular/router';
import { Provider } from '../../../models/provider';
import { Article } from '../../../models/article';
import { formatDate } from '../../../utils/formatoFecha';

@Component({
  selector: 'add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrl: './add-purchase-order.component.css'
})
export class AddPurchaseOrderComponent implements OnInit {
  
  purchaseOrder: PurchaseOrder = {
    id: 0,
    nroOC: '',
    fecEmision: '',
    fecEntrega: '',
    email: '',
    descri: '',
    detalle: [],
    estado: 'activa',
    total: 0,
  };
  
  arrProviders!: Provider[]; arrArticles!: Article[]; // arreglos para los select
  cantidad: number = 0; idProveedor: number = 0; idArticulo: number = 0;

  agregarDetalle() {
    const proveedor: any = this.arrProviders.find(provi => provi.id == this.idProveedor);
    const articulo: any = this.arrArticles.find(arti => arti.id == this.idArticulo);

    const newDetalle: Detalle = {
      proveedor: proveedor,
      articulo: articulo,
      cantidad: this.cantidad,
      subtotal: this.cantidad * articulo.precio,
    }
    if(newDetalle){
      this.purchaseOrder.detalle.push(newDetalle);
      this.purchaseOrder.total += newDetalle.subtotal;
    }
    
  }

  eliminarDetalle(detalle: any) {
    this.purchaseOrder.detalle = this.purchaseOrder.detalle.filter(d => d !== detalle);
  }

  constructor(private router: Router,
    private providerService: ProviderService, private articleService: ArticleService, private purchaseOrderService: PurchaseOrderService){}

  agregarPurchaseOrder(){
    this.purchaseOrderService.post(this.purchaseOrder);
    this.router.navigate(['purchase-order/list', "purchase-orders"]);
  }

  ngOnInit(): void {
    this.arrProviders = this.providerService.get();
    this.arrArticles = this.articleService.get();
    this.purchaseOrder.fecEmision = formatDate(new Date());
  }
}
