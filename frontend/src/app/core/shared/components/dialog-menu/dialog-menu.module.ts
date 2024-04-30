import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogMenuComponent } from './dialog-menu.component';
import { OverlayModule } from '@angular/cdk/overlay';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    DialogMenuComponent
  ],
  imports: [
    CommonModule,
    OverlayModule,
    RouterModule
  ],
  exports: [
    DialogMenuComponent
  ]
})
export class DialogMenuModule { }
