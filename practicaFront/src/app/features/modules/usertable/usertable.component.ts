import {Component, OnChanges, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {User} from '../../../shared/interfaces/user';
import {UserService} from '../../../core/services/user.service';
import {InputText} from 'primeng/inputtext';
import {NgForOf, NgIf} from '@angular/common';
import {Dialog} from 'primeng/dialog';
import {Button} from 'primeng/button';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Role} from '../../../shared/interfaces/role';
import {RoleService} from '../../../core/services/role.service';

@Component({
  selector: 'app-usertable',
  imports: [
    TableModule,
    InputText,
    NgIf,
    Dialog,
    Button,
    FormsModule,
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './usertable.component.html',
  styleUrl: './usertable.component.css'
})
export class UsertableComponent implements OnInit, OnChanges {

  tableData: User[] = [];
  roles: Role[] = [];
  visible: boolean = false;
  currentUser: User = JSON.parse(<string>localStorage.getItem('currentUser'));
  editableUser: User = JSON.parse(<string>localStorage.getItem('currentUser'));

  createForm = new FormGroup({
    id: new FormControl(0),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    role: new FormControl()
  })

  constructor(private userService: UserService, private roleService: RoleService) {
  }


  ngOnChanges() {
    this.updateTable()
  }

  ngOnInit() {
    this.updateTable();
    this.getRoles();
  }

  showUpdate(user: User) {
    this.createForm.reset();
    this.visible = true;
    this.editableUser = user;
  }

  showCreate() {
    this.createForm.reset();
    this.visible = true;
  }

  deleteUser(id: number) {
    console.log("Se ha borrado: " + id);
    this.userService.deleteUser(id);
  }

  // updateUser() {
  //   this.visible = false;
  //   console.log("Usuario editado: " + this.editableUser.userId);
  //   this.userService.updateUser(this.editableUser.userId, this.editableUser);
  // }

  saveUser() {
    const user: User = {
      userId: this.createForm.value.id || 0,
      firstName: this.createForm.value.firstname || '',
      lastName: this.createForm.value.lastname || '',
      email: this.createForm.value.email || '',
      username: this.createForm.value.username || '',
      password: this.createForm.value.password || '',
      role: this.createForm.value.role ? this.createForm.value.role : undefined
    };

    console.log(JSON.stringify(user));
    console.log(user.role?.id + ": " + user.role?.roleName);

    this.visible = false;
    console.log('Se ha generado un nuevo usuario');
    this.userService.saveUser(user).subscribe(response => {
      console.log('Usuario guardado correctamente: ', response);
    })
    this.createForm.reset();
    this.updateTable();
  }

  updateTable() {
    this.userService.getAllUsers().subscribe(
      next => {
        this.tableData = next;
      },
      error => console.log(error)
    )
  }

  getRoles() {
    this.roleService.getRoles().subscribe(
      next => {
        this.roles = next;
      }, error => {
        console.log(error);
      }
    )
  }

  protected readonly localStorage = localStorage;
}

