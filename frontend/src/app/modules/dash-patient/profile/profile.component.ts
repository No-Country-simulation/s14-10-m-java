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
  data: any[] = [];

  constructor(private assistentService: AssistentService){}

  ngOnInit(): void {
    this.getAssistedList();
  }

  getAssistedList() {
    this.assistentService.getAssistentAppointments().subscribe(
      (data) => {
        this.data = data;
      },
      (error: any) => {
        console.error('Error al obtener la lista de asistidos:', error);
      }
    );
    console.log(this.data ," turnos ")
  }

  turnsDropdown() {
    this.turns = !this.turns;
  }
  assistedsDropdown() {
    this.assisteds = !this.assisteds;
  }
  
}
