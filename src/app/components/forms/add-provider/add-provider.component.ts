import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { Provider } from '../../../models/provider';
import { Router } from '@angular/router';
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
  // provider!: Provider;
  provincia: Provincia[] = [];

  provider: Provider = {id: '-1', cod: '', razSocial: '', email: '', rubro: '', calle: '', 
                        nroCalle: 0, pais: '', provincia: '', localidad: '', cuit: '', condIva: '',
                        nombre: '', apellido: '', telefono: 0, rol: '', emailEmpresa: '',
                        telefonoEmpresa: 0, sitioWeb: ''};

  constructor(private router: Router,
    public providerService: ProviderService,
    private provService: ProvLocArgentinasService){}

  ngOnInit(): void {
    this.provService.obtenerLocalidades().subscribe((res) => {
      for (const localidad of res.localidades_censales) {
        if(localidad.nombre == null)
          continue;
        
        const provincia = this.provincia.find((provincia: any) => provincia.id === localidad.provincia_id);
        
        if (provincia) {
          const localidadExistente = provincia.localidades.find((loc: any) => loc.id === localidad.provincia_id);
          if(!localidadExistente){
            provincia.localidades.push({
              id: localidad.id,
              nombre: localidad.nombre,
            });
          }          
        } else {
          this.provincia.push({
            id: localidad.provincia_id,
            nombre: localidad.provincia_nombre,
            localidades: [{
              id: localidad.id,
              nombre: localidad.nombre,
            }],
          });
        }        
      }
      console.log(this.provincia);
    });   
  };
  

  agregarProvider(form: NgForm){
    if( form.valid && 
      ( verificarDatos(this.provider) && // que no hay ningun caracter raro
        verificarLongitudes(this.provider) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.provider) ) ){ // controlo campos especificos

      this.providerService.post(this.provider);
      this.router.navigate(['provider/list', 'providers']);
      // Si devuelve todo ok, mostrar correcto con sweetalert seguramente.
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }
}
