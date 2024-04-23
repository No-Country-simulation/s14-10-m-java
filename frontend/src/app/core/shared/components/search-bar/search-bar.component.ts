import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DoctorService } from '../specialties-box/doctors.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent {

  term: string = '';

  constructor( private doctorService: DoctorService, private router: Router ) {}

  searchByTerm(): void {
    if (this.term.trim()) {
        this.doctorService.getDoctorsByPostalCode(this.term);
        this.doctorService.getDoctorsBySpecialty(this.term);
        this.doctorService.getDoctorsBySurname(this.term);
      }
      this.router.navigate(['/search-doctor'])
    }
}
