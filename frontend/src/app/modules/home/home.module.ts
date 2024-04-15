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
import { AppointmentConfirmationComponent } from './appointment-confirmation/appointment-confirmation.component';
import { AppointmentComponent } from 'src/app/core/shared/components/appointment/appointment.component';

@NgModule({
  declarations: [PrincipalComponent, CardComponent, SliderComponent,SpecialtiesBoxComponent, SearchDoctorComponent,DoctorSearchCardComponent,SearchFiltersComponent, AppointmentConfirmationComponent,AppointmentComponent],
  imports: [CommonModule, HomeRoutingModule, SharedModule, SearchBarModule],
  exports: [CardComponent],
})
export class HomeModule {}
