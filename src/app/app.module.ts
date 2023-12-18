import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// Components 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainComponent } from './components/main/main.component';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ProviderListComponent } from './components/lists/provider-list/provider-list.component';
import { ArticleListComponent } from './components/lists/article-list/article-list.component';
import { PurhcaseOrderListComponent } from './components/lists/purhcase-order-list/purhcase-order-list.component';

// Services
import { ArticleService } from './services/article.service';
import { ProviderService } from './services/provider.service';
import { PurchaseOrderService } from './services/purchase-order.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    AddProviderComponent,
    AddArticleComponent,
    AddPurchaseOrderComponent,
    ProviderListComponent,
    ArticleListComponent,
    PurhcaseOrderListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    ArticleService,
    ProviderService,
    PurchaseOrderService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
