import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    const loginData = {
      username: username,
      password: password
    }
    return this.http.post<any>(`${environment.apiUrl}/login`, loginData);
  }
}
