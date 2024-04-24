import { Component, EventEmitter, Output } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-edit-assistant-form',
  templateUrl: './edit-assistant-form.component.html',
  styleUrls: ['./edit-assistant-form.component.scss']
})
export class EditAssistantFormComponent {
  @Output() 
  close = new EventEmitter();
  

  NumDNI: number | undefined;
  FirsName: string | undefined;
  SecondName: string | undefined;
  LastName: string | undefined;
  DateOfBirth: string | undefined;
  PhoneNumber: number | null | undefined;

  constructor(private assistentService: AssistentService){}

  closePopup() {
    this.close.emit();
  }

  
  confirmFormData(){
    if (this.isFormComplete()) {
    let formData = {
  
      id: sessionStorage.getItem('id'),
    firstName: this.FirsName,
    secondName: this.SecondName,
    lastName: this.LastName,
    DNI: this.NumDNI,
    phoneNumber: this.PhoneNumber,
    dateOfBirth: this.DateOfBirth,
    }
  
    console.log(formData);
    this.assistentService.editAssistant(formData).subscribe(response => {
      // Manejar la respuesta del servidor aquí
      console.log('Respuesta del servidor:', response);
      window.location.reload(); 
    },
    error => {
      // Manejar errores aquí
      console.error('Error al hacer la solicitud:', error);
    })
    }else{
      // Si el formulario no está completo, añadir la clase 'incomplete' al botón
      const addButton = document.querySelector('.button');
      addButton?.classList.add('incomplete');
    }
  }

  isFormComplete(): boolean {
  // Verifica que todos los campos requeridos no estén indefinidos y que no estén vacíos
  const requiredFieldsComplete =
    this.NumDNI !== undefined &&
    this.FirsName !== undefined &&
    this.SecondName !== undefined &&
    this.LastName !== undefined &&
    this.DateOfBirth !== undefined &&
    this.PhoneNumber !== undefined &&
    this.NumDNI.toString().trim() !== '' &&
    this.FirsName.trim() !== '' &&
    this.SecondName.trim() !== '' &&
    this.LastName.trim() !== '' &&
    this.DateOfBirth.trim() !== '';

  // Verifica que NumDNI tenga 8 o 9 dígitos
  const validNumDNI = this.NumDNI !== undefined && (this.NumDNI.toString().length === 8 || this.NumDNI.toString().length === 9);

  // Verifica que DateOfBirth tenga el formato correcto (xxxx-xx-xx)
  const validDateOfBirth = this.DateOfBirth !== undefined && /^\d{4}-\d{2}-\d{2}$/.test(this.DateOfBirth);

  // Verifica que PhoneNumber no sea nulo y tenga entre 8 y 9 dígitos
  const validPhoneNumber =
    this.PhoneNumber !== undefined &&
    this.PhoneNumber !== null &&// Asegurarse de que PhoneNumber sea un número antes de convertirlo a cadena
    this.PhoneNumber.toString().length >= 8 &&
    this.PhoneNumber.toString().length <= 9;

  // Retorna verdadero si todos los campos requeridos están completos y si NumDNI tiene 8 o 9 dígitos,
  // DateOfBirth tiene el formato correcto y PhoneNumber tiene entre 8 y 9 dígitos
  return requiredFieldsComplete && validNumDNI && validDateOfBirth && validPhoneNumber;
}

  
isDateOfBirthValid(): boolean {
  return this.DateOfBirth !== undefined && /^\d{4}-\d{2}-\d{2}$/.test(this.DateOfBirth);
}
}
