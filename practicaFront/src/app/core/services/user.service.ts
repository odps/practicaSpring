import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {User} from '../../shared/interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAllUsers() {
    return this.http.get<any>(environment.apiUrl + '/user/list');
  }

  deleteUser(id: number) {
    this.http.delete<any>(environment.apiUrl + "/user/delete/" + id).subscribe();
  }

  updateUser(id: number, user: User) {
    return this.http.put<any>(environment.apiUrl + '/user/edit/' + id, user).subscribe();
  }

  saveUser(user: User) {
    return this.http.post<any>(environment.apiUrl + '/register', user);
  }

}
