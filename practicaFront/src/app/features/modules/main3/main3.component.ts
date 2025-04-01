import {Component} from '@angular/core';
import {FormComponentComponent} from '../../../shared/components/form-component/form-component.component';
import {FormComponentConfig} from '../../../shared/interfaces/config/genericFormConfig';
import {Validators} from '@angular/forms';

@Component({
  selector: 'app-main3',
  imports: [
    FormComponentComponent
  ],
  templateUrl: './main3.component.html',
  styleUrl: './main3.component.css'
})
export class Main3Component {
  formConfig: FormComponentConfig = {
    formGroups: [
      {
        alias: "userForm",
        formControls: [
          {
            name: 'firstName',
            validator: [Validators.required, Validators.minLength(5)],
            type: "text",
            html: {
              placeholder: "Name",
            },
          },
          {
            name: 'lastName',
            validator: Validators.required,
            type: "text",
            html: {
              placeholder: "Last Name",
            }
          },

          {
            name: 'birthDate',
            validator: Validators.required,
            type: "date",
            html: {
              placeholder: "Birth Date",
            },
          },
          {
            name: 'gender',
            validator: Validators.required,
            type: "select",
            options: [
              {label: 'Male', value: 'male'},
              {label: 'Female', value: 'female'},
              {label: 'Other', value: 'other'},
            ],
            html: {
              placeholder: "Gender",
            },
          },
          {
            name: 'city',
            validator: [Validators.required, Validators.email],
            type: "multiselect",
            options: [
              {label: 'Madrid', value: 'Madrid'},
              {label: 'Barcelona', value: 'Barcelona'},
              {label: 'Valencia', value: 'Valencia'},
            ],
            html: {
              placeholder: "City",
            }
          },
          {
            name: 'email',
            validator: [Validators.required, Validators.email],
            type: "email",
            html: {
              placeholder: "Email",
            }
          },

          {
            name: 'phoneNumber',
            validator: Validators.required,
            type: "number",
            html: {
              placeholder: "Phone Number",
              min: 0,
              max: 999999999,
              showButtons: true,
            },
          },
        ]
      },
      {
        alias: "registerForm",
        formControls: [
          {
            name: 'username',
            validator: Validators.required,
            type: "text",
            html: {
              placeholder: "Username",
            }
          },
          {
            name: 'password',
            validator: [Validators.required, Validators.minLength(4)],
            type: "password",
            html: {
              placeholder: "Password",
            }
          },
          {
            name: 'confirmPassword',
            validator: Validators.required,
            type: "password",
            html: {
              placeholder: "Confirm Password",
            }
          },
          {
            name: 'userAgreement',
            validator: Validators.required,
            type: "radiobutton",
            options: [
              {label: 'Agree', value: "yes"},
              {label: 'Disagree', value: "no"},
            ],
            html: {
              placeholder: "User Agreement",
            }
          },
        ]
      }
    ]
  }

}
