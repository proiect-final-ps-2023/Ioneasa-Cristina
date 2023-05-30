import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {jsPDF} from "jspdf";

import {TicketService} from "../service/Ticket.service";
import {Flight} from "../model/Flight";
import {FlightService} from "../service/Flight.service";
import {UserService} from "../service/User.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.html',
  styleUrls: ['./favorites.css']
})
export class FavoritesComponent implements OnInit {

  flightList: Flight[] = [];
  flight: Flight = new Flight();
  ownerList: any;
  updateForm: FormGroup | undefined;

  constructor(private userService: UserService,
              private formBuilder:FormBuilder,) { }

  ngOnInit(): void {
    this.userService.findFavorites(localStorage.getItem("email")).subscribe((res)=>{
        console.log(res);
        this.flightList = res;
      },
      (_error)=>{
      });

    this.initOwnerFlightsForm();
  }

  initOwnerFlightsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      ticketInput:[null, Validators.required]
    })
  }

  removeFavorite(number: any){
    console.log(number);
    console.log(localStorage.getItem("email"));
    this.userService.removeFavorites(localStorage.getItem("email"), number).subscribe((res:any)=>
      {
        alert("succes delete")
      },
      (_error)=>
      {
        alert("deteled failed")
      })
  }

}
