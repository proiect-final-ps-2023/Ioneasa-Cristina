import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstPageComponent } from './component/first-page/first-page.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from '@angular/material/list';
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {ClientComponent} from "./component/client/client.component";
import {AdminComponent} from "./component/admin/admin.component";
import {GuestComponent} from "./component/guest/guest.component";
import {PasswordComponent} from "./component/password/password.component";
import {SeatComponent} from "./component/seats/seat";
import {ReservationComponent} from "./component/reservation/reservation";
import {MatIconModule} from "@angular/material/icon";
import {FavoritesComponent} from "./favorites/favorites";

@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    LoginComponent,
    RegisterComponent,
    ClientComponent,
    AdminComponent,
    GuestComponent,
    PasswordComponent,
    SeatComponent,
    ReservationComponent,
    FavoritesComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatListModule,
    FormsModule,
    AppRoutingModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
