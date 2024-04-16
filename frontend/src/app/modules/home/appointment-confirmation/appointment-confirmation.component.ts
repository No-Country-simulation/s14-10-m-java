import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AssistentService } from 'src/app/core/shared/services/assistent.service';

@Component({
  selector: 'app-appointment-confirmation',
  templateUrl: './appointment-confirmation.component.html',
  styleUrls: ['./appointment-confirmation.component.scss']
})
export class AppointmentConfirmationComponent implements OnInit {
  isForSelf: boolean = true;
  motivoConsulta: string = '';
  telefonoContacto: string = '';
  titularSeleccionado: string = '';
  doctorData: any;
  assistedList: any;

  constructor(private route: ActivatedRoute,private assistentService :AssistentService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
    if(window.history.state.doctorData){
      this.doctorData = window.history.state.doctorData;
    }
    }
    );
    this.assistentService.getAssistedList().subscribe(
      (data: any[]) => {
        this.assistedList = data;
      },
      error => {
        console.error('Error al obtener las personas del usuario:', error);
      }
    );    
  }

  toggleForSelf(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.value === 'opcion1') {
      this.isForSelf = true;
    } else {
      this.isForSelf = false;
    }
  }
  
  onSelect(values: string) {
    if (values === 'new') {
      // Lógica para abrir el modal con el nuevo formulario
      // Puedes usar bibliotecas de modales como NgbModal o MatDialog para mostrar el modal
    }
  }
  seleccionarTitular(event: Event) {
    const target = event.target as HTMLSelectElement;
    if (target && target.value) {
      this.titularSeleccionado = target.value;
    }
  }
  isFormComplete(): boolean {
    if (!this.isForSelf) {
      // Si el turno es para alguien más, debes verificar si se ha seleccionado un titular del turno.
      return this.titularSeleccionado.trim() !== '' && this.motivoConsulta.trim() !== '' && this.telefonoContacto.trim() !== '';
      
    }else{
    // Verifica si el motivo de la consulta y el teléfono de contacto están completos
    return this.motivoConsulta.trim() !== '' && this.telefonoContacto.trim() !== '';
    }
  }
}
