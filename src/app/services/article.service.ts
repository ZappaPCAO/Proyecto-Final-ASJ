import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article, articles } from '../models/article';
import { agregarObjetoSiExiste, pisarDatosByTipo } from '../utils/localStorage';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  dataArticles: Article[] = articles;

  constructor(private http: HttpClient) {
    console.log(`Servicio funcionando correctamente!`);
  }

  get (){
    return this.dataArticles;
  }

  getById(id: number): Article {
    let article!: any;

    if(this.dataArticles.length > 0){
      article = this.dataArticles.find( article => article.id == id ); 
    }

    return article;
  }
  
  post(article: Article){
    article.id = (this.dataArticles && this.dataArticles.length > 0) ? this.dataArticles[this.dataArticles.length-1].id + 1 : 1; // Controlo la id
    this.dataArticles.push(article);
    
    pisarDatosByTipo('provider', this.dataArticles);
  }

  put(article: Article) {
    let auxArticle: Article = this.dataArticles.find(arti => arti.id = article.id)!;

    auxArticle = article;

    agregarObjetoSiExiste('article', article);
  }

  delete(article: Article) {
    let index: number = this.dataArticles.findIndex(arti => arti.id === article.id);
    
    this.dataArticles.splice(index, 1);
    pisarDatosByTipo('provider', this.dataArticles);
  }
}
