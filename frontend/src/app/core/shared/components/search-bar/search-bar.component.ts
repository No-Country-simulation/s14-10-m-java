import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DoctorService } from '../specialties-box/doctors.service';
import { Doctor } from '../specialties-box/doctor.interface';
import { Observable, map, of } from 'rxjs';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent {

  term: string = '';
  postalCode$: Observable<Doctor[]> | null | undefined;
  speciality$: Observable<Doctor[]> | null | undefined;
  surname$: Observable<Doctor[]> | null | undefined;

  constructor( private doctorService: DoctorService, private router: Router ) {}

  searchByTerm(): void {
    if (this.term.trim()) {

        this.postalCode$ = this.doctorService.getDoctorsByPostalCode(this.term);

        this.speciality$ = this.doctorService.getDoctorsBySpecialty(this.term);

        this.surname$ = this.doctorService.getDoctorsBySurname(this.term);
      }

      this.router.navigate(['/search-doctor'])
    }
}
