import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdministrarServicesService } from '../../services/administrar-services.service';
import { Provider } from '../../models/provider';
import { Article } from '../../models/article';
import { PurchaseOrder } from '../../models/purchase-order';
import Swal from 'sweetalert2';

@Component({
  selector: 'listado',
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent implements OnInit{

  thead: any = [];tbody: any = [];
  condicion: string = '';
  rightPanelStyle: any;
  currentRecord!: any;

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
  
  }

  onCancel() {
    this.currentRecord.estado = 'cancelada';
    this.serivicioAdm.put(this.currentRecord, this.condicion);
  }


  generarArreglos(){
    this.tbody = this.serivicioAdm.get(this.condicion);
    console.log(this.tbody + 'array');
    if(this.tbody && this.tbody.length > 0)
      if(this.condicion === 'article'){
        this.thead = ['producto','categoria','proveedor', 'precio'];
        // this.tbody.sort((a:Article,b:Article) => a.producto.localeCompare(b.producto));        
      }else if(this.condicion === 'provider'){
        this.thead = ['cod','razSocial','datosContacto'];
        // this.tbody.sort((a:Provider,b:Provider) => a.razSocial.localeCompare(b.razSocial));  
      }else{
        this.thead = ['nroOC','fecEmision','fecEntrega','detalle','estado', 'total'];
        // this.tbody.sort((a:PurchaseOrder,b:PurchaseOrder) => a.fecEmision.localeCompare(b.fecEmision));
      }
      // this.currentRecord = this.tbody[0]
  }                                                                        
  
  onEdit(){
    let tipo = `${this.condicion}/update-${this.condicion}`;

    console.log("id " + this.currentRecord.id + ", tipo: "+tipo )

    this.router.navigate([tipo, this.currentRecord.id]);
    this.closeContextMenu();
  }

  onDelete(){
    Swal.fire({
      title: "¿Estas seguro?",
      text: `¡${this.currentRecord.id} sera eliminado permanentemente!`,
      
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonColor: "var(--color-primary)",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.serivicioAdm.delete(this.currentRecord, this.condicion); // ver de poner una promesa
        this.closeContextMenu();
        Swal.fire({
          position: "bottom-end",
          icon: "success",
          title: "Eliminado con exito",
          showConfirmButton: false,
          timer: 1500
        });
      }
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => { this.condicion = params["tipo"];
      if(this.condicion !== null){
        this.generarArreglos();
      }
      console.log(`condicion ${this.condicion}`);
      console.log('algo '+this.thead)
    });
  }
}
