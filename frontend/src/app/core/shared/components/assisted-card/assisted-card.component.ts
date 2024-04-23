import { Component, OnInit } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-assisted-card',
  templateUrl: './assisted-card.component.html',
  styleUrls: ['./assisted-card.component.scss']
})
export class AssistedCardComponent implements OnInit {
  assistedList: any[] = [];
  userId: any; // Setear el userId aquí
  dropdownStates: boolean[] = []; // Array para mantener el estado de despliegue de cada asistido
  assistedAppointments: any [] = [];
  assistedAppointmentsMap: { [assistedId: string]: any[] } = {};
  editAssisted: boolean = false;
  showCancelPopup: boolean = false;
  showCancelTurnPopup: boolean = false;

  constructor(private assistedService: AssistentService) {
   }

  ngOnInit(): void {
    this.getAssistedList();
  }

  getAssistedList() {
    this.assistedService.getAssistedList().subscribe(
      (data: any) => {
        this.assistedList = data.content;
      },
      (error: any) => {
        console.error('Error al obtener la lista de asistidos:', error);
      }
    );
  }

  // Método para alternar el estado del desplegable
  toggleDropdown(index: number) {
    this.dropdownStates[index] = !this.dropdownStates[index];
  }


  getAssistedTurns(assistedId: string) {
    this.assistedService.getAssistedAppointments(assistedId).subscribe(
      (data: any[]) => {
        this.assistedAppointmentsMap[assistedId] = data;
      },
      (error: any) => {
        console.error('Error al obtener los turnos del asistido:', error);
      }
    );
  }

  deleteAssistedAppointment(appointmentId: any) {
    this.assistedService.deleteAppointment(appointmentId).subscribe(
      (response) => {
        console.log('Turno cancelado exitosamente', response);
        window.location.reload();      },
      (error) => {
        console.error('Error al cancelar el turno:', error);
      }
    );
  }

  deleteAssisted(assistedId: any){
    this.assistedService.deleteAssisted(assistedId).subscribe(
      (response) => {
        console.log('Se a desvinculado el asistido', response);
        window.location.reload();      },
      (error) => {
        console.error('Error al desvincular el asistido:', error);
      }
    );
    
  }
  toggleEditAssisted() {
    this.editAssisted = !this.editAssisted; 
  }
  toggleCancelPopup() {
    this.showCancelPopup = !this.showCancelPopup;
  }

  toggleCancelTurnPopup() {
    this.showCancelTurnPopup = !this.showCancelTurnPopup;
  }

  obtenerDia(appointmentDate: string): string | null {
    if (appointmentDate) {
      const turnDate = new Date(appointmentDate);
      return turnDate.toLocaleDateString('es-ES', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
        year: 'numeric'
      });
    } else {
      return null;
    }
  }
  
  obtenerHora(appointmentDate: string): string | null {
    if (appointmentDate) {
      const turnDate = new Date(appointmentDate);
      const hora = turnDate.getHours();
      const minutos = turnDate.getMinutes();
      const horaFormateada = hora < 10 ? `0${hora}` : `${hora}`;
      const minutosFormateados = minutos < 10 ? `0${minutos}` : `${minutos}`;
      return `${horaFormateada}:${minutosFormateados}`;
    } else {
      return null;
    }
  }
}
