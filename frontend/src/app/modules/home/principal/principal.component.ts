import { Component } from '@angular/core';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})

export class PrincipalComponent {
  especialidades: string[] = ['Especialidad 1', 'Especialidad 2', 'Especialidad 3'];

}
