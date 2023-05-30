import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../model/User";
import {Observable} from "rxjs";
import {Flight} from "../model/Flight";


@Injectable({
  providedIn: 'root'
})
export class UserService{
  baseURL: string = "http://localhost:8081/user";

  userInfo: any;
  userName: String | undefined;
  userEmail: String | undefined;

  constructor(private httpClient: HttpClient) {
  }

  loginUser(user: User):Observable<object> {
    console.log(user);
    return this.httpClient.post(this.baseURL + '/login', user);
  }

  loginAdmin(user: User):Observable<object> {
    console.log(user);
    return this.httpClient.post(this.baseURL + '/loginAdmin', user);
  }

  insertUser(user: User): Observable<User>{
    return this.httpClient.post<User>(this.baseURL + '/insertUser', user);
  }

  send_password(email: String):Observable<String> {
    return this.httpClient.post<String>(this.baseURL + '/send_password?email=' + email, null);
  }

  update_password(email:string, oldPassword: string, newPassword: string){
    console.log(email);
    console.log(oldPassword + newPassword);
    return this.httpClient.put<User>(this.baseURL + '/updatePassword?email=' + email + '&oldPassword=' + oldPassword + '&newPassword=' + newPassword, null);
  }

  findAllUsersLogged(){
    return this.httpClient.get<User[]>(this.baseURL + "/findByLogIn");
  }

  logout(email: any){
    return this.httpClient.post<User>(this.baseURL + "/logout?email=" + email, null);
  }

  addFavorite(email: any, number:any){
    return this.httpClient.post<Flight[]>(this.baseURL +"/addFavorite?number=" + number + "&email=" + email, null);
  }

  findFavorites(email: any){
    return this.httpClient.get<Flight[]>(this.baseURL + "/findFavorites?email=" + email);
  }

  removeFavorites(email: any, number:any){
    return this.httpClient.delete<Flight[]>(this.baseURL + "/removeFavorite?email=" + email + "&number=" + number);
  }
}
