import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { PurchaseOrderService } from '../../../services/purchase-order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Provider } from '../../../models/provider';
import { Article } from '../../../models/article';
import { formatDate, formatDateTime } from '../../../utils/formatoFecha';
import { NgForm } from '@angular/forms';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import Swal from 'sweetalert2';
import { PurchaseOrder } from '../../../models/purchase-order';
import { Detail } from '../../../models/detail';

@Component({
  selector: 'add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrl: './add-purchase-order.component.css'
})
export class AddPurchaseOrderComponent implements OnInit {
  purchaseOrder: PurchaseOrder = {
    id: 0,
    numPurchaseOrder: 0,
    sendDate: '',
    receiptDate: '',
    email: '',
    description: '',
    details: [],
    state: 'A',
    total: 0,
    provider: {
      id: 0,
      codProvider: '',
      businessName: '',
      website: '',
      email: '',
      phone: '',
      logo: '',
      sector:{
        id: 0,
        sector: ''
      },
      location: {
        id: 0,
        street: '',
        number: 0,
        postalCode: '',
        city: {
          id: 0,
          name: '',
          state:{
            id: 0,
            name: '',
            country: {
              id: 0,
              name: ''
            }
          }
        }      
      },
      taxData: {
        id: 0,
        cuit: '',
        ivaCondition: {
          id: 0,
          condition: ''
        },
      },
      contactData: {
        id: 0,
        name: '',
        lastName: '',
        phone: '',
        email: '',
        role: '',
      }
    }
  };
  
  providers: Provider[] = []; articles: Article[] = []; // arreglos para los select
  amount: number = 0; idArticle: number = 0;
  indexProv: number = 0;
  idPurchaseOrder: number = 0;

  constructor(private router: Router, private route: ActivatedRoute,
    private providerService: ProviderService, private articleService: ArticleService, 
    private purchaseOrderService: PurchaseOrderService){}

  private calcularTotal(){
    this.purchaseOrder.total = this.purchaseOrder.details.reduce((total, detail) => total + detail.subtotal, 0);
  }

  agregarDetalle() {
    const detail: Detail = this.purchaseOrder.details.find(detail => detail.article.id == this.idArticle)!;   
    const article: Article = this.articles.find(arti => arti.id == this.idArticle)!;

    if(this.purchaseOrder.provider){
      this.purchaseOrder.provider = this.providers.find(provider => provider.id == this.purchaseOrder.provider.id)!;
    }

    if(detail && article){
      detail.amount += this.amount;
      detail.subtotal = detail.amount * article.price;
    }else{
      const newDetail: Detail = {        
        article: article,
        amount: this.amount,
        subtotal: this.amount * article.price,
      }
      if(newDetail){
        this.purchaseOrder.details.push(newDetail);
      }
    }
    Swal.fire({
      position: "bottom-end",
      icon: 'success',      
      title: "Agregado al detalle!",
      showConfirmButton: false,
      timer: 1000
    });
    this.calcularTotal();
    this.idArticle = 0; // Limpio el select
  }

  eliminarDetalle(detalle: any) {
    this.purchaseOrder.details = this.purchaseOrder.details.filter(d => d !== detalle);

    this.calcularTotal();
  }
  
  verificarUpdate(){
    // if(this.idPurchaseOrder > 0){      
    //   this.purchaseOrderService.getById(this.idPurchaseOrder).subscribe((data : PurchaseOrder) => {
    //     this.purchaseOrder = data;
    //   });
    // }
  }

  agregarPurchaseOrder(form: NgForm){
    console.log("Esta es la orden: " + JSON.stringify(this.purchaseOrder));
    if( form.valid && 
      ( verificarDatos(this.purchaseOrder) && // que no hay ningun caracter raro
        verificarLongitudes(this.purchaseOrder) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.purchaseOrder) ) ){ // controlo campos especificos

        if(this.idPurchaseOrder === 0){ // 0 => Nuevo ; >0 => Edito
          this.purchaseOrder.sendDate = new Date(this.purchaseOrder.sendDate).toISOString();
          this.purchaseOrder.receiptDate = new Date(this.purchaseOrder.receiptDate).toISOString();
          this.purchaseOrderService.post(this.purchaseOrder).subscribe((data : PurchaseOrder) => {
            this.purchaseOrder = data;
          }, (error) => {
            Swal.fire({
              position: "bottom-end",
              icon: "error",
              title: `Hubo un problema en la creacion: ${error.get}`,
              showConfirmButton: false,
              timer: 1500
            });
          }, () => {
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
          });
        }
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  updateArticles() {
    this.articleService.getByProvider(this.purchaseOrder.provider.id).subscribe((data : Article[]) => {
      this.articles = data; // ver xq no me va a traer los activos.
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.idPurchaseOrder = params['id'] || 0;
        this.providerService.getByActives().subscribe( (data : Provider[]) => {
          this.providers = data;
          this.articleService.getByActives().subscribe( (data : Article[]) => {
            this.articles = data;

            this.purchaseOrder.sendDate = formatDate(new Date());
            this.verificarUpdate();   
          })
        });        
    });
  }
}
