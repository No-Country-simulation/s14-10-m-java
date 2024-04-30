import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent {
  @Output() closePopup: EventEmitter<void> = new EventEmitter<void>();

  onClose(): void {
    this.closePopup.emit();
  }
}
