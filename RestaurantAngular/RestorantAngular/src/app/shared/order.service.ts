import {Injectable} from '@angular/core';
import {Order} from './order.model';
import {OrderItem} from './order-item.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment.prod';
import {firstValueFrom, lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  formData: Order = new Order();
  orderItemModel: Array<OrderItem> = [];

  constructor(private http: HttpClient) { }

  saveOrder(orderModel: Order) {
    this.formData.customerId = orderModel.customerId;
    this.formData.grandTotal = orderModel.grandTotal;
    this.formData.orderId = orderModel.orderId;
    this.formData.orderNo = orderModel.orderNo;
    this.formData.paymentMethod = orderModel.paymentMethod;

    const body = {
      orderSubDto: this.formData,
      orderItemModelDtoList: this.orderItemModel
    };

    return this.http.post(environment.apiUrl + 'order/save', body, {
      responseType: 'text' as 'json'
    });
  }
  async GetOrderList() {
    try {
      const data = await lastValueFrom(this.http.get(environment.apiUrl + 'order/getAllDto'));
      console.log("data", data);
      return data;
    } catch (error) {
      console.error("Error fetching item list:", error);
      return [];
    }
  }

  DeleteOrder(orderId: number) {
    return firstValueFrom(
      this.http.delete(environment.apiUrl + 'order/deletebyid/' + orderId, {
        responseType: 'text' as 'json'
      })
    );
  }
}
