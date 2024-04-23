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
  return (
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
    this.DateOfBirth.trim() !== '' &&
    this.PhoneNumber !== null
  );
}
  
}
