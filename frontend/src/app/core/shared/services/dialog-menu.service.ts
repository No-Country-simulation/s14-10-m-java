import { Injectable } from '@angular/core';
import { ComponentPortal, Overlay, OverlayRef } from 'ngx-toastr';
import { DialogMenuComponent } from '../components/dialog-menu/dialog-menu.component';

@Injectable({
  providedIn: 'root'
})
export class DialogMenuService {

  private overlayRef?: OverlayRef;

  constructor(private overlay: Overlay) { }

  close() {
   this.overlayRef?.detach();
  }

}
