import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

//import { emailValidator } from '@utils/validator';
//import Swal from 'sweetalert2';
//import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  //loginForm: FormGroup;
  //showPassword: boolean = false;
  //error: string = 'Error en la aplicacion';

  constructor(
    //private readonly authService: AuthService,
    //private readonly fb: FormBuilder,
    private readonly router: Router
  ) {}
}
