import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../models/article';
import { ActivatedRoute, Router } from '@angular/router';
import { checkSpecialCharacters, checkOnlyNumbers, isCuit, isEmail, isPhoneNumber, isWebsite, checkDataArticle, checkLongsArticle, isSelected } from '../../../utils/validates';
import { NgForm } from '@angular/forms';
import { Provider } from '../../../models/provider';
import Swal from 'sweetalert2';
import '@sweetalert2/theme-dark/dark.css';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'add-article',
  templateUrl: './add-article.component.html',
  styleUrl: './add-article.component.css'
})
export class AddArticleComponent implements OnInit {
  article!: Article;
  
  providers: Provider[] = []; categories: Category[] = [];
  idArticle: number = 0;
  checkSpecialCharacters = checkSpecialCharacters; checkOnlyNumbers = checkOnlyNumbers;
  isCuit = isCuit; isEmail = isEmail; isPhoneNumber = isPhoneNumber; isWebsite = isWebsite;
  show: boolean = false; showRequired = false;

  private initializeArticle(){
    this.article = {
      id: 0,
      codArticle: '',
      name: '',
      description: null,
      price: 0,
      image: null,
      provider: {
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
      },    
      category: {
        id: 0,
        name: ''
      },
    };
  }

  constructor(private router: Router, private route: ActivatedRoute,
    private providerService: ProviderService, private articleService: ArticleService,
    private categoryService: CategoryService){
      this.initializeArticle();
    }
  
  verificarUpdate(){
    if(this.idArticle > 0){      
      this.articleService.getById(this.idArticle).subscribe( (data : Article) => {
        this.article = data;
      });       
    }
  }

  agregarArticle(form: NgForm){
    if(form.invalid){
      this.showRequired = true;
      return;
    }

    this.show = !( checkDataArticle(this.article) && checkLongsArticle(this.article) );

    if( form.valid && !this.show ){ // controlo campos especificos

        if(this.idArticle === 0){ // 0 => Nuevo ; >0 => Edito          
          this.articleService.post(this.article).subscribe({
            next: (response : Article) => {
              this.article = response;
            },error: (error) => {
              // Verifico si es el string.
              if(error && typeof error.error === 'string' && error.error){              
                Swal.fire({
                  position: "bottom-end",
                  icon: "error",
                  title: `${error.error}`,
                  showConfirmButton: false,
                  timer: 2500
                });
              }
              else {
                Swal.fire({
                  position: "center",
                  icon: "error",
                  title: `Ocurrió un error al procesar la solicitud. Por favor, inténtalo de nuevo más tarde`,
                  showConfirmButton: false,
                  timer: 2500
                }); 
              }
            },complete: () => {
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
                  this.initializeArticle();                      
                  form.reset();
                }else{
                  this.router.navigate(['article', 'list']);
                }
              });
            }
          });  
        }else{
          this.articleService.put(this.article).subscribe({
            next: (response : Article) => {
              this.article = response;
            }, error: (error) => {
              // Verifico si es el string.
              if(error && typeof error.error === 'string' && error.error){              
                Swal.fire({
                  position: "bottom-end",
                  icon: "error",
                  title: `${error.error}`,
                  showConfirmButton: false,
                  timer: 2500
                });
              }
              else {
                Swal.fire({
                  position: "center",
                  icon: "error",
                  title: `Ocurrió un error al procesar la solicitud. Por favor, inténtalo de nuevo más tarde`,
                  showConfirmButton: false,
                  timer: 2500
                }); 
              }
            }, complete: () => {
              Swal.fire({
                position: "bottom-end",
                icon: "success",
                title: "Editado correctamente!",
                showConfirmButton: false,
                timer: 1500
              }).then(() => {
                this.router.navigate(['article', 'list']);
              });
            }       
          });
        }        
    }
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.idArticle = params['id'] || 0;
      this.providerService.getByActives().subscribe( (data: Provider[]) => {
        this.providers = data;
        this.categoryService.getByActives().subscribe( (data : Category[]) => {
          this.categories = data;  
          this.verificarUpdate();
        }); // GET de categories        
      }); // GET de providers
    });
  }
}
