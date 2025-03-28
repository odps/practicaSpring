import {Component, OnInit} from '@angular/core';
import {TableConfig} from '../../../shared/interfaces/config/tableConfig';
import {UserService} from '../../../core/services/user.service';
import {PaginatedList} from '../../../shared/interfaces/paginatedList';
import {GenericTableComponent} from '../../../shared/components/table-components/generic-table/generic-table.component';
import {NgIf} from '@angular/common';
import {HeaderComponent} from '../../../shared/components/header/header.component';
import {RoleService} from '../../../core/services/role.service';

@Component({
  selector: 'app-main2',
  imports: [GenericTableComponent, NgIf, HeaderComponent],
  templateUrl: './main2.component.html',
  styleUrl: './main2.component.css',
})
export class Main2Component implements OnInit {
  loading: boolean = true;
  //Respuesta del servidor en formato Page de Spring Boot
  paginatedData: PaginatedList = {} as PaginatedList;
  //Para usuarios
  tableConfig: TableConfig = {
    fields: ['userId', 'firstName', 'lastName', 'email', 'role', 'createdAt'],
    alias: [
      'Id',
      'First Name',
      'Last Name',
      'Email',
      'Role',
      'Registration Date',
      'Actions',
    ],
    objects: new Map([['role', 'roleName']]),
    types: [
      {
        userId: 'numeric',
        firstName: 'text',
        lastName: 'text',
        email: 'text',
        role: 'text',
        createdAt: 'date',
      },
    ],
    matchMode: [
      {
        firstName: 'likeName',
        lastName: 'likeLastName',
        email: 'likeEmail',
        createdAt: 'createdBefore',
      },
    ],
    pagination: {
      paginated: true,
      rows: [5, 10, 15],
      sortParams: ['userId', 'firstName', 'lastName', 'email', 'role'],
    },
    buttons: [
      {
        action: 'View',
        icon: 'pi pi-eye',
        styleClass: 'p-button-info p-button-sm m-1 p-2',
      },
      {
        action: 'Delete',
        icon: 'pi pi-eye',
        styleClass: 'p-button-danger p-button-sm m-1 p-2',
      }
    ],
  };

  //Para roles
  // tableConfig: TableConfig = {
  //   fields: ["id", "roleName"],
  //   alias: ["Id", "Tipo de Rol"],
  // pagination: {
  //   paginated: true,
  //   rows: [5, 10, 15]
  // }
  // };

  constructor(
    private userService: UserService,
    private roleService: RoleService
  ) {
  }

  ngOnInit() {
    this.getUsers();
    // this.getRoles();
  }

  private getUsers() {
    this.userService.getAllUsersPaginated().subscribe(
      (response) => {
        this.paginatedData = response;
        this.loading = false;
      },
      (error) => console.log(error)
    );
  }

  private getRoles() {
    this.roleService.getRolesPaginated().subscribe(
      (response) => {
        this.paginatedData = response;
        this.loading = false;
      },
      (error) => console.log(error)
    );
  }

  onPageChange(event: any) {
    // console.log(event)
    let page = event.first / event.rows;
    // console.log("Estoy en la pagina: " + page)
    this.userService.getAllUsersPaginated(page, event.rows).subscribe(
      (response) => {
        this.paginatedData = response;
        // console.log(this.paginatedData);
      },
      (error) => console.log(error)
    );
  }

  onSortedChange(event: any) {
    const page = event.page;
    const size = event.rows;
    const sort = event.sort;
    const direction = event.direction;
    // console.log(event);
    this.userService
      .getAllUsersPaginated(page, size, sort, direction)
      .subscribe(
        (response) => {
          this.paginatedData = response;
        },
        (error) => console.log(error)
      );
  }

  // onFilterChange(event: any) {
  //   console.log(event);
  //
  //   let sort = event.pageData.sort;
  //   let page = event.pageData.page;
  //   let rows = event.pageData.rows;
  //   let direction = event.pageData.direction;
  //
  //   let preparedQuery;
  //
  //   // event.info.filters.forEach((item: any) => {
  //   //
  //   // })
  //
  //   this.userService
  //     .getAllUsersPaginated(page, rows, sort, direction, preparedQuery)
  //     .subscribe(
  //       (response) => {
  //         this.paginatedData = response;
  //       },
  //       (error) => console.log(error)
  //     );
  // }

  onFilterChange(event: any) {
    console.log(event);
    let filter = [{field: event.field, value: event.value}];
    this.userService.getAllUsersPaginated(0, 5, undefined, undefined, filter).subscribe(
      (response) => {
        this.paginatedData = response;
      }
    )
  }

  handleAction(event: any) {
    // console.log(event);

    switch (event.action) {
      case 'delete':
        alert('Borrando user');
        break;
      case 'view':
        alert('Viendo user');
        break;
      default:
        console.log('Accion desconocida:', event.action);
    }
  }
}
