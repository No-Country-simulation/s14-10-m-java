import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from '../../../core/models/login.model';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/core/shared/services/token.service';
@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl: string = `${environment.apiUrl}`;

  public id?: number;
  public token?: string;
  public role?: string;

  private isLoggedIn = new BehaviorSubject<boolean>(false);
  public isLoggedIn$ = this.isLoggedIn.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenService: TokenService
  ) {
    this.updateVariables();
  }
  Login(login: Login): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, login).pipe(
      tap((res: any) => {
        console.log(res);
        sessionStorage.setItem('token', res.jwt);
        sessionStorage.setItem('id', res.id);
        sessionStorage.setItem('role', res.role);
        this.updateVariables();
      })
    );
  }
  logout() {
    this.isLoggedIn.next(false);
    this.tokenService.clearToken();
    this.router.navigate(['/auth/login']);
    //console.log(this.Login);
  }

  updateVariables() {
    if (sessionStorage.getItem('token')) {
      this.isLoggedIn.next(true);
    }
    if (sessionStorage.getItem('id')) {
      this.id = Number(sessionStorage.getItem('id'));
    }
    if (sessionStorage.getItem('role')) {
      this.role = sessionStorage.getItem('role')!;
    }
  }
}
