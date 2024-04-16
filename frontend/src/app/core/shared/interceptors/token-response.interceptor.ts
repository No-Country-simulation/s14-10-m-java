import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, catchError, map, tap, throwError } from 'rxjs';
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

  // intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   return next.handle(request).pipe(
  //     tap((event: HttpEvent<any>) => {
  //       if (event instanceof HttpResponse && request.url.includes('/login')) {
  //         this.handleSuccessfulLoginResponse(event);
  //       }
  //     }),
  //     catchError((error: any) => {
  //       if (error instanceof HttpErrorResponse && request.url.includes('/login')) {
  //         this.handleLoginErrorResponse(error);
  //       }
  //       return throwError(error);
  //     })
  //   );
  // }

  // private handleSuccessfulLoginResponse(event: HttpResponse<any>): void {
  //   this.toastr.success('¡Inicio de sesión exitoso!');
  // }

  // private handleLoginErrorResponse(error: HttpErrorResponse): void {
  //   if (error.error && error.error.text) {
  //     const token = JSON.stringify(error.error.text);
  //     this.tokenService.saveToken(token);
  //   }
  //   this.toastr.success('¡Inicio de sesión exitoso!');
  // }

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
          // Si hay un error en la solicitud de inicio de sesión
          this.handleLoginErrorResponse(error);
          // Retornar un nuevo HttpResponse vacío para evitar que el error se propague como HttpErrorResponse
          return new Observable<HttpEvent<any>>();
        }
        return throwError(error); // Propagar el error original si no es una solicitud de inicio de sesión
      })
    );
  }

  private handleSuccessfulLoginResponse(event: HttpResponse<any>): void {
    this.toastr.success('¡Inicio de sesión exitoso!');
  }

  private handleLoginErrorResponse(error: HttpErrorResponse): void {
    if (error.error && error.error.text) {
      const token = JSON.stringify(error.error.text);
      this.tokenService.saveToken(token);
      this.toastr.error('Verifica tus datos');
    }
    this.toastr.success('¡Inicio de sesión exitoso!');
    this.router.navigate(['/']);
  }
}
