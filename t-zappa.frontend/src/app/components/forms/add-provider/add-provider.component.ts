import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { Provider } from '../../../models/provider';
import { ActivatedRoute, Router } from '@angular/router';
// import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validates';
import { checkDataProvider, checkLongsProvider, checkOnlyNumbers, checkSpecialCharacters, isCuit, isEmail, isPhoneNumber, isWebsite } from '../../../utils/validates';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';
import { City, Country, State } from '../../../models/geoLocation';
import { InicializadorService } from '../../../services/inicializador.service';
import { IvaCondition } from '../../../models/ivaCondition';
import { Sector } from '../../../models/sector';
import { SectorService } from '../../../services/sector.service';

@Component({
  selector: 'add-provider',
  templateUrl: './add-provider.component.html',
  styleUrl: './add-provider.component.css'
})
export class AddProviderComponent implements OnInit {
  countries: Country[] = []; states: State[] = []; cities: City[] = [];
  ivaConditions: IvaCondition[] = []; sectors: Sector[] = [];
  isWebsite = isWebsite; isCuit=isCuit; isEmail = isEmail;isPhoneNumber=isPhoneNumber;
  checkOnlyNumbers=checkOnlyNumbers;checkSpecialCharacters=checkSpecialCharacters;
  show: boolean = false; showRequired: boolean = false;

  provider: Provider = {
    id: 0,
    codProvider: '',
    businessName: '',
    website: '',
    email: '',
    phone: '',
    logo: '',
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
        id: 0,
        name: '',
        state:{
          id: 0,
          name: '',
          country: {
            id: 0,
            name: ''
          }
        }
      }      
    },
    taxData: {
      id: 0,
      cuit: '',
      ivaCondition: {
        id: 0,
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
    private inicializadorService: InicializadorService,
    private sectorService: SectorService){}
  
  formatPhoneNumber(event: any) {
    let phoneNumber = event.target.value.replace(/\D/g, '');

    if (phoneNumber.length === 10) {
      phoneNumber = phoneNumber.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
    }

    this.provider.phone = phoneNumber;
  }

  formatCuil(event: any) {
    let cuit = event.target.value.replace(/\D/g, ''); // Eliminar todos los caracteres no numéricos

    if (cuit.length >= 2 && cuit.length <= 11) {
      cuit = cuit.replace(/(\d{2})(\d{8})(\d{1})/, '$1-$2-$3');
    }

    this.provider.taxData.cuit = cuit;
  }

  updateStates(idCountry:number){ // para el change del pais
    this.states = this.countries.find(country => +country.id == idCountry)?.states!; 
  }
  updateCities(idState:number){ // para el change de la provincia
    this.cities = this.states.find(state => +state.id == idState)?.cities!;
  }

  verificarUpdate(){
    if(this.idProvider > 0){      
      this.providerService.getById(this.idProvider).subscribe( data => {
        this.provider = data;
         this.updateStates(this.provider.location.city.state.country.id);
         this.updateCities(this.provider.location.city.state.id);        
      });
    }
    
  }

  agregarProvider(form: NgForm){
    if(form.invalid){
      this.showRequired = true;
      return;
    }

    this.show = !( checkDataProvider(this.provider) && checkLongsProvider(this.provider) );

    if( form.valid && !this.show ){ // controlo campos especificos

        if(this.idProvider === 0){ // 0 => Nuevo ; >0 => Edito          
          this.providerService.post(this.provider).subscribe(data =>{
            this.provider = data;
          },(error) => {
            Swal.fire({
              position: "bottom-end",
              icon: "error",
              title: `Hubo un problema en la creacion: ${error.get}`,
              showConfirmButton: false,
              timer: 1500
            });
          }, () => {
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
              if (result.isConfirmed) {  // Si quiere cargar otro formateo los datos.              
                this.provider.id = 0;
                this.provider.contactData.id = 0;
                this.provider.taxData.id = 0;   
                this.provider.location.id = 0;        
                form.reset();
              }else{
                this.router.navigate(['provider', 'list']);
              }
            });
          });
        }else{
          this.providerService.put(this.provider).subscribe(data => {
            this.provider = data;
            Swal.fire({
              position: "bottom-end",
              icon: "success",
              title: "Creado correctamente!",
              showConfirmButton: false,
              timer: 1500
            }).then(() => {
              this.router.navigate(['provider', 'list']);
            });
          });
        }        
    }
  }

  ngOnInit(): void {    
    this.route.params.subscribe((params) => {
      this.idProvider = params['id'] || 0;
      this.inicializadorService.getGeoLocations().subscribe(data => {
        this.countries = data;
        this.inicializadorService.getIvaConditions().subscribe(data => {
          this.ivaConditions = data;
          this.sectorService.getSectors().subscribe(data => {
            this.sectors = data;
            this.verificarUpdate();
          }); // Get de Sectors
        }); // GET de IVA       
      });// GET de geoLocations        
    });  
  }
}
