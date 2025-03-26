import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {PaginatedList} from '../../../interfaces/paginatedList';
import {TableModule} from 'primeng/table';
import {TableConfig} from '../../../interfaces/config/tableConfig';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  imports: [
    TableModule,
    NgForOf
  ],
  styleUrls: ['./generic-table.component.css']
})

export class GenericTableComponent implements OnInit, OnChanges {
  @Input() config: TableConfig = {} as TableConfig;
  @Input() paginatedData: PaginatedList = {} as PaginatedList;

  @Output() onPageChange: EventEmitter<any> = new EventEmitter();
  @Output() onSortedChange: EventEmitter<any> = new EventEmitter();

  //Campos e informacion sobre la tabla.
  fields: Array<any> = [];
  content: Array<any> = [];
  alias: Array<any> = [];
  objects: Map<any, any> | undefined = new Map();

  //pagination
  paginated: boolean = false;
  rowSize: number[] | undefined;

  //

  constructor() {
  }

  ngOnInit() {
    this.fields = this.config.fields;
    this.alias = this.config.alias;
    this.objects = this.config.objects;

    //pagination
    this.paginated = this.config.pagination.paginated;
    this.rowSize = this.config.pagination.rows;
    this.content = this.paginatedData.content;
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['paginatedData']) {
      this.content = this.paginatedData.content;
    }
  }

  objectCheck(object: any, field: string): any {
    let obj = object[field];
    let result = this.objects?.get(field);
    if (result !== undefined) {
      return obj[result];
    } else return obj;
  }

  onLazyLoad(event: any) {
    // console.log(event)
    this.onPageChange.emit(event);
  }

  onSortedColumn(event: any) {
    // console.log('onSortedColumn', event);
    let clicked = event.field;
    let position = this.alias.indexOf(clicked);
    // console.log(this.fields[position]);
    this.onSortedChange.emit({
      filter: this.fields[position],
      page: this.paginatedData.pageable.pageNumber,
      column: this.paginatedData.size
    });

  }
}
