import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from '../../../core/models/login.model';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/core/shared/services/token.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl: string = `${environment.apiUrl}`;
  public apptoken?: string;
  public isLogedIn = new BehaviorSubject<boolean>(false);
  public isLoggedIn$ = this.isLogedIn.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenService: TokenService
  ) {
    if (localStorage.getItem('token')) {
      this.isLogedIn.next(true);
    }
  }
  login(login: Login): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, login);
  }
  logout() {
    this.tokenService.clearToken();
    this.router.navigate(['/auth/login']);
    //console.log(this.Login);
  }
}
