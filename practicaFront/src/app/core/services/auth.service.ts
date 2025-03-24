import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {User} from '../../shared/interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {
  }

  login(username: string, password: string) {
    const loginData = {
      username: username,
      password: password
    }
    return this.http.post<any>(`${environment.apiUrl}/login`, loginData);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  getUserRole(): any {
    let user: User = JSON.parse(<string>localStorage.getItem('currentUser'));
    return user.role.roleName;
  }


}
