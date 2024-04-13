//NGX-TOASTR

import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { emailValidator } from 'src/app/core/utils/validator';
import Swal from 'sweetalert2';
import { LoginService } from 'src/app/services/login.service';
import { NotifyService } from 'src/app/services/notify.service';
import { ToastrService, ToastrModule } from 'ngx-toastr';
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
    /* this.http.post(`https://s14-10-m-java-production.up.railway.app/login`, {
   email:'prueba11@gmail.com',password:'123456789'
    }).subscribe(
      res => console.log('res', res),
      (error) => {
        const token = JSON.stringify(error.error.text);
        this.tokenService.saveToken(token);
        this.toastr.success("Inicio de sesion exitoso")
      }
 ) */
    /* this.loginService.login(this.loginForm.value).subscribe(
      (resp) => {
        console.log(resp);
      },
      (err) => {
        const loginToken = err.error.error.message;

        localStorage.setItem('token', JSON.stringify(loginToken));

        console.log(loginToken);


        this.loginService.login(this.loginForm.value).subscribe({
          next: () => {
            Swal.fire({
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
        });
      }
    ); */
  }

  /* if (this.loginForm.value && this.loginService) {
      this.notifySvc.showError('datos no coninciden');
      return;
    } else if (this.loginForm.value === this.loginService) {
      this.notifySvc.showSuccess('Datos enviados correctamente');
      return;
    } */

  /* if (this.loginForm.valid) {
      this.notifySvc.showSuccess('Datos enviados correctamente');
    }
    if (this.loginForm.invalid) {
      this.notifySvc.showError('datos no coninciden');
    } */

  /* if (
      this.loginService.Login(this.loginForm.value).subscribe({
        next: () => {
          this.notifySvc.showSuccess('Datos enviados correctamente');
          console.log(this.loginForm.value);
        },
      })
    ) */
  //temporalmente para q el formulario fue tocado
  /* if (this.loginForm.invalid) {
      return this.loginForm.markAllAsTouched();
    } */
  /* this.loginService.Login(this.loginForm.value).subscribe((res: any) => {
      this.loginService.id = res.id;
      this.loginService.token = res.token;
      localStorage.setItem('id', res.id);
      localStorage.setItem('token', res.token);
    }); */

  /* if (this.loginForm.value) {
      this.notifySvc.showError('Faltan campos por diligenciar');
    } */
  /* this.loginService.Login(this.loginForm.value).subscribe({
      next: () => {
        this.notifySvc.showSuccess('Datos enviados correctamente')
           .then(() => {
            this.loginForm.reset();
            this.router.navigate(['/home']);
        })
      }
    }) */
  /* this.loginService.Login(this.loginForm.value).subscribe({
        next: (response: any) => {
          // Assuming response type is unknown
          this.notifySvc.showSuccess('Datos enviados correctamente');

          // Handle successful login response (replace with your logic)
          console.log('Login correcto:', response); // Example logging

          this.loginForm.reset();
          this.router.navigate(['/home']);
        },
        error: (error: any) => {
          // Handle login errors
          console.error('Login error:', error);
          this.notifySvc.showError('Error al iniciar sesión'); // Example error notification
        },
      });
  }
 */
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
