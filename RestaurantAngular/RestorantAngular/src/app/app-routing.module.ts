import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrdersComponent} from "./orders/orders.component";
import {OrderComponent} from "./orders/order/order.component";

// const routes: Routes = [
//   { path: '', redirectTo: 'order', pathMatch: 'full' },
//   { path: 'orders', component: OrdersComponent },
//   { path: 'order', component: OrderComponent }, // Ana seviyede olmalı
//   { path: 'order/edit/:id', component: OrderComponent }
// ];

const routes: Routes = [
  {path:'',redirectTo:'order',pathMatch:'full'},
  // {path:'login',component:LoginComponent},
  // {path:'register',component:RegisterComponent},
  {path:'orders',component:OrdersComponent},
  {path:'order',children:[
      {path:'',component:OrderComponent},
      {path:'edit/:id',component:OrderComponent}
    ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
