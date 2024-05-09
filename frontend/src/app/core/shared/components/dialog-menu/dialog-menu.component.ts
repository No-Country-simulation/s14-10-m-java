import { Component, EventEmitter, Output, ViewEncapsulation } from '@angular/core';
import { DialogMenuService } from '../../services/dialog-menu.service';
import { LoginService } from 'src/app/modules/auth/services/login.service';

import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { checkToken } from '../../interceptors/token.interceptor';

@Component({
  selector: 'app-dialog-menu',
  templateUrl: './dialog-menu.component.html',
  styleUrls: ['./dialog-menu.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DialogMenuComponent {

  private apiUrl = 'https://s14-10-m-java-production.up.railway.app';
  doctorSubscription: Subscription = new Subscription();
  assistentSubscription: Subscription = new Subscription();

  isOpen: boolean = false;
  isLogged: boolean = false;
  doctor: any;
  assistent: any;
  roled: any;
  @Output() closeModal = new EventEmitter<void>();


  constructor(
    private dialogMenuService: DialogMenuService,
    private loginService: LoginService,
    private http: HttpClient ){}

  ngOnInit(): void {
    this.roled = sessionStorage.getItem('role');
    const id = sessionStorage.getItem('id');

    if(this.roled === '[DOCTOR]'){
      this.doctorSubscription = this.http.get(`${this.apiUrl}/doctor/id/${id}`, { context: checkToken() }).subscribe(doctor => {
        this.doctor = doctor;
      });
    }
    if(this.roled === '[ASSISTENT]'){
      this.assistentSubscription = this.http.get(`${this.apiUrl}/assistent/id/${id}`, { context: checkToken() }).subscribe(assistent => {
        this.assistent = assistent;
      });
    }
  }

  onCloseModal() {
    this.closeModal.emit();
  }

  close() {
    this.dialogMenuService.close()
  }

  logout() {
    this.close();
    this.loginService.logout();
  }

  ngOnDestroy() {
    this.doctorSubscription?.unsubscribe();
    this.assistentSubscription?.unsubscribe();
  }

}
