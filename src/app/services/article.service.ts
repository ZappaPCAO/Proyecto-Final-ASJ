import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { articles } from '../data/article';

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
    dataArticles.push(article);
  }
}
