import { Component, OnInit } from '@angular/core';
import { OrderService } from '../shared/order.service';
import { Order } from '../shared/order.model';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orderList: Order[];

  constructor(private orderService: OrderService
              ) { }

  ngOnInit(): void {
    this.orderService.GetOrderList().then(res => this.orderList = res as Order[]);
    this.RefreshToList();
  }

  RefreshToList(){
    this.orderService.GetOrderList().then(res => this.orderList = res as Order[]);
  }

  OnOrderDelete(orderId: number) {
    this.orderService.DeleteOrder(orderId).then(res=>{
      this.RefreshToList();
    });
  }

}
