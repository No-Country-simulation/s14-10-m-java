import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-edit-assisted-form',
  templateUrl: './edit-assisted-form.component.html',
  styleUrls: ['./edit-assisted-form.component.scss']
})
export class EditAssistedFormComponent {
  @Output() 
  close = new EventEmitter();
  @Input() assistedId: string | undefined;

  NumDNI: number | undefined;
  FirsName: string | undefined;
  SecondName: string | undefined;
  LastName: string | undefined;
  DateOfBirth: string | undefined;

constructor(private assistentService: AssistentService){}

  closePopup() {
    this.close.emit();
  }

  
  confirmFormData(){
    if (this.isFormComplete()) {
    let formData = {
      
      id: this.assistedId,
      DNI: this.NumDNI,
    firstName: this.FirsName,
    secondName: this.SecondName,
    lastName: this.LastName,
    dateOfBirth: this.DateOfBirth,
    }
    console.log(formData);
  
    this.assistentService.editAssisted(formData).subscribe(response => {
      // Manejar la respuesta del servidor aquí
      console.log('Respuesta del servidor:', response);
      window.location.reload(); 
    },
    error => {
      // Manejar errores aquí
      console.error('Error al hacer la solicitud:', error);
    }
    )
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
    this.NumDNI.toString().trim() !== '' &&
    this.FirsName.trim() !== '' &&
    this.SecondName.trim() !== '' &&
    this.LastName.trim() !== '' &&
    this.DateOfBirth.trim() !== ''
  );
}
  
}
