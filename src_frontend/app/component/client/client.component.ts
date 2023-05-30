import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Flight} from "../../model/Flight";
import {FlightService} from "../../service/Flight.service";
import {Ticket} from "../../model/Ticket";
import {TicketService} from "../../service/Ticket.service";
import {User} from "../../model/User";
import {UserService} from "../../service/User.service";
import {ActivatedRoute, Router} from "@angular/router";
import {webSocket} from "rxjs/webSocket";


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  message=" ";
  flightList: Flight[] = [];
  ownerList: any;
  updateForm: FormGroup | undefined;
  flight: Flight = new Flight();
  ticket: Ticket = new Ticket();
  user: User = new User();
  userInfo: String = this.userService.userInfo;
  subject = webSocket('ws://localhost:8889/');

  constructor(private flightService: FlightService,
              private ticketService: TicketService,
              private userService: UserService,
              private formBuilder:FormBuilder,
              private router: Router,) { }

  ngOnInit(): void {
    this.flightService.findAll().subscribe((res)=>{
      //console.log(res);
      this.flightList = res;
    },
      (_error)=>{
      });

    this.initOwnerFlightsForm();
  }

  findByArrival(arrival: any){
    this.flightService.findByArrival(arrival).subscribe((res)=>{
        console.log(res);
        this.flightList = res;
      },
      (_error)=>{
      });
  }

  findByDeparture(departure: any){
    this.flightService.findByDeparture(departure).subscribe((res)=>{
      console.log(res);
      this.flightList = res;
    },
      (_error)=>{
    });
  }

  sortByPrice(){
    this.flightService.sortByPrice().subscribe((res)=>{
        console.log(res);
        this.flightList = res;
      },
      (_error)=>{
      });
  }

  initOwnerFlightsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      flightInput:[null, Validators.required]
    })
  }

  logout(){
    this.userService.logout(localStorage.getItem("email")).subscribe(data => {
      alert("Logout succesfully");
    },error => {alert("failed");
    })
  }

  reserve(flight_number: any, price: any){
      localStorage.setItem("number_flight", flight_number);
      localStorage.setItem("price", price);
      this.router.navigateByUrl('/seats');
  }

  sendToServer(event: any){
    this.subject.subscribe();
    this.subject.next(this.message);
    this.subject.complete();
  }

  removeFavorite(number: any){
    console.log(number);
    this.userService.addFavorite(localStorage.getItem("email"), number).subscribe(data=>{
      alert("added successfully");
    }, error => {alert("add failed");
    })
  }
}
