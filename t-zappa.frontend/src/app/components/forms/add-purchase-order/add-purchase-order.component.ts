import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Detalle, PurchaseOrder } from '../../../models/purchase-order';
import { PurchaseOrderService } from '../../../services/purchase-order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Provider } from '../../../models/provider';
import { Article } from '../../../models/article';
import { formatDate } from '../../../utils/formatoFecha';
import { NgForm } from '@angular/forms';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import Swal from 'sweetalert2';

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
  indexProv: number = 0;
  idPurchaseOrder: number = 0;

  agregarDetalle() {
    const detalle: Detalle = this.purchaseOrder.detalle.find(deta => deta.articulo.id == this.idArticulo)!;
    const proveedor: Provider = this.arrProviders.find(provi => provi.id == this.idProveedor)!;
    const articulo: Article = this.arrArticles.find(arti => arti.id == this.idArticulo)!;

    if(detalle && articulo){
      detalle.cantidad += this.cantidad;
      this.purchaseOrder.total += (this.cantidad * articulo.price);
      detalle.subtotal = detalle.cantidad * articulo.price;
    }else{
      const newDetalle: Detalle = {
        proveedor: proveedor,
        articulo: articulo,
        cantidad: this.cantidad,
        subtotal: this.cantidad * articulo.price,
      }
      if(newDetalle){
        this.purchaseOrder.detalle.push(newDetalle);
        this.purchaseOrder.total += newDetalle.subtotal;
      }
    }
    Swal.fire({
      position: "bottom-end",
      icon: 'success',      
      title: "Agregado al detalle!",
      showConfirmButton: false,
      timer: 1000
    });

    this.idArticulo = 0; // Limpio el select
  }

  eliminarDetalle(detalle: any) {
    this.purchaseOrder.detalle = this.purchaseOrder.detalle.filter(d => d !== detalle);
  }

  constructor(private router: Router, private route: ActivatedRoute,
    private providerService: ProviderService, private articleService: ArticleService, private purchaseOrderService: PurchaseOrderService){}
  
  verificarUpdate(){
    if(this.idPurchaseOrder > 0){      
      this.purchaseOrder = this.purchaseOrderService.getById(this.idPurchaseOrder);
      this.idProveedor = this.purchaseOrder.detalle[0].proveedor.id || 0; // verificar luego
    }
  }

  agregarPurchaseOrder(form: NgForm){
    if( form.valid && 
      ( verificarDatos(this.purchaseOrder) && // que no hay ningun caracter raro
        verificarLongitudes(this.purchaseOrder) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.purchaseOrder) ) ){ // controlo campos especificos

        if(this.idPurchaseOrder === 0){ // 0 => Nuevo ; >0 => Edito
          this.purchaseOrderService.post(this.purchaseOrder);
        }else{
          this.purchaseOrderService.put(this.purchaseOrder);
        }
        Swal.fire({
          title: "¿Desea crear otro?",          
          icon: 'success',
          timer: 2500,       
          showCancelButton: true, 
          confirmButtonColor: "var(--color-primary)",
          cancelButtonColor: "var(--color-secondary)",          
          confirmButtonText: "Si",
          cancelButtonText: "No"
        }).then((result) => {
          if (result.isConfirmed) {
            form.reset();
          }else{
            this.router.navigate(['purchase-order', "list"]); 
          }
        });
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  updateArticles() {
    //this.arrArticles = this.articleService.get().filter(article => article.proveedor.id == this.idProveedor); 
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.idPurchaseOrder = params['id'] || 0;      
      // this.arrProviders = this.providerService.get(); //get ACA VER XQ CAMBIE EL SERVICIO
      //this.arrArticles = this.articleService.get(); //get ACA VER PQ CAMBIE EL SERVICIO
      this.purchaseOrder.fecEmision = formatDate(new Date());

      this.verificarUpdate();     
    });
  }
}
