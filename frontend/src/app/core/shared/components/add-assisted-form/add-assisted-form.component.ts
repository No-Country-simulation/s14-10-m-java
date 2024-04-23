import { Component, EventEmitter, Output } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-add-assisted-form',
  templateUrl: './add-assisted-form.component.html',
  styleUrls: ['./add-assisted-form.component.scss']
})
export class AddAssistedFormComponent {
  
  @Output() 
  close = new EventEmitter();
  

  NumDNI: number | undefined;
  FirsName: string | undefined;
  SecondName: string | undefined;
  LastName: string | undefined;
  DateOfBirth: string | undefined;
  AssistantID: string | null | undefined;
  RelationTypeWithAssistant: string | undefined;

constructor(private assistentService: AssistentService){}

  closePopup() {
    this.close.emit();
  }

  
  confirmFormData(){
    if (this.isFormComplete()) {
    let formData = {
  
      DNI: this.NumDNI,
    firstName: this.FirsName,
    secondName: this.SecondName,
    lastName: this.LastName,
    dateOfBirth: this.DateOfBirth,
    AssistantID: sessionStorage.getItem('id'),
    relationTypeWithAssistant: this.RelationTypeWithAssistant,
    }
  
    this.assistentService.addAssisted(formData).subscribe(response => {
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
      const addButton = document.querySelector('button');
      addButton?.classList.add('.incomplete');
      console.log("incompleto")
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
      this.RelationTypeWithAssistant !== undefined &&
      this.NumDNI.toString().trim() !== '' &&
      this.FirsName.trim() !== '' &&
      this.SecondName.trim() !== '' &&
      this.LastName.trim() !== '' &&
      this.DateOfBirth.trim() !== '' &&
      this.RelationTypeWithAssistant.trim() !== '';
  
    // Verifica que NumDNI tenga 8 o 9 dígitos
    const validNumDNI = this.NumDNI !== undefined && (this.NumDNI.toString().length === 8 || this.NumDNI.toString().length === 9);
  
    // Verifica que DateOfBirth tenga el formato correcto (xxxx-xx-xx)
    const validDateOfBirth = this.DateOfBirth !== undefined && /^\d{4}-\d{2}-\d{2}$/.test(this.DateOfBirth);
  
    // Retorna verdadero si todos los campos requeridos están completos y si NumDNI tiene 8 o 9 dígitos y DateOfBirth tiene el formato correcto
    return requiredFieldsComplete && validNumDNI && validDateOfBirth;
  }
  
  
isDateOfBirthValid(): boolean {
  return this.DateOfBirth !== undefined && /^\d{4}-\d{2}-\d{2}$/.test(this.DateOfBirth);
}
}
