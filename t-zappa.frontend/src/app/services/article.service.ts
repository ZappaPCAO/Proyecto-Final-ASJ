import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../models/article';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private url = 'http://localhost:8080/articles';

  constructor(private http: HttpClient) {}

  get () : Observable<Article[]>{
    return this.http.get<Article[]>(this.url);
  }

  getById(id: number) : Observable<Article> {
    return this.http.get<Article>(`${this.url}/${id}`);
  }

  getByCategory(id: number) : Observable<Article[]> {
    return this.http.get<Article[]>(`${this.url}/category/${id}`);
  }

  getByProvider(id: number) : Observable<Article[]> {
    return this.http.get<Article[]>(`${this.url}/provider/${id}`);
  }

  post(article: Article) : Observable<Article>{
    return this.http.post<Article>(this.url, article);
  }

  put(article: Article) : Observable<Article>{
    return this.http.put<Article>(`${this.url}/${article.id}`, article);
  }

  delete(id: number) : Observable<Article>{
    return this.http.delete<Article>(`${this.url}/delete/${id}`);
  }

  rescue(id: number) : Observable<Article> {
    return this.http.delete<Article>(`${this.url}/rescue/${id}`);
  }
}
