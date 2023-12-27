import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { Provider } from '../../../models/provider';
import { ActivatedRoute, Router } from '@angular/router';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import { NgForm } from '@angular/forms';
import { Provincia } from '../../../models/provincias';
import { ProvLocArgentinasService } from '../../../services/prov-loc-argentinas.service';


@Component({
  selector: 'add-provider',
  templateUrl: './add-provider.component.html',
  styleUrl: './add-provider.component.css'
})
export class AddProviderComponent implements OnInit {
  indexProv = 0; provincias!: Provincia[];

  provider: Provider = {
    id: 0,
    cod: '',
    razSocial: '',
    rubro: '',
    sitioWeb: '',
    email: '',
    telefono: 0,
    direccion: {
      calle: '',
      nro: 0,
      cp: '',
      localidad: '',
      provincia: '',
      pais: '',
    },
    datosFiscales: {
      cuit: '',
      condIva: '',
    },
    datosContacto: {
      nombre: '',
      apellido: '',
      telefono: 0,
      email: '',
      rol: '',
    },
  };
  
  idProvider: any;

  constructor(private router: Router, private route: ActivatedRoute,
    public providerService: ProviderService,
    private provService: ProvLocArgentinasService){}
  
  updateLocalidades(){ // para el change de la provincia
    this.indexProv = this.provincias.findIndex(provincia => provincia.nombre === this.provider.direccion.provincia); 
  }

  verificarUpdate(){
    if(this.idProvider > 0){      
      this.provider = this.providerService.getById(this.idProvider);
      this.updateLocalidades();
    }
  }

  agregarProvider(form: NgForm){
    if( form.valid && 
      ( verificarDatos(this.provider) && // que no hay ningun caracter raro
        verificarLongitudes(this.provider) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.provider) ) ){ // controlo campos especificos
        if(this.idProvider === 0){ // 0 => Nuevo ; >0 => Edito
          this.providerService.post(this.provider);
        }else{
          this.providerService.put(this.provider);
        }
      
      this.router.navigate(['provider', 'list']); // Ver luego para q pueda agregar mas
      // Si devuelve todo ok, mostrar correcto con sweetalert seguramente.
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  ngOnInit(): void {
    // this.loadProvincias(); // Busco de la API las prinvincias Argentinas.
    this.route.params.subscribe(params => this.idProvider = params['id'] || 0);

    this.provincias = this.provService.provincias; // get

    this.verificarUpdate();

    console.log(`id =>  ${this.provider.direccion.localidad}`);
  };
}
