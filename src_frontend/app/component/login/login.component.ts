import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/User.service";
import {User} from "../../model/User";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();


  constructor(private userService:UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  loginUser() {
    this.userService.loginUser(this.user).subscribe((data:any) => {
        alert("User login successfully");

        //console.log(this.user?.email);
        //console.log(data);
        //console.log(data.name)
        localStorage.setItem("name", data.name);

      if (typeof this.user?.email === "string") {
        localStorage.setItem("email", this.user?.email);
      }
      this.router.navigateByUrl('/client');

      /*this.userService.userInfo = JSON.stringify(data);
      const pattern = /"name"\s*:\s*"([^"]+)"/;
      const match = this.userService.userInfo.match(pattern);
      //console.log(this.userService);
      if (match) {
        this.userService.userName = match[1];
        //console.log(this.userService.userName);
      } else {
        //console.log("name not found");
      }
      const regex = /"username"\s*:\s*"([^"]+)"/;
      const match2 = this.userService.userInfo.match(regex);
      if (match2) {
        this.userService.userEmail = match2[1];
       // console.log(this.userService.userEmail);
      } else {
       // console.log("email not found");
      }*/

    }, error => {

    })
  }

  loginAdmin() {
    this.userService.loginAdmin(this.user).subscribe(data=>{
      alert("Admin login successfully");
      this.router.navigateByUrl('/admin');
    },error => {
      alert("Login failed");
    })
  }
}
