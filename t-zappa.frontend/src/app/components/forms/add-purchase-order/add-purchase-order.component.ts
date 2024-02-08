import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { PurchaseOrderService } from '../../../services/purchase-order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Provider } from '../../../models/provider';
import { Article } from '../../../models/article';
import { formatDate } from '../../../utils/formatDate';
import { NgForm } from '@angular/forms';
import { checkSpecialCharacters, checkOnlyNumbers, isCuit, isEmail, isPhoneNumber, isWebsite, checkLongsPurchaseOrder, checkDataPurchaseOrder } from '../../../utils/validates';
import Swal from 'sweetalert2';
import '@sweetalert2/theme-dark/dark.css';
import { PurchaseOrder } from '../../../models/purchase-order';
import { Detail } from '../../../models/detail';

@Component({
  selector: 'add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrl: './add-purchase-order.component.css'
})
export class AddPurchaseOrderComponent implements OnInit {
  purchaseOrder!: PurchaseOrder;
  
  providers: Provider[] = []; articles: Article[] = []; // arreglos para los select
  amount: number = 1;
  idPurchaseOrder: number = 0; idArticle: number = 0; idProvider: number = 0;
  checkSpecialCharacters = checkSpecialCharacters; checkOnlyNumbers = checkOnlyNumbers;
  isCuit = isCuit; isEmail = isEmail; isPhoneNumber = isPhoneNumber; isWebsite = isWebsite;
  show: boolean = false; showRequired = false;
  
  constructor(private router: Router, private route: ActivatedRoute,
    private providerService: ProviderService, private articleService: ArticleService, 
    private purchaseOrderService: PurchaseOrderService){
      this.initializePurchaseOrder();
    }

  private initializePurchaseOrder(){
    this.purchaseOrder = {
      id: 0,
      numPurchaseOrder: '',
      sendDate: '',
      receiptDate: '',
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
    }
  }

  private calcularTotal(){
    this.purchaseOrder.total = this.purchaseOrder.details.reduce((total, detail) => total + detail.subtotal, 0);
  }

  agregarDetalle() {    
    const detail: Detail = this.purchaseOrder.details.find(detail => detail.article.id == this.idArticle)!;   
    const article: Article = this.articles.find(arti => arti.id == this.idArticle)!;
    this.purchaseOrder.provider = this.providers.find(provider => provider.id == this.idProvider)!;

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

  eliminarDetalle(detail: Detail) {
    this.purchaseOrder.details = this.purchaseOrder.details.filter(d => d !== detail);
    this.calcularTotal();
  }
  
  changeImage(event: Event): void {
    const imagen = event.target as HTMLImageElement;
    imagen.src = 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/832px-No-Image-Placeholder.svg.png';
  }

  agregarPurchaseOrder(form: NgForm){
    if(form.invalid){
      this.showRequired = true;
      return;
    }

    this.show = !( checkDataPurchaseOrder(this.purchaseOrder) && checkLongsPurchaseOrder(this.purchaseOrder) );

    console.log("Test 1-");

    if(this.purchaseOrder.details.length < 1){
      console.log("Test 2-");
      Swal.fire({
        title: "No puede crear una orden de compra sin tener un detalle",
        text: "Seleccione un producto y una cantidad, luego en el boton '+' para agregar.",
        icon: "warning"
      });
      return;
    }

    if( form.valid && !this.show ){ // controlo campos especificos
        if(this.idPurchaseOrder === 0){ // 0 => Nuevo ; >0 => Edito  
          this.purchaseOrder.sendDate = new Date(this.purchaseOrder.sendDate).toISOString();
          this.purchaseOrder.receiptDate = new Date(this.purchaseOrder.receiptDate).toISOString();        
          this.purchaseOrderService.post(this.purchaseOrder).subscribe(data =>{            
            this.purchaseOrder = data;
          },(error) => {
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
              if (result.isConfirmed){  // Si quiere cargar otro formateo los datos.
                this.idArticle = 0;
                this.idProvider = 0;      
                this.amount = 1;     
                this.initializePurchaseOrder();                                  
                form.reset();
                this.purchaseOrderService.getNewNumOrder().subscribe((data : string) => {
                  this.purchaseOrder.numPurchaseOrder = data;              
                  this.purchaseOrder.sendDate = formatDate(new Date());               
                });
              }else{
                this.router.navigate(['purchase-order', 'list']);
              }
            });
          });
        }    
    }
  }

  updateArticles() {  
    this.articleService.getByProviderActives(this.idProvider).subscribe((data : Article[]) => {
      this.articles = data;   
      this.idArticle = 0;
    });    
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.idPurchaseOrder = params['id'] || 0;
        this.providerService.getByActives().subscribe( (data : Provider[]) => {
          this.providers = data;
          this.articleService.getByActives().subscribe( (data : Article[]) => {
            this.articles = data;
            this.purchaseOrderService.getNewNumOrder().subscribe((data : string) => {
              this.purchaseOrder.numPurchaseOrder = data;              
              this.purchaseOrder.sendDate = formatDate(new Date());               
            });            
          })
        });        
    });
  }
}
