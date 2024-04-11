import { Component, Input, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'shared-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  router = inject(Router);

  @Input()
  activeBlue: boolean = false;

  onLogin() {
    this.router.navigateByUrl('/auth/login');
  }

  onRegister() {
    this.router.navigateByUrl('/auth/register');
  }

  public get showLogo(): string {
    if (this.activeBlue) {
      return `./assets/icons-svg/blue-logo.svg`;
    }
    return `./assets/icons-svg/logo.svg`;
  }
}
