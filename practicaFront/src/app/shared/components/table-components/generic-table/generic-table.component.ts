import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { PaginatedList } from '../../../interfaces/paginatedList';
import { Table, TableModule } from 'primeng/table';
import { TableConfig } from '../../../interfaces/config/tableConfig';
import { NgForOf, NgIf } from '@angular/common';
import { Button } from 'primeng/button';
import { dt } from '@primeng/themes';
import { InputText } from 'primeng/inputtext';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  imports: [TableModule, NgForOf, Button, NgIf, InputText],
  styleUrls: ['./generic-table.component.css'],
})
export class GenericTableComponent implements OnInit, OnChanges {
  @Input() config: TableConfig = {} as TableConfig;
  @Input() paginatedData: PaginatedList = {} as PaginatedList;

  @Output() onPageChange: EventEmitter<any> = new EventEmitter();
  @Output() onSortedChange: EventEmitter<any> = new EventEmitter();
  @Output() onFilterChange: EventEmitter<any> = new EventEmitter();
  @Output() onAction = new EventEmitter<{ action: string; item: any }>();

  @ViewChild('dt') dataTable: Table = dt();

  //Campos e informacion sobre la tabla.
  fields: Array<any> = [];
  content: Array<any> = [];
  alias: Array<any> = [];
  objects: Map<any, any> | undefined = new Map();

  //pagination
  paginated: boolean = false;
  rowSize: number[] | undefined;

  // Date filters
  dateFilters: Map<string, { after?: string; before?: string }> = new Map();

  constructor() {}

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

  onPagination(event: any) {
    // console.log(event)
    this.onPageChange.emit(event);
    console.log(this.paginatedData);
  }

  onSortedColumn(event: any) {
    let clicked = event.field;
    let position = this.alias.indexOf(clicked);
    this.onSortedChange.emit({
      sort: this.fields[position],
      page: this.paginatedData.pageable.pageNumber,
      rows: this.paginatedData.size,
      direction: event.order === 1 ? 'asc' : 'desc',
    });
  }

  onColumnFilter(event: any) {
    // console.log(event);
    this.onFilterChange.emit({
      info: event,
      pageData: {
        sort: '',
        page: this.paginatedData.pageable.pageNumber,
        rows: this.paginatedData.size,
        direction: 'asc',
      },
    });
  }

  clear() {
    this.dataTable.clear();
    this.dateFilters.clear();
  }

  checkType(type: any): string {
    return this.config.types[0][this.fields[this.alias.indexOf(type)]];
  }

  getField(field: any) {
    return this.fields[this.alias.indexOf(field)];
  }

  hasActionsColumn(): boolean {
    return this.alias.includes('Actions');
  }

  onActionClick(action: string, item: any) {
    this.onAction.emit({ action, item });
  }

  getMatchMode(field: any) {
    if (this.config.matchMode) {
      return this.config.matchMode[0][this.getField(field)];
    } else return undefined;
  }

  onInputChange(event: any, value: any, matchMode: string) {
    this.onFilterChange.emit({
      field: this.getField(value),
      matchMode: matchMode || 'contains',
      value: event.target.value,
    });
  }

  onDateAfterChange(event: any, field: string) {
    const fieldName = this.getField(field);

    if (!this.dateFilters.has(fieldName)) {
      this.dateFilters.set(fieldName, {});
    }

    const filterValue = this.dateFilters.get(fieldName);
    filterValue!.after = event.target.value;

    this.emitDateFilter(fieldName);
  }

  onDateBeforeChange(event: any, field: string) {
    const fieldName = this.getField(field);

    if (!this.dateFilters.has(fieldName)) {
      this.dateFilters.set(fieldName, {});
    }

    const filterValue = this.dateFilters.get(fieldName);
    filterValue!.before = event.target.value;

    this.emitDateFilter(fieldName);
  }

  emitDateFilter(fieldName: string) {
    const filterValue = this.dateFilters.get(fieldName);

    this.onFilterChange.emit({
      field: fieldName,
      type: 'date',
      value: {
        after: filterValue?.after,
        before: filterValue?.before,
      },
      pageData: {
        sort: '',
        page: this.paginatedData.pageable.pageNumber,
        rows: this.paginatedData.size,
        direction: 'asc',
      },
    });
  }
}
