import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/User.service";
import {User} from "../../model/User";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  user: User = new User();
  oldPassword: any;
  newPassword: any;


  constructor(private userService:UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  sendEmail(email: any){
    this.userService.send_password(email).subscribe()
  }

  updatePassword(email:any, oldPassword: any, newPassword: any) {
    console.log(oldPassword);
    console.log(newPassword);
    this.userService.update_password(email, oldPassword, newPassword).subscribe(data=>{
      alert("Password changed successfully");
    }, error => {alert("failed");
    })
  }

}
