import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal/principal.component';

import { HomeRoutingModule } from './home-routing.module';
import { CardComponent } from 'src/app/core/shared/components/card/card.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { SearchBarModule } from 'src/app/core/shared/components/search-bar/search-bar.module';
import { SliderComponent } from 'src/app/core/shared/components/slider/slider.component';
import { SpecialtiesBoxComponent } from 'src/app/core/shared/components/specialties-box/specialties-box.component';

@NgModule({
  declarations: [PrincipalComponent, CardComponent, SliderComponent,SpecialtiesBoxComponent],
  imports: [CommonModule, HomeRoutingModule, SharedModule, SearchBarModule],
  exports: [CardComponent],
})
export class HomeModule {}
