import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'add-provider',
  templateUrl: './add-provider.component.html',
  styleUrl: './add-provider.component.css'
})
export class AddProviderComponent implements OnInit {
  provider: Provider = {id: 99, cod: '', razSocial: '', email: '', rubro: '', calle: '', 
                        nro: '', pais: '', provincia: '', localidad: '', cuit: '', condIva: '',
                        nombre: '', apellido: '', telefono: '', rol: ''};

  constructor(public providerService: ProviderService){}
  
  ngOnInit(): void {
    
  }
  agregarProvider(form:any) {
    console.log(form);

    this.providerService.post( 
      this.provider
    )
  }
}

type Provider = {
  id: number;
  cod: string;
  razSocial: string;
  email: string;
  rubro: string;
  calle: string;
  nro: string;
  pais: string;
  provincia: string;
  localidad: string;
  cuit: string;
  condIva: string;
  nombre: string;
  apellido: string;
  telefono: string;
  rol: string;
}

