import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Doctor } from './doctor.interface';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private apiUrl = 'https://s14-10-m-java-production.up.railway.app/doctor/all/true?page=0&size=1&sort=string';

  constructor(private http: HttpClient) {}

  getDoctors(): Observable<Doctor[]> {
    return this.http.get<{ content: Doctor[] }>(this.apiUrl)
      .pipe(
        map(response => response.content)
      );
  }

  
}