import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-add-assisted',
  templateUrl: './add-assisted.component.html',
  styleUrls: ['./add-assisted.component.scss']
})
export class AddAssistedComponent {
    @Input() visible: boolean = false;
  @Input() selectedOption: string = ''; // Added default value

  @Output() close = new EventEmitter<void>(); // Renamed to close

  constructor() {} // Added constructor to initialize selectedOption

  emitClose() {
    this.visible = false; // Assuming you want to close the modal here
    this.close.emit();
  }
}
