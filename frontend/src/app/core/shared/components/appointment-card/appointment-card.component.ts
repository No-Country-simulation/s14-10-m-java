import { Component, OnInit } from '@angular/core';
import { AssistentService } from '../../services/assistent.service';

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.scss']
})
export class AppointmentCardComponent implements OnInit {

  constructor(private assistentService: AssistentService){}
  assistentAppointments: any [] = [];
  showCancelPopup: boolean = false;

  ngOnInit(): void {
    this.getAssistentList();
  }

  getAssistentList() {
    this.assistentService.getAssistentAppointments().subscribe(
      (data) => {
        this.assistentAppointments = data;
      },
      (error: any) => {
        console.error('Error al obtener la lista de asistidos:', error);
      }
    );
    console.log(this.assistentAppointments ," turnos ")
  }

  deleteAssistentAppointment(appointmentId: any) {
    this.assistentService.deleteAppointment(appointmentId).subscribe(
      (response) => {
        console.log('Turno cancelado exitosamente', response);
        window.location.reload();
            },
      (error) => {
        console.error('Error al cancelar el turno:', error);
      }
    );
  }

  openCancelPopup() {
    this.showCancelPopup = !this.showCancelPopup;
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
