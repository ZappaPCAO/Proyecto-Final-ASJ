import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { Provider } from '../../../models/provider';
import { ActivatedRoute, Router } from '@angular/router';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import { NgForm } from '@angular/forms';
import { Provincia } from '../../../models/provincias';
import { ProvLocArgentinasService } from '../../../services/prov-loc-argentinas.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'add-provider',
  templateUrl: './add-provider.component.html',
  styleUrl: './add-provider.component.css'
})
export class AddProviderComponent implements OnInit {
  indexProv = 0; states!: Provincia[];

  provider: Provider = {
    id: 0,
    codProvider: '',
    businessName: '',
    website: '',
    email: '',
    phone: '',
    sector:{
      id: 0,
      sector: ''
    },
    location: {
      id: 0,
      street: '',
      number: 0,
      postalCode: '',
      city: {
        id: 20,
        name: '',
        state:{
          id: 24,
          name: '',
          country: {
            id: 2,
            name: ''
          }
        }
      }      
    },
    taxData: {
      id: 0,
      cuit: '',
      ivaCondition: {
        id: 2,
        condition: ''
      },
    },
    contactData: {
      id: 0,
      name: '',
      lastName: '',
      phone: '',
      email: '',
      role: '',
    }
  };
  
  idProvider: any;

  constructor(private router: Router, private route: ActivatedRoute,
    public providerService: ProviderService,
    private provService: ProvLocArgentinasService){}
  
  updateLocalidades(){ // para el change de la provincia
     this.indexProv = this.states.findIndex(provincia => provincia.nombre === this.provider.location.city.state.name); 
  }

  verificarUpdate(){
    if(this.idProvider > 0){      
      this.provider = this.providerService.getById(this.idProvider);
      this.updateLocalidades();
    }
    console.log(this.provider)
  }

  agregarProvider(form: NgForm){
    if( form.valid && 
      ( verificarDatos(this.provider) && // que no hay ningun caracter raro
        verificarLongitudes(this.provider) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.provider) ) ){ // controlo campos especificos
        if(this.idProvider === 0){ // 0 => Nuevo ; >0 => Edito
          this.providerService.post(this.provider).subscribe(data =>{
            this.provider = data;
          } );
        }else{
          this.providerService.put(this.provider);
        }
        Swal.fire({
          title: "¿Desea crear otro?",          
          icon: 'success',
          timer: 2500,       
          showCancelButton: true, 
          confirmButtonColor: "var(--color-primary)",
          cancelButtonColor: "var(--color-secondary)",          
          confirmButtonText: "Si",
          cancelButtonText: "No"
        }).then((result) => {
          if (result.isConfirmed) {
            form.reset();
          }else{
            this.router.navigate(['provider', 'list']);
          }
        });
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  ngOnInit(): void {   
    this.route.params.subscribe((params) => {
      this.idProvider = params['id'] || 0;      
      this.states = this.provService.provincias; // get
      
      this.verificarUpdate();
    });  
  };
}
