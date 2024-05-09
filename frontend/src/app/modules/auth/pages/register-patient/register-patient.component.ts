import { Component } from '@angular/core';
import { RegisterPatientService } from '../../services/register-patient.service'
import { Router } from '@angular/router';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-register-patient',
  templateUrl: './register-patient.component.html',
  styleUrls: ['./register-patient.component.scss'],
})
export class RegisterPatientComponent {

  formData: any = {
    email: '',
    password: '',
    firstName: '',
    secondName: '',
    lastName: '',
    DNI: '',
    phoneNumber: 0,
    dateOfBirth: '',
    aceptoTerminos: false
  };

  isDisabled: boolean = true;

  constructor(
    private registerPatientService: RegisterPatientService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.formData = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      firstName: ['', Validators.required],
      secondName: ['', Validators.required],
      lastName: ['', Validators.required],
      DNI: ['', [
        Validators.required,
        Validators.pattern(/^\d+$/),
        Validators.maxLength(9),
        Validators.minLength(7)
      ]],
      phoneNumber: ['', [
        Validators.required,
        Validators.pattern(/^\d+$/)
      ]],
      dateOfBirth: ['', [Validators.required, this.dateOfBirthValidator]],
      aceptoTerminos: [false, Validators.requiredTrue]
    });
    this.formData.valueChanges.subscribe(() => {
      this.isDisabled = !this.formData.valid;
    });
  }
  dateOfBirthValidator(control: any) {
    const currentDate = new Date();
    const selectedDate = new Date(control.value);
    return selectedDate < currentDate ? null : { futureDate: true };
  }

  get f() { return this.formData.controls; }


  submitForm() {

    if (this.formData.valid) {
      const formData = {
        email: this.formData.value.email,
        password: this.formData.value.password,
        firstName: this.formData.value.firstName,
        secondName: this.formData.value.secondName,
        lastName: this.formData.value.lastName,
        DNI: this.formData.value.DNI,
        phoneNumber: this.formData.value.phoneNumber ? Number(this.formData.value.phoneNumber) : null,
        dateOfBirth: this.formData.value.dateOfBirth,
        // aceptoTerminos: this.formData.value.aceptoTerminos
      };
      console.log(formData);
      this.registerPatientService.submitForm(formData).subscribe((response) => {
        // console.log('Datos enviados correctamente:', response);
        this.router.navigateByUrl('/auth/login');

      },
        (error) => {
          console.error('Error al enviar los datos:', error);
          alert('Datos incorrectos');
        }
      )
    } else {
      this.formData.markAllAsTouched();
    }
  }
}
