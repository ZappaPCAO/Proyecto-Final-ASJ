import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sector } from '../models/sector';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SectorService {
  private url = "http://localhost:8080/sectors";

  constructor(private http: HttpClient) {}
  
  getSectors() : Observable<Sector[]>{
    return this.http.get<Sector[]>(this.url);
  }

  // post(article: Article) : Observable<Article>{
  //   return this.http.post<Article>(this.url, article);
  // }

  // put(article: Article) : Observable<Article>{
  //   return this.http.put<Article>(`${this.url}/${article.id}`, article);
  // }

  // delete(id: number) : Observable<Article>{
  //   return this.http.delete<Article>(`${this.url}/delete/${id}`);
  // }

  // rescue(id: number) : Observable<Article> {
  //   return this.http.delete<Article>(`${this.url}/rescue/${id}`);
  // }

}
