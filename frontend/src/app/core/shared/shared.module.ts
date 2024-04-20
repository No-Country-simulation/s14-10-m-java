import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './components/button/button.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

import { RouterModule } from '@angular/router';
import { DialogMenuModule } from './components/dialog-menu/dialog-menu.module';

import { AddAssistedFormComponent } from './components/add-assisted-form/add-assisted-form.component';
import { PopupComponent } from './components/popup/popup.component';


@NgModule({
  declarations: [ButtonComponent, NavbarComponent, FooterComponent, AddAssistedFormComponent, PopupComponent],
  imports: [CommonModule, RouterModule, DialogMenuModule],
  exports: [ButtonComponent, NavbarComponent, FooterComponent, PopupComponent],
})
export class SharedModule {}
