import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router, ActivatedRoute} from "@angular/router";
import {Flight} from "../../model/Flight";
import {FlightService} from "../../service/Flight.service";

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css']
})
export class FirstPageComponent implements OnInit {

  flightList: Flight[] = [];
  ownerList:any;
  updateForm:FormGroup | undefined;

  constructor(private flightService: FlightService,
              private formBuilder:FormBuilder,
              private router: Router,
              private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.flightService.findAll().subscribe((res)=>{
      console.log(res);
      this.flightList = res;
    },
      (_error)=> {
      });

    this.flightService.findByArrival("MILAN").subscribe();
    this.initOwnerCarsForm();
  }

  initOwnerCarsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      carInput:[null,Validators.required]
    })
  }

}
