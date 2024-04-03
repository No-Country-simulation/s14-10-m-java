import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal/principal.component';

import { HomeRoutingModule} from './home-routing.module'
import { CardComponent } from 'src/app/core/shared/components/card/card.component';

@NgModule({
  declarations: [
    PrincipalComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  exports: [
    CardComponent
  ]
})
export class HomeModule { }
