import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

// Components 
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { MainComponent } from './shared/main/main.component';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ListadoComponent } from './components/list/listado.component';
import { AddCategoryComponent } from './components/forms/add-category/add-category.component';

// Services
import { ArticleService } from './services/article.service';
import { ProviderService } from './services/provider.service';
import { PurchaseOrderService } from './services/purchase-order.service';

import { FilterPipe } from './pipes/filter.pipe';
import { LowFilterPipe } from './pipes/lowFilter.pipe';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TruncatePipe } from './pipes/truncate.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    AddProviderComponent,
    AddArticleComponent,
    AddPurchaseOrderComponent,
    ListadoComponent,
    HomeComponent,
    FilterPipe,
    LowFilterPipe,
    AddCategoryComponent,
    TruncatePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
  ],
  providers: [
    ArticleService,
    ProviderService,
    PurchaseOrderService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
