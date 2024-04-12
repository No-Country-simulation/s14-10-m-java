import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal/principal.component';

import { HomeRoutingModule } from './home-routing.module';
import { CardComponent } from 'src/app/core/shared/components/card/card.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { SearchBarModule } from 'src/app/core/shared/components/search-bar/search-bar.module';
import { SpecialtyComponent } from 'src/app/core/shared/components/specialties/specialties.component';

@NgModule({
  declarations: [PrincipalComponent, CardComponent, SpecialtyComponent],
  imports: [CommonModule, HomeRoutingModule, ],
  exports: [CardComponent],
})
export class HomeModule {}
