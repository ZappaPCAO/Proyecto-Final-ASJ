import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { articles } from '../models/article';

const dataArticles = articles;

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) {
    console.log(`Servicio funcionando correctamente!`);
  }

  
  get (){
    return dataArticles;
    // return this.http.get('URL API');
  }
  
  post(article: any){
    article.id = (dataArticles && dataArticles.length > 0) ? dataArticles[dataArticles.length-1].id + 1 : 1; // Controlo la id
    dataArticles.push(article);
  }
}
