import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ProviderListComponent } from './components/lists/provider-list/provider-list.component';
import { ArticleListComponent } from './components/lists/article-list/article-list.component';
import { PurhcaseOrderListComponent } from './components/lists/purhcase-order-list/purhcase-order-list.component';

const routes: Routes = [
  {path: ``, component: AddProviderComponent},
  {path: `provider/add-provider`, component: AddProviderComponent},
  {path: `article/add-article`, component: AddArticleComponent},
  {path: `purchase-order/add-purchase-order`, component: AddPurchaseOrderComponent},
  {path: `provider/list-providers`, component: ProviderListComponent},
  {path: `article/list-articles`, component: ArticleListComponent},
  {path: `purchase-order/list-purchases-orders`, component: PurhcaseOrderListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
