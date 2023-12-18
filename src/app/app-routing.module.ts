import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ProviderListComponent } from './components/lists/provider-list/provider-list.component';
import { ArticleListComponent } from './components/lists/article-list/article-list.component';
import { PurhcaseOrderListComponent } from './components/lists/purhcase-order-list/purhcase-order-list.component';

const routes: Routes = [
  {path: ``, component: AddProviderComponent},
  {path: `article`,
    children:[
      {path: `add-article`, component: AddArticleComponent},
      {path: `list-articles`, component: ArticleListComponent}      
    ]
  },
  {path: `provider`,
    children:[
      {path: `add-providers`, component: AddProviderComponent},
      {path: `list-providers`, component: ProviderListComponent}
    ]
  },
  {path: `purchase-order`,
    children:[
      {path: `add-purchase-order`, component: AddPurchaseOrderComponent},
      {path: `list-purchase-order`, component: PurhcaseOrderListComponent}
    ]
  },
  {path: `**`, component: AddProviderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
