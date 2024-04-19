import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { emailValidator } from 'src/app/core/utils/validator';
import { LoginService } from 'src/app/modules/auth/services/login.service';
import { TokenService } from 'src/app/core/shared/services/token.service';
import { NotifyService } from '../../services/notify.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword: boolean = false;
  error: string = 'Error en la aplicacion';

  constructor(
    private readonly loginService: LoginService,
    private tokenService: TokenService,
    private NotifySvc: NotifyService,
    private readonly fb: FormBuilder,
    private readonly router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', [Validators.required, Validators.minLength(7)]],
    });
  }
  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }

  login() {
    //temporalmente para q el formulario fue tocado
    if (this.loginForm.invalid) {
      this.NotifySvc.showError('Error en la aplicacion');
      return this.loginForm.markAllAsTouched();
    }
    this.loginService.Login(this.loginForm.value).subscribe(
      (res: any) => {
        this.router.navigate(['/']);
      },
      (error) => {
        console.log(error.jwt);
        this.loginService.id = error.error.id;
        this.loginService.token = error.error.token;
        localStorage.getItem(error.token);
        sessionStorage.setItem('id', error.error.id);
        this.router.navigate(['/']);
      }
    );

    /* this.loginService.Login(this.loginForm.value).subscribe({
          next: () => {
              this.showPassword
              title: '¡Ingreso exitoso!',
              text: `¡Hola, bienvenido a esta iniciativa ambiental!`,
              icon: 'success',
            }).then(() => {
              this.loginForm.reset();
              this.router.navigate(['/home']);
            });
          },
          error: (error) => {
            Swal.fire({
              title: 'Ha ocurrido un error...',
              text: error.error,
              icon: 'error',
            });
          },
        }); */

    // handleShowPassword(): void {
    //   this.showPassword = !this.showPassword;
    // }
  }
}

