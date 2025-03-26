import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) {
  }

  getRoles() {
    return this.http.get<any>(environment.apiUrl + '/role/list');
  }

  getRolesPaginated() {
    return this.http.get<any>(environment.apiUrl + '/role/pagedList');
  }
}
