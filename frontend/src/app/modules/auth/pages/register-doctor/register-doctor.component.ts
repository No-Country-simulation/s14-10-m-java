import { Component } from '@angular/core';

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
  };

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
      //  ...this.formDataStep1,
      //  ...this.formDataStep2
      email: this.formDataStep2.email,
      password: this.formDataStep2.password,
      firstName: this.formDataStep1.firstName,
      secondName: this.formDataStep1.secondName,
      lastName: this.formDataStep1.lastName,
      DNI: this.formDataStep1.DNI,
      specialty: this.formDataStep2.specialty,
      dateOfBirth: this.formDataStep1.dateOfBirth,
      availability: this.formDataStep2.availability,
    };
    console.log(formData);
  }
}
