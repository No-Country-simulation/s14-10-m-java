import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/core/shared/components/card/interface/card.interface';
import { cardList } from 'src/app/core/shared/components/card/cards-data';
import { Specialty } from 'src/app/core/shared/components/slider/interface/specialty.interface';
import { Specialties } from 'src/app/core/shared/components/slider/specialties-data';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})

export class PrincipalComponent implements OnInit {
  especiList:Specialty[] =[];
  cardList:Card[] = [];

  ngOnInit(): void {
      this.cardList = cardList;
      this.especiList = Specialties;
  }
}
