import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Flight} from "../model/Flight";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class FlightService{
  baseURL: string = "http://localhost:8081/flight";

  constructor(private httpClient: HttpClient) {}

  findAll(){
    return this.httpClient.get<Flight[]>(this.baseURL + "/findAll");
  }

  insertFlight(flight: Flight):Observable<Flight>{
    return this.httpClient.put<Flight>(this.baseURL + "/insertFlight", flight);
  }

  deleteFlight(id: any){
    //let params = new HttpParams().set('id', id);
    // @ts-ignore
    return this.httpClient.delete<Flight>(this.baseURL + "/deleteFlight?id=" + id)
  }

  findById(id:number){
    let params = new HttpParams().set('id', id);
    return this.httpClient.get(this.baseURL + "/findById" + {params: params})
  }

  findByArrival(arrival: string){
    return this.httpClient.get<Flight[]>(this.baseURL + "/findByArrival?arrival=" + arrival)
  }

  findByDeparture(departure: string){
    return this.httpClient.get<Flight[]>(this.baseURL + "/findByDeparture?departure=" + departure);
  }
  sortByPrice(){
    return this.httpClient.get<Flight[]>(this.baseURL + "/sort");
  }

}
