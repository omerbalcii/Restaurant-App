import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  // GetCustomerList() {
  //   var data =  this.http.get(environment.apiUrl + 'customers/getall').toPromise();
  //   return data;
  // }

  async GetCustomerList() {
    try {
      const data = await lastValueFrom(this.http.get(environment.apiUrl + 'customers/getAllDto'));
      console.log("data", data);
      return data;
    } catch (error) {
      console.error("Error fetching item list:", error);
      return [];
    }
  }
}
