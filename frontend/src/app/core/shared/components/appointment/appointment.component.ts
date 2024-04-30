import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.scss']
})
export class AppointmentComponent implements OnInit {
  @Input() doctorData: any;
  turnDate?: Date | null;

  ngOnInit(): void {
    const fechaAlmacenada = sessionStorage.getItem('fecha');
    if (fechaAlmacenada) {
      this.turnDate = new Date(fechaAlmacenada);
    }
    console.log(this.turnDate,"appoint comp");

  }

  obtenerDia(): string | null {
    if (this.turnDate) {
      return this.turnDate.toLocaleDateString('es-ES', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
        year: 'numeric'
      });
    } else {
      return null;
    }
  }
  
  
  obtenerHora(): string | null {
    if (this.turnDate) {
      const hora = this.turnDate.getHours();
      const minutos = this.turnDate.getMinutes();
      const horaFormateada = hora < 10 ? `0${hora}` : `${hora}`;
      const minutosFormateados = minutos < 10 ? `0${minutos}` : `${minutos}`;
      return `${horaFormateada}:${minutosFormateados}`;
    } else {
      return null;
    }
  }
  
}
