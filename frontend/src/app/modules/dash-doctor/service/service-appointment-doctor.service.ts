import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServiceAppointmentDoctorService {

  private readonly getDoctorAppointmentUrl: string = `${environment.apiUrl}/appointment/doctor`;

  constructor(private http: HttpClient) { }

  getDoctorAppointmentId(id: number, active: boolean): Observable<any> {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.get(`${this.getDoctorAppointmentUrl}/${id}?&active=${active}`, { headers });
  }
}
