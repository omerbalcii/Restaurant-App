import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../shared/order.service';
import { Order } from '../../shared/order.model';
import { OrderItem } from '../../shared/order-item.model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { OrderItemsComponent } from '../order-items/order-items.component';
import { NgForm } from '@angular/forms';
import { CustomerService } from '../../shared/customer.service';
import { Customer } from '../../shared/customer.model';
import { Router } from '@angular/router';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  isValid: boolean = true;
  customerList: Customer[] = [];
  orderModel: Order = new Order();

  constructor(
    private orderService: OrderService,
    private dialog: MatDialog,
    private customerService: CustomerService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.resetForm();
    this.customerService.GetCustomerList().then(res => {
      this.customerList = res as Customer[];
    });
  }

  resetForm(form?: NgForm) {
    if (form) form.resetForm();

    this.orderModel = {
      orderId: 0,
      orderNo: Math.floor(100000 + Math.random() * 900000).toString(),
      customerId: 0,
      paymentMethod: '',
      grandTotal: 0,
      customerName: ''
    };

    this.orderService.orderItemModel = [];
  }

  AddOrEditOrderItem(orderItemIndex: number, orderId: number): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "500px";
    dialogConfig.data = { orderItemIndex, orderId };

    this.dialog.open(OrderItemsComponent, dialogConfig).afterClosed().subscribe(() => {
      this.updateGrandTotal();
    });
  }

  DeleteOrderItem(orderItemIndex: number, orderId: number): void {
    this.orderService.orderItemModel.splice(orderItemIndex, 1);
    this.updateGrandTotal();
  }

  updateGrandTotal(): void {
    this.orderModel.grandTotal = this.orderService.orderItemModel.reduce((prev, curr) => {
      return prev + curr.total;
    }, 0);
    this.orderModel.grandTotal = parseFloat(this.orderModel.grandTotal.toFixed(2));
  }

  validateForm(): boolean {
    this.isValid = true;
    if (!this.orderModel.customerId || this.orderModel.customerId === 0) {
      this.isValid = false;
    } else if (this.orderService.orderItemModel.length === 0) {
      this.isValid = false;
    }
    return this.isValid;
  }

  onSubmit(form: NgForm): void {
    if (this.validateForm()) {
      this.orderService.saveOrder(this.orderModel).subscribe({
        next: res => {
          this.resetForm(form);
          this.router.navigate(['/orders']);
          this.snackBar.open('Kayıt başarılı!', '', {
            duration: 3000,
            panelClass: ['snackbar-success']
          });
        },
        error: err => {
          console.error('Kayıt sırasında hata:', err);
          this.snackBar.open('Kayıt sırasında hata oluştu!', '', {
            duration: 3000,
            panelClass: ['snackbar-error']
          });
        }
      });
    }
  }



  get orderItemModel(): OrderItem[] {
    return this.orderService.orderItemModel;
  }
}
