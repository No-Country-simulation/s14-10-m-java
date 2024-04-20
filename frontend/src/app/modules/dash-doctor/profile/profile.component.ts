import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceGetDoctorService } from '../service/service-get-doctor.service'

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  doctorId: number = 0;
  doctor$: Observable<any> = new Observable<any>();

  isPopupVisible: boolean = false;

  upcomingDays: string[] = [];

  constructor(private doctorService: ServiceGetDoctorService) {

    const today = new Date();
    for (let i = 1; i <= 5; i++) {
      const nextDay = new Date(today);
      nextDay.setDate(today.getDate() + i);
      this.upcomingDays.push(nextDay.toLocaleDateString('es-ES', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }));
    }

  }

  ngOnInit(): void {
    // Obtener el ID del doctor del Session Storage
    const userDataString = sessionStorage.getItem('id');
    if (userDataString) {
      this.doctorId = Number(userDataString);
      this.doctor$ = this.doctorService.getDoctorById(this.doctorId);

      // Suscribirse a la observable para obtener los datos del doctor
      this.doctor$.subscribe({
        next: (data) => {
          console.log('Datos del doctor:', data);
        },
        error: (error) => {
          console.error('Error al obtener los datos del doctor:', error);
        }
      });
    } else {
      console.error('No se encontró el ID del doctor en sessionStorage.');
    }
  }

  // Function to handle the select event
  selectDay(event: any) {
    const selectedDay = event.target.value;
    console.log('Selected day:', selectedDay);
    // You can display the message in the corresponding div here
  }

  openPopup(): void {
    this.isPopupVisible = true;
  }

  closePopup(): void {
    this.isPopupVisible = false;
  }
}
