import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { ProviderService } from '../../services/provider.service';
import { PurchaseOrderService } from '../../services/purchase-order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AdministrarServicesService } from '../../services/administrar-services.service';
import { Provider } from '../../models/provider';
import { Article } from '../../models/article';
import { PurchaseOrder } from '../../models/purchase-order';

@Component({
  selector: 'listado',
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent implements OnInit{
  thead: any = [];tbody: any = [];
  condicion!: string;

  // rightPanelStyle: { display: string, left?: number, top?: number } = { display: 'none' };
  rightPanelStyle: any;
  currentRecord: any;

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

  onEdit(soyAlgo: any){
    let tipo = `${this.condicion}/update-${this.condicion}`;
    this.router.navigate([tipo, soyAlgo.id]);
  }
  onDelete(soyAlgo: any){
    // Preguntar si esta seguro que desea eliminar el proveedor etc
    this.serivicioAdm.delete(soyAlgo, this.condicion);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.condicion = params['tipo']);
    console.log(`condicion ${this.condicion}`);
    this.generarArreglos();
  }

}
