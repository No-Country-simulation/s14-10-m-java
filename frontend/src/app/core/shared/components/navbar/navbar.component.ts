import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'shared-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  router = inject(Router);

  onLogin() {
    this.router.navigateByUrl('/auth/login');
  }

  onRegister() {
    this.router.navigateByUrl('/auth/register');
  }
}
