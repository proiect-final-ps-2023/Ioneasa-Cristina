import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TicketService} from "../../service/Ticket.service";
import {Ticket} from "../../model/Ticket";
import {jsPDF} from "jspdf";

@Component({
  selector: 'app-guest',
  templateUrl: './reservation.html',
  styleUrls: ['./reservation.css']
})
export class ReservationComponent implements OnInit {

  ticketsList: Ticket[] = [];
  ticket: Ticket = new Ticket();
  ownerList: any;
  updateForm: FormGroup | undefined;

  constructor(private ticketService: TicketService,
              private formBuilder:FormBuilder,) { }

  ngOnInit(): void {
    this.ticketService.findByPassagerName(localStorage.getItem("name")).subscribe((res)=>{
        console.log(res);
        this.ticketsList = res;
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

  deleteTicket(id: any){
    console.log(id);
    this.ticketService.deleteTicket(id).subscribe((res:any)=>
    {
      alert("succes delete")
    },
      (_error)=>
      {
        alert("deteled failed")
      })
  }

  downloadTicket(id: any) {
    let pdf = new jsPDF();
    this.ticketService.generatePDF(id).subscribe((res: any) => {
      let text: any = res.text;
      console.log(text);
      pdf.text(text, 10, 10);
      pdf.save();
    });
  }

}
