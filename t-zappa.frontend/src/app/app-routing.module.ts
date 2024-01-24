import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from './components/forms/add-provider/add-provider.component';
import { AddArticleComponent } from './components/forms/add-article/add-article.component';
import { AddPurchaseOrderComponent } from './components/forms/add-purchase-order/add-purchase-order.component';
import { ListadoComponent } from './components/list/listado.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {path:``, component: HomeComponent},
  {path: `article`,
    children:[
      {path: ``, component: AddArticleComponent},
      {path: `list`, component: ListadoComponent, data: { tipo: 'article' }},
      {path: `:id`, component: AddArticleComponent},      
      {path: `**`, redirectTo: ''},
    ]
  },
  {path: `provider`,
    children:[
      {path: ``, component: AddProviderComponent},       
      {path: `list`, component: ListadoComponent, data: { tipo: 'provider' }},
      {path: ':id', component:AddProviderComponent}, 
      {path: `**`, redirectTo: '' },
    ]
  },
  {path: `purchase-order`,
    children:[
      {path: ``, component: AddPurchaseOrderComponent},  
      {path: `list`, component: ListadoComponent, data: { tipo: 'purchase-order' }},     
      {path: `:id`, component:AddPurchaseOrderComponent},          
      {path: `**`, redirectTo: ''},
    ]
  },
  {path: `**`, redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
