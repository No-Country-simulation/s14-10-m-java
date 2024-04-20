import { Component, OnInit } from '@angular/core';
import { AssistentService } from 'src/app/core/shared/services/assistent.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  listaAsistidos: any = [];

  constructor(private asistenteSvc: AssistentService) {}
  ngOnInit(): void {
    this.asistenteSvc.getAssistedList().subscribe((data: any) => {
      this.listaAsistidos = data;
      console.log(this.listaAsistidos);
    });
  }
}
