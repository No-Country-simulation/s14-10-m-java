import { Component, EventEmitter, Output } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-add-assisted-form',
  templateUrl: './add-assisted-form.component.html',
  styleUrls: ['./add-assisted-form.component.scss']
})
export class AddAssistedFormComponent {
  @Output() close: EventEmitter<void> = new EventEmitter();

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

this.assistentService.addAssisted(formData);
console.log(formData);
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
    this.RelationTypeWithAssistant !== undefined &&
    this.NumDNI.toString().trim() !== '' &&
    this.FirsName.trim() !== '' &&
    this.SecondName.trim() !== '' &&
    this.LastName.trim() !== '' &&
    this.DateOfBirth.trim() !== '' &&
    this.RelationTypeWithAssistant.trim() !== ''
  );
}
  
}
