import { Component } from '@angular/core';
import { RegisterDoctorService } from "../../services/register-doctor.service";
import { Router } from '@angular/router';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-register-doctor',
  templateUrl: './register-doctor.component.html',
  styleUrls: ['./register-doctor.component.scss'],
})
export class RegisterDoctorComponent {
  currentStep: number = 1;

  formDataStep1: any = {
    firstName: '',
    secondName: '',
    lastName: '',
    DNI: '',
    dateOfBirth: '',
    address: ''
  };
  formDataStep2: any = {
    email: '',
    password: '',
    specialty: '',
    availability: '',
    phoneNumber: 0,
    morning: false,
    afternoon: false,
    night: false,
    postalCode: 0,
    licenseNumber: 0
  };
  // formDataStep1: FormGroup | any;
  // formDataStep2: FormGroup | any;

  constructor(
    private registerDoctorService: RegisterDoctorService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) {
    this.formDataStep1 = this.formBuilder.group({
      firstName: ['', Validators.required],
      secondName: ['', Validators.required],
      lastName: ['', Validators.required],
      DNI: ['', [Validators.required, Validators.pattern(/^[0-9]{7}$/)]],
      dateOfBirth: ['', [Validators.required, this.dateOfBirthValidator]],
      address: ['', Validators.required]
    });

    this.formDataStep2 = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      specialty: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      morning: [false],
      afternoon: [false],
      night: [false],
      postalCode: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      licenseNumber: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]]
    });
  }

  dateOfBirthValidator(control: any) {
    const currentDate = new Date();
    const selectedDate = new Date(control.value);
    return selectedDate < currentDate ? null : { futureDate: true };
  }

  goToStep(step: number) {
    this.currentStep = step;
  }

  goBack() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  submitForm() {
    if (this.formDataStep1.valid && this.formDataStep2.valid) {
      const formData = {
        email: this.formDataStep2.value.email,
        password: this.formDataStep2.value.password,
        firstName: this.formDataStep1.value.firstName,
        secondName: this.formDataStep1.value.secondName,
        lastName: this.formDataStep1.value.lastName,
        DNI: this.formDataStep1.value.DNI,
        specialty: this.formDataStep2.value.specialty,
        dateOfBirth: this.formDataStep1.value.dateOfBirth,
        phoneNumber: Number(this.formDataStep2.value.phoneNumber),
        morning: this.formDataStep2.value.morning,
        afternoon: this.formDataStep2.value.afternoon,
        night: this.formDataStep2.value.night,
        postalCode: Number(this.formDataStep2.value.postalCode),
        licenseNumber: Number(this.formDataStep2.value.licenseNumber),
        address: this.formDataStep1.value.address
      };
      console.log(formData);
      this.registerDoctorService.sendFormData(formData).subscribe(
        (response) => {
          console.log('Datos enviados correctamente:', response); this.router.navigateByUrl('/auth/login');
        },
        (error) => {
          console.error('Error al enviar los datos:', error);
        }
      )
    }
  }
}
