import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../models/article';
import { ActivatedRoute, Router } from '@angular/router';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import { NgForm } from '@angular/forms';
import { Provider } from '../../../models/provider';
import Swal from 'sweetalert2';

@Component({
  selector: 'add-article',
  templateUrl: './add-article.component.html',
  styleUrl: './add-article.component.css'
})
export class AddArticleComponent implements OnInit {
  article: Article = {
    id: 0,
    proveedor: {
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
      }
    },
    cod: '',
    categoria: '',
    producto: '',
    descri: '',
    precio: 0,
  };
  arrProviders!: Provider[];
  idArticle: number = 0;idProveedor: number = 0;

  constructor(private router: Router, private route: ActivatedRoute,
    private providerService: ProviderService, private articleService: ArticleService){}
  
  verificarUpdate(){
    if(this.idArticle > 0){      
      this.article = this.articleService.getById(this.idArticle);
    }
  }

  agregarArticle(form: NgForm){ 
    this.article.proveedor = this.arrProviders.find(provi => provi.id == this.idProveedor)!;

    if( form.valid && 
      ( verificarDatos(this.article) && // que no hay ningun caracter raro
        verificarLongitudes(this.article) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.article) ) ){ // controlo campos especificos

      if(this.idArticle === 0){ // 0 => Nuevo ; >0 => Edito
        this.articleService.post(this.article);
      }else{
        this.articleService.put(this.article);
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
          this.router.navigate(['article', 'list']);
        }
      });
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.idArticle = params['id'] || 0);

    this.arrProviders = this.providerService.get()

    this.verificarUpdate();
  }
}
