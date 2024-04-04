import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/core/shared/components/card/card';
import { cardList } from 'src/app/core/shared/components/card/cards-data';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})

export class PrincipalComponent implements OnInit {
  especialidades: string[] = ['Especialidad 1', 'Especialidad 2', 'Especialidad 3'];
  cardList:Card[] = [];
  
  ngOnInit(): void {
      this.cardList = cardList;
  }
}
