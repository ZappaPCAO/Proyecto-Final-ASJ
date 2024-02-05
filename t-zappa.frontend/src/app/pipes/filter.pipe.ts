import { Pipe, PipeTransform } from '@angular/core';
import { Provider } from '../models/provider';


@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any[], args?: any, all?:boolean): any {
    if (args === '' || args === undefined) {
      return value;
    }

    if(!all){ // Si busca solo por algunos campos
      return value.filter((item) => {
        for (const key in item) {
            if ( (key === 'businessName'|| key === 'codProvider'||
                  key === 'name'|| key === 'description') &&
                  (item[key].toLowerCase().includes(args))){    
                return true;            
            }else if(( key === 'contactData' ) &&
                  ( item[key].name.toLowerCase().includes(args) || item[key].lastName.toLowerCase().includes(args) )){
              return true;
            }
        }
        return false;
      });
    }else{ // Si busca por todos los campos
      return value.filter((item) => JSON.stringify(item).toLowerCase().includes(args));
    }
   
  }

}
