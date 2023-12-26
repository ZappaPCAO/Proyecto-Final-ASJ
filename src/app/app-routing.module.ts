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
      {path: `update-article/:id`, component: AddArticleComponent},
      {path: `**`, component: AddArticleComponent},
    ]
  },
  {path: `provider`,
    children:[
      {path: `add-provider`, component: AddProviderComponent},
      {path: 'update-provider/:id', component:AddProviderComponent},
      {path: `**`, component: AddPurchaseOrderComponent},
    ]
  },
  {path: `purchase-order`,
    children:[
      {path: `add-purchase-order`, component: AddPurchaseOrderComponent},
      {path: `update-purchase-order/:id`, component:AddPurchaseOrderComponent},
      {path: `**`, component: AddPurchaseOrderComponent},
    ]
  },
  {path: `:tipo/list`, component: ListadoComponent},
  
  {path: `**`, component: AddProviderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
