import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashDoctorRoutingModule } from './dash-doctor-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  imports: [
    CommonModule,
    DashDoctorRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class DashDoctorModule { }
