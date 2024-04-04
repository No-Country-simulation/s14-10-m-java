import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageUnderConstructionComponent } from './page-under-construction/page-under-construction.component';
import { NotFoundRoutingModule } from './not-found-routing.module';



@NgModule({
  declarations: [
    PageUnderConstructionComponent
  ],
  imports: [
    CommonModule,
    NotFoundRoutingModule
  ]
})
export class NotFoundModule { }
