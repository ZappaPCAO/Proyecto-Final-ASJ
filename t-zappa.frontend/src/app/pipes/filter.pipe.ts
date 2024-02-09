import { Pipe, PipeTransform } from '@angular/core';
import Swal from 'sweetalert2';


@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any[], args?: any, all?:boolean): any {
    if (args === '' || args === undefined) {
      return value;
    }

    let filteredValue = [];

    if(!all){ // Si busca solo por algunos campos
      filteredValue = value.filter((item) => {
        for (const key in item) {
          if(item[key] !== null){
            if ( (key === 'businessName'|| key === 'codProvider'||
                  key === 'name'|| key === 'description') &&
                  (item[key].toLowerCase().includes(args.toLowerCase()))){    
                return true;            
            }else if(( key === 'contactData' ) &&
                  ( item[key].name.toLowerCase().includes(args.toLowerCase()) ||
                    item[key].lastName.toLowerCase().includes(args.toLowerCase()) )){
              return true;
            }
          }
        }
        return false;
      });
    }else{ // Si busca por todos los campos
      filteredValue = value.filter((item) => JSON.stringify(item).toLowerCase().includes(args.toLowerCase()));
    }

    if (filteredValue.length == 0) { // Me fijo si encontro coincidencias
      Swal.fire({
        title: "No se econtraron registros para el filtro aplicado!",
        icon: "warning",
        showCloseButton: true,        
        showConfirmButton: false,
      });
    }  

    return filteredValue;
  }

}
