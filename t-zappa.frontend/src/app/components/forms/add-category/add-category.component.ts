import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
// import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validates';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent implements OnInit {
  @Input() categories: Category[] = [];

  category!: Category;
  idCategory: number = 0;
  isModal: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute,
    private categoryService: CategoryService){
      this.initializeCategory();
    }

  private initializeCategory(){
    this.category = {
      id: 0,
      name: '',
    };
  }

  verificarUpdate(){
    if(this.idCategory > 0){      
      this.categoryService.getById(this.idCategory).subscribe( (data : Category) => {
        this.category = data;
      });       
    }
  }

  agregarCategory(form: NgForm){
    if( form.valid 
      // && 
      // ( verificarDatos(this.category) && // que no hay ningun caracter raro
      //   verificarLongitudes(this.category) && // que los largos sean los que quiero
      //   verificarCamposEspeciales(this.category) ) 
      ){ // controlo campos especificos
      if(this.idCategory === 0){ // 0 => Nuevo ; >0 => Edito
        this.categoryService.post(this.category).subscribe({
          next: (response) => {
            this.category = response;
          },
          error: (error) => {
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
          },
          complete: () => {
            if(!this.isModal){ // Si no es una modal.
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
                  this.category.id = 0;                      
                  form.reset();
                }else{
                  this.router.navigate(['category', 'list']);
                }
              });
            }else{
              this.categories.push({...this.category});                
              Swal.fire({
                position: "bottom-end",
                icon: "success",
                title: "Creado correctamente!",
                showConfirmButton: false,
                timer: 1500
              });                         
            }
            this.initializeCategory();                     
            form.reset();
          }
        });    
      }else{
        this.categoryService.put(this.category).subscribe({
          next: (response) => {
            this.category = response;
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
          },
          complete: () => {
            Swal.fire({
              position: "bottom-end",
              icon: "success",
              title: "Editado correctamente!",
              showConfirmButton: false,
              timer: 1500
            }).then(() => {
              this.router.navigate(['category', 'list']);
            });
          }
        });
      }
    }
  }

  ngOnInit(): void {   
    const currentRoute = this.router.url;
    this.isModal = !currentRoute.startsWith('/category');

    if(this.isModal){
      this.idCategory = 0;
    }else{
      this.route.params.subscribe((params) => {
        this.idCategory = params['id'] || 0;
        this.verificarUpdate();
      });
    }    
  }
}

