import { Component, OnInit } from '@angular/core';
import { AssistentService } from 'src/app/core/shared/services/assistent.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  turns: boolean = false;
  assisteds: boolean = false;
  data: any;

  constructor(private assistentService: AssistentService){}

  ngOnInit(): void {
    this.assistentService.getAssistant().subscribe(data=>
      this.data = data
    );
    //console.log(this.data);
  }

  turnsDropdown() {
    this.turns = !this.turns;
  }
  assistedsDropdown() {
    this.assisteds = !this.assisteds;
  }

}
