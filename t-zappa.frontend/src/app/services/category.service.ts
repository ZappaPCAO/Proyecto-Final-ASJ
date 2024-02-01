import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) {}

  get () : Observable<Category[]>{
    return this.http.get<Category[]>(this.url);
  }

  post(category: Category) : Observable<Category>{
    return this.http.post<Category>(this.url, category);
  }

  put(category: Category) : Observable<Category>{
    return this.http.put<Category>(`${this.url}/${category.id}`, category);
  }

  delete(id: number) : Observable<Category>{
    return this.http.delete<Category>(`${this.url}/delete/${id}`);
  }

  rescue(id: number) : Observable<Category> {
    return this.http.delete<Category>(`${this.url}/rescue/${id}`);
  }
}
