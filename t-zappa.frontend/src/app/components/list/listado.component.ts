import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdministrarServicesService } from '../../services/administrar-services.service';
import { Provider } from '../../models/provider';
import { Article } from '../../models/article';
import { PurchaseOrder } from '../../models/purchase-order';
import Swal from 'sweetalert2';
import '@sweetalert2/theme-dark/dark.css';
import { Sector } from '../../models/sector';
import { Category } from '../../models/category';


@Component({
  selector: 'listado',
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent implements OnInit{

  thead: any = [];tbody: any = [];
  condicion: string = '';
  rightPanelStyle: any;
  currentRecord!:  Provider | Article | PurchaseOrder | any;
  sectors: Sector[] = []; categories: Category[] = []; providers: Provider[] = [];
  filtro: number = 0;
  filter: string= "";
  filtroBaja: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, 
    private serivicioAdm: AdministrarServicesService){     
  }

  detectRightMouseClick($event: any, el: Provider | Article | PurchaseOrder) {
    if ($event.which === 3) {
      // Ajusta la posición del menú respecto a la ventana del navegador
      this.rightPanelStyle = {
        'display': 'block',               
        'left.px': $event.clientX,
        'top.px': $event.clientY,      
      };
      this.currentRecord = el;
    }else{
      this.closeContextMenu();
    }
  }
  
  closeContextMenu() {
    this.rightPanelStyle = {
      display: 'none'
    };
  }
  
  onInfo() {
    console.log(this.currentRecord);
  }

  onAdd() {
    this.sinInfo();
  }

  changeImage(event: Event): void {
    const imagen = event.target as HTMLImageElement;
    imagen.src = 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/832px-No-Image-Placeholder.svg.png';
  }

  generarArreglos(){
    this.serivicioAdm.get(this.condicion).subscribe( (data: Provider[] | Article[] | PurchaseOrder[] | Category[] | Sector[]) => {
      this.tbody = data;
      
      if(this.tbody && this.tbody.length > 0){
      
        this.thead = (this.condicion === 'article')  ? ['image','codArticle','category','price','provider'] :
                     (this.condicion === 'provider') ? ['logo','codProvider','businessName', 'location', 'contactData'] : 
                     (this.condicion === 'purchase-order') ? ['numPurchaseOrder','sendDate','receiptDate', 'provider', 'state', 'total'] : ['name'];
      }else{
        this.sinInfo();
      }      
    });
  }                                                                        
  
  onEdit(){
    this.router.navigate([this.condicion, this.currentRecord.id]);
    this.closeContextMenu();
  }

  onRecuperar(){
    this.serivicioAdm.rescue(this.currentRecord.id, this.condicion).subscribe( (data : Provider | Article | PurchaseOrder | Category) => {
          
      let index = this.tbody.findIndex((item: Provider | Article | PurchaseOrder) => item.id === this.currentRecord.id);

      if (index !== -1){
        Object.assign(this.tbody[index], data);
        Swal.fire({
          position: "bottom-end",
          icon: "success",
          title: "Recuperado correctamente!",
          showConfirmButton: false,
          timer: 1500
        });
        this.closeContextMenu();
      }     
    });      
  }

  onDelete(){
    Swal.fire({
      title: "¿Estas seguro?",
      text: `¡Proveedor: ${this.currentRecord.businessName}, sera eliminado!`,   // VER ACA   
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonColor: "var(--color-primary)",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminaló!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.serivicioAdm.delete(this.currentRecord.id, this.condicion).subscribe( (data : Provider | Article | PurchaseOrder | Category) => {
          
          let index = this.tbody.findIndex((item: Provider | Article | PurchaseOrder) => item.id === this.currentRecord.id);

          if (index !== -1)
            Object.assign(this.tbody[index], data);          
          
        });        
        Swal.fire({
          position: "bottom-start",
          icon: "success",
          title: "Eliminado con exito",
          showConfirmButton: false,
          timer: 1500
        });
        this.closeContextMenu();
      }
    });
  }

  updateList(tipo:string){
    this.serivicioAdm.getBy(this.filtro, tipo).subscribe( (data : Provider[] | Article[] | PurchaseOrder[]) => {
      this.tbody = data;

      if(this.tbody.length == 0){ // Me fijo si encontro coincidencias         
          console.log("deberia mostrar el cartel!")
          Swal.fire({
            title: "No se econtraron registros para el filtro aplicado!",
            icon: "warning",
            showCloseButton: true,        
            showConfirmButton: false,
          });        
      }
    });
  }

  sinInfo(){
    Swal.fire({
      title: "¡No hay nada para mostrar!",
      html: "Sera redirigido",
      allowOutsideClick: false,
      allowEscapeKey: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: () => {
        Swal.showLoading();        
      }
    }).then((result) => {      
      if (result.dismiss === Swal.DismissReason.timer) {
        this.router.navigate([this.condicion]);
      }
    });
  }

  ngOnInit(): void {
    this.route.data.subscribe(data => {

      if(data && data['tipo']){       
        this.condicion = data['tipo'];        
          this.generarArreglos();
      }else{
        this.router.navigate(['']); // Lo mando al home
      }

      if(this.condicion === 'provider'){
        this.serivicioAdm.get('sector').subscribe((data : Provider[] | Article[] | PurchaseOrder[] | Category[] | Sector[]) => {
          this.sectors = data as Sector[];
        });
      }else if(this.condicion === 'article'){
        this.serivicioAdm.get('category').subscribe((data: Provider[] | Article[] | PurchaseOrder[] | Category[] | Sector[]) => {
          this.categories = data as Category[];          
        });
      }else if(this.condicion === 'purchase-order'){
        this.serivicioAdm.get('provider').subscribe((data: Provider[] | Article[] | PurchaseOrder[] | Category[] | Sector[]) => {
          this.providers = data as Provider[];          
        });
      }
      
    });
  }
}
