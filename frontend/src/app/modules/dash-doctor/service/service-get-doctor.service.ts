import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ServiceGetDoctorService {

  private readonly getDoctorUrl: string = `${environment.apiUrl}/doctor/id`;

  constructor(private http: HttpClient) { }

  getDoctorById(id: number): Observable<any> {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`${this.getDoctorUrl}/${id}`, { headers });
  }
}
