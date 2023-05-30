import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../service/User.service';
import { User } from '../../model/User';

@Component({
  selector: 'app-register',
  templateUrl: 'register.component.html',
  styleUrls: ['register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  pass: String = "";

  constructor(
    private userService: UserService,
  ) {}

  ngOnInit(): void {}

  createUser() {
    this.userService.insertUser(this.user).subscribe(
      (data) => {
        alert('User created successfully');
        this.generateXmlFile(data);
      },
      (error) => {
        alert('User creation failed');
      }
    );
  }

  generateXmlFile(user: User) {
    const xmlData = this.generateXmlData(user); // Generate the XML data for the user

    const blob = new Blob([xmlData], { type: 'text/xml' });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = 'user_data.xml';
    link.click();
  }

  generateXmlData(user: User): string {

    const xmlString = `
      <user>
        <name>${user.name}</name>
        <email>${user.email}</email>
        <password>${this.pass}</password>

      </user>
    `;

    return xmlString;
  }
}
