import { Component } from '@angular/core';
import { RegisterDoctorService } from "../../services/register-doctor.service";
import { Router } from '@angular/router';

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
  };
  formDataStep2: any = {
    email: '',
    password: '',
    specialty: '',
    availability: '',
    phoneNumber: 0, // Agregado phoneNumber
    morning: false, // Agregado morning
    afternoon: false, // Agregado afternoon
    night: false, // Agregado night
    postalCode: 0, // Agregado postalCode
  };

  constructor(private registerDoctorService: RegisterDoctorService, private router: Router) { }

  goToStep(step: number) {
    this.currentStep = step;
  }

  goBack() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  submitForm() {
    const formData = {
      email: this.formDataStep2.email,
      password: this.formDataStep2.password,
      firstName: this.formDataStep1.firstName,
      secondName: this.formDataStep1.secondName,
      lastName: this.formDataStep1.lastName,
      DNI: this.formDataStep1.DNI,
      specialty: this.formDataStep2.specialty,
      dateOfBirth: this.formDataStep1.dateOfBirth,
      phoneNumber: Number(this.formDataStep2.phoneNumber), // Cambiado a phoneNumber
      morning: this.formDataStep2.morning, // Cambiado a morning
      afternoon: this.formDataStep2.afternoon, // Cambiado a afternoon
      night: this.formDataStep2.night, // Cambiado a night
      postalCode: Number(this.formDataStep2.postalCode), // Cambiado a postalCode
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

/**
 *
 * this.registerDoctorService.sendFormData(formData).subscribe(
      (response) => {
        console.log('Datos enviados correctamente:', response);
      },
      (error) => {
        console.error('Error al enviar los datos:', error);
      }
    )
 *
 */
