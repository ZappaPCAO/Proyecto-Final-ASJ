import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdministrarServicesService } from '../../services/administrar-services.service';
import { Provider } from '../../models/provider';
import { Article } from '../../models/article';
import { PurchaseOrder, purchasesOrders } from '../../models/purchase-order';
import Swal from 'sweetalert2';
import { SectorService } from '../../services/sector.service';
import { Sector } from '../../models/sector';

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
  sectors: Sector[] = [];
  filtro: number = 0;
  filter: string= "";
  constructor(private route: ActivatedRoute, private router: Router, 
    private serivicioAdm: AdministrarServicesService, private sectorService: SectorService){     
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
  
  }

  onCancel() {
    this.currentRecord.estado = 'cancelada'; // VEER creo q seria solo un caracter.
    this.serivicioAdm.put(this.currentRecord, this.condicion);
  }


  generarArreglos(){
    this.serivicioAdm.get(this.condicion).subscribe( (data: Provider[] | Article[] | PurchaseOrder[]) => {
      this.tbody = data;
      
      if(this.tbody && this.tbody.length > 0){
      
        this.thead = (this.condicion === 'article')  ? ['producto','categoria','proveedor', 'precio'] :
                     (this.condicion === 'provider') ? ['codProvider','businessName','contactData'] : 
                                                       ['nroOC','fecEmision','fecEntrega','detalle','estado', 'total'];
      }
    });
  }                                                                        
  
  onEdit(){
    this.router.navigate([this.condicion, this.currentRecord.id]);
    this.closeContextMenu();
  }

  onRecuperar(){
    this.serivicioAdm.rescue(this.currentRecord.id, this.condicion).subscribe( (data : Provider | Article | PurchaseOrder) => {
          
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
      text: `¡Proveedor: ${this.currentRecord.businessName}, sera eliminado!`,      
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonColor: "var(--color-primary)",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminaló!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.serivicioAdm.delete(this.currentRecord.id, this.condicion).subscribe( (data : Provider | Article | PurchaseOrder) => {
          
          let index = this.tbody.findIndex((item: Provider | Article | PurchaseOrder) => item.id === this.currentRecord.id);

          if (index !== -1)
            Object.assign(this.tbody[index], data);          
          
        });        
        Swal.fire({
          position: "bottom-end",
          icon: "success",
          title: "Eliminado con exito",
          showConfirmButton: false,
          timer: 1500
        });
        this.closeContextMenu();
      }
    });
  }

  updateList(){
    this.serivicioAdm.getBySector(this.filtro, this.condicion).subscribe( (data : Provider[] | Article[] | PurchaseOrder[]) => {
      this.tbody = data;
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

      this.sectorService.getSectors().subscribe((data: Sector[]) => {
        this.sectors = data;        
      });
    });
  }
}
