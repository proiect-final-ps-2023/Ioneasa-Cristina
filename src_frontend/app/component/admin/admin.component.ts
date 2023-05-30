import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Flight} from "../../model/Flight";
import {FlightService} from "../../service/Flight.service";
import {User} from "../../model/User";
import {UserService} from "../../service/User.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  flight: Flight = new Flight();
  usersList: User[] = [];
  updateForm: FormGroup | undefined;

  constructor(private flightService: FlightService,
              private userService: UserService,
              private formBuilder:FormBuilder,) { }

  ngOnInit(): void {
    this.userService.findAllUsersLogged().subscribe((res)=>{
        console.log(res);
        this.usersList = res;
      },
      (_error)=>{
      });

    this.initOwnerFlightsForm();
  }

  initOwnerFlightsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      userInput:[null, Validators.required]
    })
  }

  insertFlight(){
    this.flightService.insertFlight(this.flight).subscribe(data=>{
      alert("Flight created successfully");
    }, error => {alert("Insert failed");
    })
  }

  deleteFlight(id: any){
    this.flightService.deleteFlight(id).subscribe(data=>{
      alert("Flight deleted successfully");
    }, error => {alert("Deleted failed");
    })
  }
}
