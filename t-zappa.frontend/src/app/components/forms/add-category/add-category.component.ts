import { Component } from '@angular/core';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { verificarCamposEspeciales, verificarDatos, verificarLongitudes } from '../../../utils/validaciones';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {
  category: Category = {
    id: 0,
    category: ''
  };
  idCategory: number = 0;

  constructor(private router: Router, private route: ActivatedRoute,
    private categoryService: CategoryService){}
  
  verificarUpdate(){
    if(this.idCategory > 0){      
      this.categoryService.getById(this.idCategory).subscribe( (data : Category) => {
        this.category = data;
      });       
    }
  }

  agregarCategory(form: NgForm){
    console.log(JSON.stringify(this.category))
    
    if( form.valid && 
      ( verificarDatos(this.category) && // que no hay ningun caracter raro
        verificarLongitudes(this.category) && // que los largos sean los que quiero
        verificarCamposEspeciales(this.category) ) ){ // controlo campos especificos
        if(this.idCategory === 0){ // 0 => Nuevo ; >0 => Edito

          this.categoryService.post(this.category).subscribe((data : Category) =>{
            this.category = Object.assign({}, data);
          }, (error) => {
            Swal.fire({
              position: "bottom-end",
              icon: "error",
              title: `Hubo un problema en la creacion: ${error.get}`,
              showConfirmButton: false,
              timer: 1500
            });
          },() => {
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
                this.router.navigate(['article', 'list']);
              }
            });
          });
        }else{
          this.categoryService.put(this.category).subscribe(data => {
            this.category = data;
          });
        }
    }else{
      // Hago lo que hizo el profe con los cartelitos.
    }
  }

  ngOnInit(): void {    

    this.route.params.subscribe((params) => {
      this.idCategory = params['id'] || 0;
      this.verificarUpdate();
    });
  }
}

