import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment.prod';
import {lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  async getItemList() {
    try {
      const data = await lastValueFrom(this.http.get(environment.apiUrl + 'item/getall'));
      console.log("itemm data", data);
      return data;
    } catch (error) {
      console.error("Error fetching item list:", error);
      return [];
    }
  }
}
