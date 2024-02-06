import { Pipe, PipeTransform } from '@angular/core';
import Swal from 'sweetalert2';

@Pipe({
  name: 'lowFilter'
})
export class LowFilterPipe implements PipeTransform {

  transform(value: any[], condicion:boolean): any {

    if (!condicion) {
      return value;
    }

    let filteredValue = [];

    filteredValue = value.filter((item) => (item.state === 'A' || item.isDeleted === false ));

    if (filteredValue.length == 0) { // Me fijo si encontro coincidencias
      console.log("deberia mostrar el cartel!")
      Swal.fire({
        title: "No se econtraron registros activos!",
        icon: "warning",
        showCloseButton: true,        
        showConfirmButton: false,
      });
    }
    
    return filteredValue;
  }
  
}
