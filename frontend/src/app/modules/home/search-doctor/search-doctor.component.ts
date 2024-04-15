import { Component } from '@angular/core';

@Component({
  selector: 'app-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls: ['./search-doctor.component.scss']
})
export class SearchDoctorComponent {
  selectedSpecialtyFilter: string = '';
  selectedAvailabilityFilter: string = '';

  applySpecialtyFilter(filter: string) {
    this.selectedSpecialtyFilter = filter;
  }
  applyAvailabilityFilter(filter: string) {
    this.selectedAvailabilityFilter = filter;
  }
}