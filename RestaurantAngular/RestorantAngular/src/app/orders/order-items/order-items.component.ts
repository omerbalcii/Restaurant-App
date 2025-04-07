import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {OrderItem} from '../../shared/order-item.model';
import {ItemService} from '../../shared/item.service';
import {Item} from '../../shared/item.model';
import {NgForm} from '../../../../node_modules/@angular/forms';
import {OrderService} from 'src/app/shared/order.service';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.css']
})
export class OrderItemsComponent implements OnInit{

  formData: OrderItem;
  itemList: Item[];
  isValid: boolean = true;


  constructor(@Inject(MAT_DIALOG_DATA) public data,
              public dialogRef: MatDialogRef<OrderItemsComponent>,
              private itemService: ItemService,
              private orderService :OrderService) { }


  ngOnInit(): void {

    this.itemService.getItemList()
      .then(res =>
        this.itemList = res as Item[]);
    console.log(this.itemList);
    console.log("this.data", this.data);

    if (this.data == null) {
      this.formData = {
        id: null,
        orderId: null,
        itemId: 0,
        itemName: '',
        price: 0,
        quantity: 0,
        total: 0
      }
    } else {
      this.formData = Object.assign({}, this.orderService.orderItemModel[this.data.orderItemIndex]);
    }
  }

  updatePrice(ctrl) {

    if (ctrl.selectedIndex == 0) {
      this.formData.price = 0;
      this.formData.itemName = '';
      this.formData.total = 0;
    } else {
      this.formData.price = this.itemList[ctrl.target.selectedIndex - 1].price;
      this.formData.itemName = this.itemList[ctrl.target.selectedIndex - 1].name;
      this.formData.total = 0;
    }
    this.updateTotal();
  }
  updateTotal() {
    if(this.formData.quantity==undefined || this.formData.price==undefined){
      this.formData.total=0;
    }else{
      this.formData.total = parseFloat((this.formData.quantity * this.formData.price).toFixed(2));
    }
  }

  onSubmit(form: NgForm) {
    if (this.validateForm(form.value)) {
      if (!this.data || this.data.orderItemIndex == null || this.data.orderItemIndex === undefined) {
        this.orderService.orderItemModel.push(form.value);
      } else {
        this.orderService.orderItemModel[this.data.orderItemIndex] = form.value;
      }
      console.log("this.orderService.orderItemModel=>", this.orderService.orderItemModel);
      this.dialogRef.close();
    }
  }


  validateForm(formData: OrderItem) {
    this.isValid = true;

    if (formData.itemId == 0)
      this.isValid = false;
    else if (formData.quantity == 0)
      this.isValid = false;
    return this.isValid;
  }
  close() {
    this.dialogRef.close();
  }
}
