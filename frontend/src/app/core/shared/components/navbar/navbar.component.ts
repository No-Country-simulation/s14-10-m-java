import { Component, Input, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/modules/auth/services/login.service';

export interface NavItems {
  name: string;
  ruta: string;
}

@Component({
  selector: 'shared-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  router = inject(Router);
  auth = inject(LoginService);

  navItems: NavItems[] = [
    { name: '¿Cómo podemos ayudarte?', ruta: 'no-definida' },
    { name: 'Especialidades', ruta: 'no-definida' },
    { name: 'Trabaja con nosotros', ruta: 'no-definida' },
  ];

  isLogged: boolean = false;

  @Input()
  activeBlue: boolean = false;

  @Input()
  showOptions: boolean = true;

  ngOnInit(): void {
    this.auth.isLoggedIn$.subscribe({
      next: (resp) => {
        this.isLogged = resp;
        console.log(resp);
        this.changeNavItems();
      },
    });
  }

  onLogin() {
    this.router.navigateByUrl('/auth/login');
  }

  onBooking() {
    this.router.navigateByUrl('/search-doctor');
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

  // private changeNavItems() {
  //   if (this.isLogged) {
  //     this.navItems = [
  //       { name: 'Especialidades', ruta: 'no-definida' },
  //       { name: 'Mis turnos', ruta: 'no-definida' },
  //     ];
  //   }
  // }
  private changeNavItems() {
    const userType = sessionStorage.getItem('role');

    if (this.isLogged && userType) {
      if (userType === '[DOCTOR]') {
        this.navItems = [
          { name: 'Especialidades', ruta: '/dashboard/profile-doctor' },
          { name: 'Mis turnos', ruta: '/dashboard/profile-doctor' },
        ];
      } else if (userType === '[ASSISTENT]') {
        this.navItems = [
          { name: 'Especialidades', ruta: '/dashboard/profile-patient' },
          { name: 'Mis turnos', ruta: '/dashboard/profile-patient' },
        ];
      } else {
        this.navItems = [
          { name: 'Especialidades', ruta: 'no-definida' },
          { name: 'Mis turnos', ruta: 'no-definida' },
        ];
      }
    }
  }

}
