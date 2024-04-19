import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashPatientRoutingModule } from './dash-patient-routing.module';
import { ProfileComponent } from './profile/profile.component';

import { SharedModule } from 'src/app/core/shared/shared.module';

@NgModule({
  declarations: [ProfileComponent],
  imports: [CommonModule, SharedModule, DashPatientRoutingModule],
})
export class DashPatientModule {}
