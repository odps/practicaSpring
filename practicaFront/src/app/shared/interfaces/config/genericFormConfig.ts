import {ValidatorFn} from '@angular/forms';

export interface FormComponentConfig {
  formGroups: Array<{
    alias: string;
    class?: string;
    //Propiedades y atributos del group
    formControls: Array<{
      //Propiedades y atributos del input o 'control'
      name: string;
      validator?: ValidatorFn | ValidatorFn[];
      //tipo de dato, se puede mejorar haciendo clases
      type: string;
      // En caso de multiselect o dropdown
      options?: Array<{
        label: string;
        value: string;
      }>;
      //Atributos reservados para presentacion del input
      html?: {
        //Placeholder
        placeholder: string;
        //Class para modificar el contenedor del input
        class?: string;
        //Botones
        showButtons?: boolean;
        // En caso de numbers y strings
        max?: number;
        min?: number;
      }
    }>
  }>;

}
