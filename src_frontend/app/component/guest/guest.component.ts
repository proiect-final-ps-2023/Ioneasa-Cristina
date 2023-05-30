import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Flight} from "../../model/Flight";
import {FlightService} from "../../service/Flight.service";

@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.css']
})
export class GuestComponent implements OnInit {

  flightList: Flight[] = [];
  flight: Flight = new Flight();
  ownerList: any;
  updateForm: FormGroup | undefined;

  constructor(private flightService: FlightService,
              private formBuilder:FormBuilder,) { }

  ngOnInit(): void {
    this.flightService.findAll().subscribe((res)=>{
        console.log(res);
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

  initOwnerFlightsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      flightInput:[null, Validators.required]
    })
  }
}
