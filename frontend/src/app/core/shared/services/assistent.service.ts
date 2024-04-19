import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssistentService {

  constructor(private http: HttpClient) { }

  getAssistedList(): Observable<any[]> {
    const jwt = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('id');

    if (!jwt) {
      throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    return this.http.get<any[]>(`https://s14-10-m-java-production.up.railway.app/assisted/all/${userId}`, { headers });
  }

  confirmAppointment(formData: any): Observable<any>{
    const jwt = sessionStorage.getItem('token');
      if (!jwt) {
         throw new Error('JWT no encontrado en sessionStorage');
      }

      const headers = new HttpHeaders({
       'Authorization': `Bearer ${jwt}`
      });
    return this.http.post('https://s14-10-m-java-production.up.railway.app/appointment/', formData , {headers})
    
  }
}
