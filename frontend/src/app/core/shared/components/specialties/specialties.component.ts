import { Component, OnInit } from '@angular/core';
import { Specialty } from './interface/specialty.interface';
import { Specialties } from './specialties-data';

@Component({
  selector: 'app-specialties',
  templateUrl: './specialties.component.html',
  styleUrls: ['./specialties.component.scss']
})
export class SpecialtyComponent implements OnInit {

specialties: Specialty[] = [];

ngOnInit(): void {
    this.specialties = Specialties;
}  



}
  