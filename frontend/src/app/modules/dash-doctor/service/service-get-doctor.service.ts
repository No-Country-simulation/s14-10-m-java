import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServiceGetDoctorService {
  http = inject(HttpClient);

  private readonly getDoctorUrl: string = `${environment.apiUrl}/doctor/id`;

  constructor() { }

  getDoctorById(id: number): Observable<any> {
    return this.http.get(`${this.getDoctorUrl}/${id}`);
  }
}
