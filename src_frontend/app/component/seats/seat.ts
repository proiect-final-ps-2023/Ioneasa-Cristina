import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/User.service";
import {User} from "../../model/User";
import { Router } from '@angular/router';
import {Ticket} from "../../model/Ticket";
import {TicketService} from "../../service/Ticket.service";
import {FlightService} from "../../service/Flight.service";
import {Flight} from "../../model/Flight";

@Component({
  selector: 'app-seat',
  templateUrl: './seat.html',
  styleUrls: ['./seat.css']
})
export class SeatComponent implements OnInit {

  user: User = new User();
  ticket: Ticket = new Ticket();
  flight: Flight = new Flight();
  private seatsList: string[]=[];
  price: any = 0;

  constructor(private userService:UserService,
              private ticketService: TicketService,
              private flightService: FlightService,
              private router: Router) { }


    ngOnInit(): void {

      this.colorRed();
      const checkboxes = document.querySelectorAll('input[type=checkbox]');
      checkboxes.forEach(checkbox => {
        checkbox.addEventListener('click', () => {
          const inputElement = checkbox as HTMLInputElement;
          let ceva;
          if (inputElement.checked && !inputElement.classList.contains('purchased')) {
            console.log(checkbox.id);
            this.seatsList.push(checkbox.id);
            ceva = localStorage.getItem("price");
            if(ceva==null) ceva="";
            this.price += parseInt(ceva)
            this.f1();
          }
          if(!inputElement.checked && !inputElement.classList.contains('purchased'))
          {
            ceva = localStorage.getItem("price");
            if(ceva==null) ceva="";
            this.price -= parseInt(ceva);
            this.f1();
          }
        });
      });


    }

    colorRed()
    {
      this.ticketService.findByNumberFlight(localStorage.getItem("number_flight")
      ).subscribe
      ((response) =>
        {
          for (let i = 0; i < response.length; i++) {
            const ticket = response[i];
            var element;
            console.log(ticket.seat)
            if (typeof ticket.seat === "string") {
              element = document.getElementById(ticket.seat) as HTMLInputElement;
            }
            if (element) {
              element.nextElementSibling?.setAttribute("style", "background-color: red");
              element.classList.add('purchased')
            }
          }
        },
        (error) => {
        });

    }

    f1() {

      const parent = document.getElementById('total');

      const paragraph = document.getElementById('p');

      if (!paragraph) {
        const newParagraph = document.createElement('p');
        newParagraph.setAttribute('id', 'p');
        parent?.appendChild(newParagraph);
      } else {
        paragraph.textContent = '' + this.price;
        paragraph.setAttribute('style', ' position:absolute; left: 790px; top: 848px;');
      }
    }

    pay() {
      console.log(this.seatsList)


      for (let i = 0; i < this.seatsList.length; i++) {
        var seat = this.seatsList[i];
        var element;

        element = document.getElementById(seat) as HTMLInputElement;
        if (element) {
          element.nextElementSibling?.setAttribute("style", "background-color: red");
        }

        element.classList.add('purchased');

        this.ticketService.insertUser(localStorage.getItem("number_flight"), localStorage.getItem("email"), seat).subscribe
        ((response: any) => {
            console.log(response);
          },
          (error: any) => {
          });
      }
      this.seatsList=[]
    }

  insertTicket(seat:any){
    this.ticketService.insertUser(localStorage.getItem("number_flight"), localStorage.getItem("email"), seat).subscribe(data=>{
      alert("Rezerve successfully");
    }, error => {alert("Rezerve failed");
    })
  }

}
