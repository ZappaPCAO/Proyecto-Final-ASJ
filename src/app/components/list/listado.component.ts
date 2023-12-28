import { Component, OnInit } from '@angular/core';
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
onInfo() {
throw new Error('Method not implemented.');
}
onAdd() {
throw new Error('Method not implemented.');
}
  thead: any = [];tbody: any = [];
  condicion: string = '';
  rightPanelStyle: any;
  currentRecord!: Provider | Article | PurchaseOrder;

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

  generarArreglos(){
    this.tbody = this.serivicioAdm.get(this.condicion);
    console.log(this.tbody + 'array');
    if(this.tbody && this.tbody.length > 0)
      this.thead = Object.keys(this.tbody[0]).filter(key => // Obtengo las claves a mostrar individualmente. Las q agrupo las trabajo en el html. 
        ( key !== 'id' && key !== 'rubro' && key !== 'direccion' && key !== 'datosFiscales' && key !== 'sitioWeb' &&
          key !== 'email' && key !== 'telefono' )); // para Proveedores.
  }                                                                        

  onEdit(){
    this.closeContextMenu();
    let tipo = `${this.condicion}/update-${this.condicion}`;
    this.router.navigate([tipo, this.currentRecord.id]);
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
    });
  }
}
