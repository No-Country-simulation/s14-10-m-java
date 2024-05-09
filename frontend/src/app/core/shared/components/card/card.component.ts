import { Component, OnInit} from '@angular/core';
import { Card } from './interface/card.interface';
import { cardList } from './cards-data';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  cardList:Card[] = [];

  ngOnInit(): void {
      this.cardList = cardList;
  }

}
