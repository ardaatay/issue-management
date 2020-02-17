import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    return this.http.post<any>(environment.API_BASE_PATH + '/token', {username, password})
      .pipe(map(user => {
        if (user && user.token) {
          localStorage.setItem('currentUser', JSON.stringify(user));
        }

        return user;
      }));
  }

  register(registerForm) {
    return this.http.post(environment.API_BASE_PATH + '/token/register', registerForm)
      .pipe(map(response => {
        return response
      }));
  }

  logout() {
    localStorage.removeItem('currentUser');
  }
}
