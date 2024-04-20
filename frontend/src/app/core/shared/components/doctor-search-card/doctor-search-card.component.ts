import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Doctor } from '../specialties-box/doctor.interface';
import { DoctorService } from '../specialties-box/doctors.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-search-card',
  templateUrl: './doctor-search-card.component.html',
  styleUrls: ['./doctor-search-card.component.scss'],
})
export class DoctorSearchCardComponent implements OnInit {
  @Input() selectedSpecialtyFilter: string = '';
  @Input() selectedAvailabilityFilter: string = '';

  doctors: Doctor[] = [];
  speciality: string = '';

  constructor(private doctorService: DoctorService, private router: Router) {}

  ngOnInit(): void {
    this.loadDoctors();
    this.doctorService.specialityParamSubject.subscribe(speciality => {
      this.speciality = speciality;
      console.log('speciality observable desde doctor-search', this.speciality);
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (
      'selectedSpecialtyFilter' in changes ||
      'selectedAvailabilityFilter' in changes
    ) {
      this.applyFilter();
    }
  }

  applyFilter() {
    if (this.selectedSpecialtyFilter || this.selectedAvailabilityFilter) {
      this.doctorService.getDoctors().subscribe((doctors) => {
        // Aplicamos ambos filtros si están seleccionados
        this.doctors = doctors.filter(
          (doctor) =>
            (!this.selectedSpecialtyFilter ||
              doctor.specialty === this.selectedSpecialtyFilter) &&
            (!this.selectedAvailabilityFilter ||
              this.checkAvailability(doctor, this.selectedAvailabilityFilter))
        );
      });
    } else {
      this.loadDoctors();
    }
  }
  checkAvailability(doctor: Doctor, availabilityFilter: string): boolean {
    // Verificar la disponibilidad según el filtro seleccionado
    switch (availabilityFilter) {
      case 'Mañana':
        return doctor.morning;
      case 'Tarde':
        return doctor.afternoon;
      case 'Noche':
        return doctor.night;
      default:
        return false;
    }
  }

  loadDoctors() {
    this.doctorService.getDoctors().subscribe((doctors) => {
      this.doctors = doctors;
    });
  }

  redirectToAppointmentConfirmation(doctor: any) {
    console.log(doctor);

    this.router.navigate([`scheduler/${doctor.id}`]);

    // this.router.navigate(['/appointment-confirmation'], {
    //   state: { doctorData: doctor },
    // });
  }
}
