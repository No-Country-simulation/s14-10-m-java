import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal/principal.component';

import { HomeRoutingModule } from './home-routing.module';
import { CardComponent } from 'src/app/core/shared/components/card/card.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { SearchBarModule } from 'src/app/core/shared/components/search-bar/search-bar.module';
import { SliderComponent } from 'src/app/core/shared/components/slider/slider.component';
import { SpecialtiesBoxComponent } from 'src/app/core/shared/components/specialties-box/specialties-box.component';
import { SearchDoctorComponent } from './search-doctor/search-doctor.component';
import { DoctorSearchCardComponent } from 'src/app/core/shared/components/doctor-search-card/doctor-search-card.component';
import { SearchFiltersComponent } from 'src/app/core/shared/components/search-filters/search-filters.component';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';
import { AppointmentComponent } from 'src/app/core/shared/components/appointment/appointment.component';
import { FormsModule } from '@angular/forms';

import { DocScheduleComponent } from './doc-schedule/doc-schedule.component';

import {
  ScheduleModule,
  RecurrenceEditorModule,
} from '@syncfusion/ej2-angular-schedule';
import { AppointmentConfirmationComponent } from './appointment-confirmation/appointment-confirmation.component';

@NgModule({
  declarations: [
    PrincipalComponent,
    CardComponent,
    SliderComponent,
    SpecialtiesBoxComponent,
    SearchDoctorComponent,
    DoctorSearchCardComponent,
    SearchFiltersComponent,
    AppointmentFormComponent,
    AppointmentConfirmationComponent,
    AppointmentComponent,
    DocScheduleComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    SearchBarModule,
    FormsModule,
    ScheduleModule,
    RecurrenceEditorModule,
  ],
  exports: [CardComponent],
})
export class HomeModule {}
