import {Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import {Table, TableModule} from 'primeng/table';
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

  @ViewChild('dt') dt: Table | undefined;
  tableData: User[] = [];
  roles: Role[] = [];
  visible: boolean = false;
  editable: boolean = false;
  currentUser: User = JSON.parse(<string>localStorage.getItem('currentUser'));

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
  }

  ngOnInit() {
    this.updateTable();
    this.getRoles();
  }

  showForm(editable: boolean = false, user?: User) {
    this.createForm.reset();
    this.editable = editable;

    if (editable && user) {
      this.createForm.patchValue({
        id: user.userId,
        firstname: user.firstName,
        lastname: user.lastName,
        email: user.email,
        username: user.username,
        password: user.password,
        role: user.role,
      })
    }

    this.visible = true;

  }

  onSubmit() {
    if (this.editable) {
      this.updateUser();
    } else {
      this.saveUser()
    }
  }

  updateUser() {
    const user: User = {
      userId: this.createForm.value.id || 0,
      firstName: this.createForm.value.firstname || '',
      lastName: this.createForm.value.lastname || '',
      email: this.createForm.value.email || '',
      username: this.createForm.value.username || '',
      password: this.createForm.value.password || '',
      role: this.createForm.value.role ? this.createForm.value.role : undefined
    };

    console.log("Actualizando usuario: " + JSON.stringify(user));
    this.userService.updateUser(<number>user.userId, user).subscribe({
      next: (edit: boolean) => {
        this.updateTable()
      },
      error: () => {
      }
    });

    this.visible = false;
    this.createForm.reset();
  }

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

    console.log('Se ha generado un nuevo usuario');
    this.userService.saveUser(user).subscribe(response => {
      console.log('Usuario guardado correctamente: ', response);
    })

    this.visible = false;
    this.createForm.reset();
    this.updateTable();
  }

  deleteUser(id: number) {
    console.log("Se ha borrado: " + id);
    this.userService.deleteUser(id);
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

  applyFilterGlobal($event: any, stringVal: any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }


  protected readonly localStorage = localStorage;
}

