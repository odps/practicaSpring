import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormComponentConfig} from '../../interfaces/config/genericFormConfig';
import {NgForOf, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault} from '@angular/common';
import {InputText} from 'primeng/inputtext';
import {IftaLabel} from 'primeng/iftalabel';
import {DropdownModule} from 'primeng/dropdown';
import {Select} from 'primeng/select';
import {InputNumber} from 'primeng/inputnumber';
import {DatePicker} from 'primeng/datepicker';
import {MultiSelect} from 'primeng/multiselect';
import {RadioButton} from 'primeng/radiobutton';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-form-component',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgForOf,
    InputText,
    IftaLabel,
    DropdownModule,
    Select,
    NgSwitchCase,
    NgSwitch,
    NgSwitchDefault,
    InputNumber,
    DatePicker,
    MultiSelect,
    RadioButton,
    Button,
    Message,
    NgIf
  ],
  templateUrl: './form-component.component.html',
  styleUrl: './form-component.component.css'
})

export class FormComponentComponent implements OnInit, OnChanges {

  @Input() formConfig: FormComponentConfig = {formGroups: []};
  formArray: Map<any, any> = new Map();

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.configFormBuilder();
    // console.log(this.formArray);
    // console.log(this.formConfig.formGroups[0].formControls[3].options);
    // console.log(this.formConfig);
  }

  ngOnChanges(changes: SimpleChanges): void {
  }

  configFormBuilder() {
    for (let group of this.formConfig.formGroups) {
      //Generamos un form group vacio para recoger los datos de config
      let config: FormGroup = this.formBuilder.group({});
      //Iteramos por objeto dentro del array formGroups
      for (let control of group.formControls) {
        //Dentro de cada formGroup iteramos sobre su control
        let ctl: FormControl<any> = new FormControl();
        if (control.validator != undefined) {
          ctl.addValidators(control.validator);
        }
        //Asignamos al formgroup vacio dicho control, con su validator en caso de existir
        config.controls[control.name] = ctl;
      }
      //Introducimos el nuevo formgroup dentro de nuestro Mapa junto a su alias
      //antes de empezar una nueva iteracion
      this.formArray.set(group.alias, config);
    }
  }

  getFormGroup(alias: any) {
    // console.log(alias);
    return this.formArray.get(alias)
  }

  onSubmit(event: any) {
    // console.log(event);
  }

  isValid(form: any) {
    // console.log(form)
    // console.log(this.formArray)
  }

  getFormControl(alias: string, name: string) {
    // console.log(this.getFormGroup(alias).controls[name]);
    return this.getFormGroup(alias).controls[name].errors;
  }
}
