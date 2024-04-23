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
}
