import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { RegisterDoctorComponent } from './pages/register-doctor/register-doctor.component';
import { RegisterPatientComponent } from './pages/register-patient/register-patient.component';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    RegisterDoctorComponent,
    RegisterPatientComponent,
  ],
  imports: [CommonModule, AuthRoutingModule, SharedModule, FormsModule, ReactiveFormsModule],
})
export class AuthModule {}
