import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Doctor } from '../specialties-box/doctor.interface';
import { DoctorService } from '../specialties-box/doctors.service';

@Component({
  selector: 'app-doctor-search-card',
  templateUrl: './doctor-search-card.component.html',
  styleUrls: ['./doctor-search-card.component.scss']
})
export class DoctorSearchCardComponent implements OnInit{
  @Input() selectedFilter: string = '';

  doctors: Doctor[] = [];

  constructor(private doctorService: DoctorService){}

  ngOnInit(): void {
    this.doctorService.getDoctors().subscribe((doctors) => {
      this.doctors = doctors;
    });
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    if ('selectedFilter' in changes) {
      this.applyFilter();
    }
  }

  applyFilter() {
    if (this.selectedFilter) {
      this.doctorService.getDoctors().subscribe((doctors) => {
        this.doctors = doctors.filter(doctor => doctor.specialty === this.selectedFilter);
      });
    } else {
      this.doctorService.getDoctors().subscribe((doctors) => {
        this.doctors = doctors;
      });
    }
  }
}
