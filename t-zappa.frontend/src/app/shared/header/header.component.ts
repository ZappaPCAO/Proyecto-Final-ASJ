import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',  
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  icono = "fa-regular fa-user";

  constructor(private route: Router){}

  isActive(word:string):boolean{
    return this.route.routerState.snapshot.url.split('/').includes(word);
  }
}
