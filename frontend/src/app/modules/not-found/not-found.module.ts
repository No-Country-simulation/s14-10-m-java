import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageUnderConstructionComponent } from './page-under-construction/page-under-construction.component';
import { NotFoundRoutingModule } from './not-found-routing.module';
import { SharedModule } from 'src/app/core/shared/shared.module';



@NgModule({
  declarations: [
    PageUnderConstructionComponent,
  ],
  imports: [
    CommonModule,
    NotFoundRoutingModule,
    SharedModule
  ]
})
export class NotFoundModule { }
