import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './components/button/button.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

import { RouterModule } from '@angular/router';
import { DialogMenuModule } from './components/dialog-menu/dialog-menu.module';

import { PopupComponent } from './components/popup/popup.component';
import { EditAssistantFormComponent } from './components/edit-assistant-form/edit-assistant-form.component';
import { EditAssistedFormComponent } from './components/edit-assisted-form/edit-assisted-form.component';
import { AddAssistedFormComponent } from './components/add-assisted-form/add-assisted-form.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [ButtonComponent, NavbarComponent, FooterComponent, PopupComponent, EditAssistantFormComponent, EditAssistedFormComponent,AddAssistedFormComponent],

  imports: [CommonModule, RouterModule, DialogMenuModule,FormsModule],
  exports: [ButtonComponent, NavbarComponent, FooterComponent, PopupComponent,AddAssistedFormComponent,EditAssistantFormComponent, EditAssistedFormComponent],
})
export class SharedModule {}
