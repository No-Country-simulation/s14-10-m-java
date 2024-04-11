import { Component } from '@angular/core';

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
    dateOfBirth: ''
  };


  submitForm(form: any) {
    console.log(this.formData); // Aquí puedes enviar los datos a un servicio o realizar otras acciones
    // form.resetForm();
  }
}
