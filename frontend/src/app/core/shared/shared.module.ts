import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './components/button/button.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

import { RouterModule } from '@angular/router';
import { DialogMenuModule } from './components/dialog-menu/dialog-menu.module';

import { AddAssistedComponent } from './components/add-assisted/add-assisted.component';


@NgModule({
  declarations: [ButtonComponent, NavbarComponent, FooterComponent, AddAssistedComponent],
  imports: [CommonModule, RouterModule, DialogMenuModule],
  exports: [ButtonComponent, NavbarComponent, FooterComponent],
})
export class SharedModule {}
