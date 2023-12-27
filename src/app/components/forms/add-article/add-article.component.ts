import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../models/article';
import { Router } from '@angular/router';

@Component({
  selector: 'add-article',
  templateUrl: './add-article.component.html',
  styleUrl: './add-article.component.css'
})
export class AddArticleComponent implements OnInit {
  article: Article = {
    id: 0, proveedor: '', cod: '', categoria: '', producto: '', descri: '', precio: 0,
  };
  arrProviders: string[] = [];

  constructor(private router: Router,
    private providerService: ProviderService, private articleService: ArticleService){}

  ngOnInit(): void {
    for (const provedor of this.providerService.get()) {
      this.arrProviders.push(provedor.datosContacto.nombre);
    }
  }

  agregarArticle(){ 
    this.articleService.post(this.article);
    this.router.navigate(['provider/list', 'providers']);
  }
}
