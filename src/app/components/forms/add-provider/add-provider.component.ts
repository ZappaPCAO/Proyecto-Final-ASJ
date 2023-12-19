import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { Provider } from '../../../data/provider';
import { Router } from '@angular/router';

@Component({
  selector: 'add-provider',
  templateUrl: './add-provider.component.html',
  styleUrl: './add-provider.component.css'
})
export class AddProviderComponent implements OnInit {
  provider: Provider = {id: 0, cod: '', razSocial: '', email: '', rubro: '', calle: '', 
                        nro: '', pais: '', provincia: '', localidad: '', cuit: '', condIva: '',
                        nombre: '', apellido: '', telefono: '', rol: ''};

  constructor(private router: Router,
    public providerService: ProviderService){}
  ngOnInit(): void {}

  agregarProvider(){
    this.providerService.post(this.provider);
    this.router.navigate(['provider/list', 'providers']);
  }
}
