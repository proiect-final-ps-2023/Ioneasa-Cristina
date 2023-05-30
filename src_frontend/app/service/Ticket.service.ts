import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../model/Ticket";
import {Flight} from "../model/Flight";

@Injectable({
  providedIn: 'root'
})
export class TicketService{
  baseURL: string = "http://localhost:8081/ticket";

  constructor(private httpClient: HttpClient) {
  }

  insertUser(number: any, email:any, seat:any){
    return this.httpClient.put<Ticket>(this.baseURL + '/insertTicket?number=' + number + '&email=' + email + '&seat=' +seat, null);
  }

  findByPassagerName(name: any){
    return this.httpClient.get<Ticket[]>(this.baseURL + "/findByPassagerName?passagerName=" + name);
  }

  findByNumberFlight(number: any){
    return this.httpClient.get<Ticket[]>(this.baseURL + "/findByFlight?number_flight=" + number);
  }

  deleteTicket(id: any){
    console.log(id);
    return this.httpClient.delete<Ticket>(this.baseURL + "/deleteTicket?noTicket=" + id);
  }

  generatePDF(id: any): Observable<any> {
    return this.httpClient.get<any>(this.baseURL + "/ticketDetails?id=" + id);
  }

}
