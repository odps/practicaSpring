import {paginationConfig} from './pagination-config';

export interface TableConfig {
  fields: Array<any>;
  alias: Array<any>;
  objects?: Map<any, any>;
  pagination: paginationConfig;
}

/*Objecto configurador de tablas:
* fields: hace referencia a las propiedades del objeto
* alias: Lo que se va a mostrar en el header de la tabla
* objects: en caso de enviar un objeto, se define el field y la propiedad a acceder
* */
