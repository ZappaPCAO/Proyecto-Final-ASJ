import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ListadoComponent } from './components/list/listado.component';

const routes: Routes = [
  {path:``, component: AddProviderComponent},
  {path: `article`,
    children:[
      {path: `add-article`, component: AddArticleComponent},
      {path: `list/:tipo`, component: ListadoComponent},
      {path: `**`, component: AddArticleComponent},
    ]
  },
  {path: `provider`,
    children:[
      {path: `add-provider`, component: AddProviderComponent},
      {path: `list/:tipo`, component: ListadoComponent},
      {path: `**`, component: AddProviderComponent},
    ]
  },
  {path: `purchase-order`,
    children:[
      {path: `add-purchase-order`, component: AddPurchaseOrderComponent},
      {path: `list/:tipo`, component: ListadoComponent},
      {path: `**`, component: AddPurchaseOrderComponent},
    ]
  },
  {path: `**`, component: AddProviderComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
