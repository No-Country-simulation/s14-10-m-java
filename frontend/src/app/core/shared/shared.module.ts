import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './components/button/button.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

import { RouterModule } from '@angular/router';
import { AddAssistedFormComponent } from './components/add-assisted-form/add-assisted-form.component';


@NgModule({
  declarations: [ButtonComponent, NavbarComponent, FooterComponent, AddAssistedFormComponent],
  imports: [CommonModule, RouterModule],
  exports: [ButtonComponent, NavbarComponent, FooterComponent],
})
export class SharedModule {}
