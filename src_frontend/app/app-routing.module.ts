import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstPageComponent } from './component/first-page/first-page.component';
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {ClientComponent} from "./component/client/client.component";
import {AdminComponent} from "./component/admin/admin.component";
import {GuestComponent} from "./component/guest/guest.component";
import {PasswordComponent} from "./component/password/password.component";
import {SeatComponent} from "./component/seats/seat";
import {ReservationComponent} from "./component/reservation/reservation";
import {FavoritesComponent} from "./favorites/favorites";

const routes: Routes = [
  {path: '', component:FirstPageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'client', component: ClientComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'guest', component: GuestComponent},
  {path: 'reset', component: PasswordComponent},
  {path: 'first_page', component: FirstPageComponent},
  {path: 'seats', component: SeatComponent},
  {path: 'reservation', component: ReservationComponent},
  {path: 'favorites', component: FavoritesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
