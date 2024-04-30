import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { DoctorService } from './doctors.service';
import { Doctor } from './doctor.interface';
import { Specialty } from '../slider/interface/specialty.interface';

@Component({
  selector: 'app-specialties-box',
  templateUrl: './specialties-box.component.html',
  styleUrls: ['./specialties-box.component.scss']
})
export class SpecialtiesBoxComponent implements OnInit {
  doctors: Doctor[] = [];
  filteredDoctors: Doctor[] = [];

  constructor(private doctorService: DoctorService) {}

  ngOnInit(): void {
    this.doctorService.getDoctors().subscribe((doctors) => {
      this.doctors = doctors;
      if (this.doctors.length > 0) {
        const thirdSpecialty = this.doctors[3].specialty; 
        this.filteredDoctors = this.doctors.filter((doctor) => doctor.specialty === thirdSpecialty);
      }
    });
    
  }
  filterDoctorsBySpecialty(specialty: Specialty) {
    this.filteredDoctors = this.doctors.filter((doctor) => doctor.specialty === specialty.name);
  }

}