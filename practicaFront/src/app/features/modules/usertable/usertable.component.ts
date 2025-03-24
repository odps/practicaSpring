import {Component, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {User} from '../../../shared/interfaces/user';
import {UserService} from '../../../core/services/user.service';
import {InputText} from 'primeng/inputtext';
import {NgIf} from '@angular/common';
import {Dialog} from 'primeng/dialog';
import {Button} from 'primeng/button';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-usertable',
  imports: [
    TableModule,
    InputText,
    NgIf,
    Dialog,
    Button,
    FormsModule
  ],
  templateUrl: './usertable.component.html',
  styleUrl: './usertable.component.css'
})
export class UsertableComponent implements OnInit {

  tableData: User[] = [];
  currentUser: User = JSON.parse(<string>localStorage.getItem('currentUser'));
  visible: boolean = false;
  editableUser: User = JSON.parse(<string>localStorage.getItem('currentUser'));
  eventTrigger: String = '';

  constructor(private userService: UserService) {
  }


  ngOnInit() {
    this.userService.getAllUsers().subscribe(
      next => {
        this.tableData = next;
      },
      error => console.log(error)
    )
  }

  showDialog(user: User, eventTrigger: string) {
    eventTrigger = eventTrigger;
    this.visible = true;
    this.editableUser = user;
  }

  deleteUser(id: number) {
    console.log("Se ha borrado: " + id);
    this.userService.deleteUser(id);
  }

  updateUser() {
    this.visible = false;
    console.log("Usuario editado: " + this.editableUser.userId);
    this.userService.updateUser(this.editableUser.userId, this.editableUser);
  }

  protected readonly localStorage = localStorage;
}

