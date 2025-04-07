import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customer : string = "Restaurant App Deneme Test";
  constructor() { }

  ngOnInit(): void {
  }
}
