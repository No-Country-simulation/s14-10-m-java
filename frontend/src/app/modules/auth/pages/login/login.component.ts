//NGX-TOASTR

import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { emailValidator } from 'src/app/core/utils/validator';

import { LoginService } from 'src/app/modules/auth/services/login.service';
import { NotifyService } from 'src/app/modules/auth/services/notify.service';
import { ToastrService } from 'ngx-toastr';
import { Login } from '../../../../core/models/login.model';
import { passwordValidator } from '../../../../core/utils/validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword: boolean = false;
  error: string = 'Error en la aplicacion';
  http: any;

  constructor(
    private readonly loginService: LoginService,
    private toastr: ToastrService,
    private readonly fb: FormBuilder,
    private notifySvc: NotifyService,
    private readonly router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }

  Login() {
    this.loginService.isLoggedIn$.subscribe((isLoggedIn) => {
      if (isLoggedIn) {
        this.notifySvc.showSuccess('Datos enviados correctamente');
        this.loginForm.reset(this.loginForm);
        this.router.navigate(['/home']);
      }
    });

    if (this.loginService.login(this.loginForm.value).subscribe()) {
      this.notifySvc.showError('Verifica los datos');
    } else if (this.loginForm.invalid) {
      this.notifySvc.showError('ERROR EN EL FORMULARIO');
    }

    this.loginService.login(this.loginForm.value).subscribe(
      () => {
        this.notifySvc.showSuccess('Datos enviados correctamente');
        this.loginForm.reset();
        this.router.navigate(['/home']);
      },
      (error) => {
        const appToken = JSON.stringify(error.error.text);
        let token = appToken;
        token = token.replace(/^"(.*)"$/, '$1');
        localStorage.setItem('token', token);
        console.log(token);
      },

      () => {
        // Este bloque se ejecuta cuando la operación completa,
        // independientemente de si hay un error o no.
        this.notifySvc.showSuccess('Datos enviados correctamente');
        console.log('Formulario enviado:', this.loginForm.value);
      }
    );
  }

  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }
}
