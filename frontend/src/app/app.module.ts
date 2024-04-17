import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundModule } from './modules/not-found/not-found.module';
import { AuthModule } from './modules/auth/auth.module';
// import { HomeModule } from './modules/home/home.module';
import { SharedModule } from './core/shared/shared.module';
import { TokenResponseInterceptor } from './core/shared/interceptors/token-response.interceptor';
import { TokenInterceptor } from './core/shared/interceptors/token.interceptor';
import { DashDoctorModule } from './modules/dash-doctor/dash-doctor.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NotFoundModule,
    AuthModule,
    // HomeModule,
    SharedModule,
    DashDoctorModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenResponseInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
