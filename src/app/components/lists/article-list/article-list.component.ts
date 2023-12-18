import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';

@Component({
  selector: 'article-list',
  templateUrl: './article-list.component.html',
  styleUrl: './article-list.component.css'
})
export class ArticleListComponent implements OnInit {
  articles: any = [];

  constructor(public articleService: ArticleService){}

  ngOnInit(): void {
    // this.articleService.get().subscribe(data => this.articles = data); Para usar con api, mas adelante
    this.articles = this.articleService.get();
  }
  
}
