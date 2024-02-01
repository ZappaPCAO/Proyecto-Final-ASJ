import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'lowFilter'
})
export class LowFilterPipe implements PipeTransform {

  transform(value: any[], condicion:boolean): any {

    if (!condicion) {
      return value;
    }

    return value.filter((item) => !item.isDeleted);
  }
  
}
