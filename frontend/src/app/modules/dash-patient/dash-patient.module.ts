import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashPatientRoutingModule } from './dash-patient-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { AssistedCardComponent } from 'src/app/core/shared/components/assisted-card/assisted-card.component';
import { AppointmentCardComponent } from 'src/app/core/shared/components/appointment-card/appointment-card.component';
import { SharedModule } from 'src/app/core/shared/shared.module';


@NgModule({

  declarations: [
    ProfileComponent,
    AssistedCardComponent,
    AppointmentCardComponent,
  ],
  imports: [
    CommonModule,
    DashPatientRoutingModule,
    SharedModule
  ]
})
export class DashPatientModule {}
