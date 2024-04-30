import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-appointment-confirmation',
  templateUrl: './appointment-confirmation.component.html',
  styleUrls: ['./appointment-confirmation.component.scss'],
})
export class AppointmentConfirmationComponent {

  turnDate?: Date | null;
  doctorData: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const fechaAlmacenada = sessionStorage.getItem('fecha');
    if (fechaAlmacenada) {
      this.turnDate = new Date(fechaAlmacenada);
    }
    const queryParams = this.route.snapshot.queryParams;
    if (queryParams && queryParams['doctorData']) {
      this.doctorData = JSON.parse(queryParams['doctorData']);
    }
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
