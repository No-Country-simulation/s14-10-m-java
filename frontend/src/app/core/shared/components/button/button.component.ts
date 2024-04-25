import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss'],
})
export class ButtonComponent {
  @Input() color?: 'primary' | 'secondary' | 'danger'|'large';
  @Input() buttonType?: 'submit' | 'button' = 'button';
  @Input() additionalClass?: string ;
  @Input() endIcon?: string;
  @Input() routerLink?: string | any;

  get endIconClass() {
    return this.endIcon ? `${this.endIcon}` : '';
  }
}
