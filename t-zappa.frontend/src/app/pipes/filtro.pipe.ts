import { Pipe, PipeTransform } from '@angular/core';
import { Provider } from '../models/provider';


@Pipe({
  name: 'filtro'
})
export class FiltroPipe implements PipeTransform {

  transform(value: any[], args?: any): any {
    if (args === '' || args === undefined) {
      return value;
    }

    return value.filter((item) => {
      for (const key in item) {
        if (key === "businessName" || key === "codProvider") {
          if (item[key].toLowerCase().includes(args)) {
            return true;
          }
        } else if (key === "contactData") {
          if (
            item[key].name.toLowerCase().includes(args) ||
            item[key].lastName.toLowerCase().includes(args)
          ) {
            return true;
          }
        }
      }
      return false;
    });
  }

}
