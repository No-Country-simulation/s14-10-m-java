import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class NotifyService {
  constructor(private toastr: ToastrService) {}

  showSuccess(message: string) {
    this.toastr.success(message, '¡Éxito!');
  }

  showError(message: string) {
    this.toastr.error(message, '¡Error!');
  }
}
