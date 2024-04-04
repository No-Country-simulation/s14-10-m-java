import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal/principal.component';

import { HomeRoutingModule} from './home-routing.module'
import { CardComponent } from 'src/app/core/shared/components/card/card.component';
import { SharedModule } from 'src/app/core/shared/shared.module';

@NgModule({
  declarations: [
    PrincipalComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule
  ],
  exports: [
    CardComponent
  ]
})
export class HomeModule { }
