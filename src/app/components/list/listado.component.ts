import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { ProviderService } from '../../services/provider.service';
import { PurchaseOrderService } from '../../services/purchase-order.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'listado',
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent implements OnInit{
  thead: any = [];tbody: any = [];
  condicion!: string;

  constructor(private route: ActivatedRoute, private router: Router, 
    private articleService: ArticleService, private providerService: ProviderService, private purchaseOrderService: PurchaseOrderService){
  }

  generarArreglos(){
    this.tbody = (this.condicion === 'articles') ? this.articleService.get() : //podria crear un servicio q los maneje
                 (this.condicion === 'providers') ? this.providerService.get() : this.purchaseOrderService.get();

    if(this.tbody && this.tbody.length > 0)
      this.thead = Object.keys(this.tbody[0]).filter(key => key !== 'id'); // Obtengo las claves, excepto id. 
  }                                     // Ya q no lo quiero mostrar

  onEdit(soyAlgo: any){
    let tipo = (this.condicion === 'articles') ? 'article/update-article' :
               (this.condicion === 'providers') ? 'provider/update-provider' : 'purchase-order/update-purchase-order'

    this.router.navigate([tipo, soyAlgo.id]);
  }
  onDelete(soyAlgo: any){
    // Preguntar si esta seguro que desea eliminar el proveedor etc 

    
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.condicion = params['tipo']);
    console.log(`condicion ${this.condicion}`);
    this.generarArreglos();
  }

}
