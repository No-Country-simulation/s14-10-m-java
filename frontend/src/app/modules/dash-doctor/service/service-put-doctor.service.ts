import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ServicePutDoctorService {
  private readonly putDoctorUrl: string = `${environment.apiUrl}/doctor/update`;

  constructor(private http: HttpClient) { }

  putDoctor(doctorData: any): Observable<any> {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.put(this.putDoctorUrl, doctorData, { headers })
  }
}
