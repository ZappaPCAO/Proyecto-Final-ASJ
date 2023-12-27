import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../models/article';
import { ActivatedRoute, Router } from '@angular/router';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import { NgForm } from '@angular/forms';
import { Provider } from '../../../models/provider';

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
      
      this.router.navigate(['article', 'list']); // Ver luego para q pueda agregar mas
      // Si devuelve todo ok, mostrar correcto con sweetalert seguramente.
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }

    // this.articleService.post(this.article);
    // this.router.navigate(['provider/list', 'providers']);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.idArticle = params['id'] || 0);

    this.arrProviders = this.providerService.get()

    this.verificarUpdate();
  }
}
