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

  constructor(private doctorService: DoctorService, private router: Router) { }

  result: string[] = [];

  specialities: string[] = [
    "CARDIOLOGY",
    "DERMATOLOGY",
    "ENDOCRINOLOGY",
    "GASTROENTEROLOGY",
    "GERIATRICS",
    "GYNECOLOGY",
    "HEMATOLOGY",
    "INTERNAL_MEDICINE",
    "NEPHROLOGY",
    "NEUROLOGY",
    "OPHTHALMOLOGY",
    "ONCOLOGY",
    "ORTHOPEDICS",
    "OTOLARYNGOLOGY",
    "PEDIATRICS",
    "PSYCHIATRY",
    "RADIOLOGY",
    "RHEUMATOLOGY",
    "TRAUMATOLOGY",
    "UROLOGY"
  ];


  ngOnInit() {
    console.log(this.specialities)

    this.doctorService.specialityParamSubject.subscribe(speciality => console.log('speciality observable', speciality))
  }

  performSearch(): void {
    this.result = this.searchBySpeciality(this.term);
    console.log('resultados',this.result);
    this.sendSpeciality(this.result[0])
  }

  searchBySpeciality(query: string): string[] {
    const searchTerm = query.toUpperCase();
    return this.specialities.filter(speciality =>
      speciality.toUpperCase().includes(searchTerm)
    );
  }

  sendSpeciality(speciality: string){
    let specialityParam = '';
    this.specialities.map(item => {
      if(item === speciality){
        specialityParam = speciality;
        console.log('specialityParam', specialityParam)
        this.doctorService.specialityParamSubject.next(specialityParam)
        this.router.navigate(['/search-doctor'])
      }
    })
  }


}
