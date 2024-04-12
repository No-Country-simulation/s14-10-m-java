import { Component } from '@angular/core';
import { RegisterPatientService } from '../../services/register-patient.service'

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


  constructor(private registerPatientService: RegisterPatientService) { }

  submitForm(form: any) {
    console.log(this.formData); // Aquí puedes enviar los datos a un servicio o realizar otras acciones
    // form.resetForm();
    this.registerPatientService.submitForm(this.formData).subscribe((response) => {
      console.log('Datos enviados correctamente:', response);
      // Aquí puedes realizar acciones adicionales después de enviar los datos, como mostrar un mensaje de éxito
      alert("Registro exitoso");
    },
      (error) => {
        console.error('Error al enviar los datos:', error);
        // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario
        alert('Datos incorrectos');
      }
    )
  }
}
