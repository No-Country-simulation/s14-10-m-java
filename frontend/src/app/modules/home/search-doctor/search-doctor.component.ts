import { Component } from '@angular/core';

@Component({
  selector: 'app-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls: ['./search-doctor.component.scss']
})
export class SearchDoctorComponent {
  selectedFilter: string = '';

  applySpecialtyFilter(filter: string) {
    this.selectedFilter = filter;
  }
}