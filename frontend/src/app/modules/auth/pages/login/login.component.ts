//NGX-TOASTR

import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { emailValidator } from 'src/app/core/utils/validator';
//import Swal from 'sweetalert2';
import { LoginService } from 'src/app/services/login.service';
import { NotifyService } from 'src/app/services/notify.service';
import { ToastrService } from 'ngx-toastr';
import { Login } from '../../../../core/models/login.model';

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

  login() {
    //temporalmente para q el formulario fue tocado
    /* if (this.loginForm.invalid) {
      return this.loginForm.markAllAsTouched();
    } */
    //if (this.loginForm.value) {
    //this.notifySvc.showError('Error al iniciar sesión');
    //}
    this.loginService.Login(this.loginForm.value).subscribe((res: any) => {
      this.loginService.id = res.id;
      this.loginService.token = res.token;
      localStorage.setItem('id', res.id);
      localStorage.setItem('token', res.token);
    });
  }

  //this.notifySvc.showSuccess('formulario enviado correctamente');

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

  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }
}
