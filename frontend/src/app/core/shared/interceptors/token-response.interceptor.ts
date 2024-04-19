import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../services/token.service';
import { Router } from '@angular/router';

@Injectable()
export class TokenResponseInterceptor implements HttpInterceptor {
  constructor(
    private toastr: ToastrService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse && request.url.includes('/login')) {
          this.handleSuccessfulLoginResponse(event);
        }
      }),
      catchError((error: any) => {
        if (
          error instanceof HttpErrorResponse &&
          request.url.includes('/login')
        ) {
          this.handleLoginErrorResponse(error);
          return new Observable<HttpEvent<any>>();
        }
        return throwError(error);
      })
    );
  }

  private handleSuccessfulLoginResponse(event: HttpResponse<any>): void {
    const token = JSON.stringify(event.body.jwt);
    this.tokenService.saveToken(token);
    this.toastr.success('¡Inicio de sesión exitoso!');
    this.router.navigate(['/']);
  }

  private handleLoginErrorResponse(error: HttpErrorResponse): void {
    this.toastr.error('¡Fallo inicio de sesión!');
    this.router.navigate(['/auth/login']);
  }
}
