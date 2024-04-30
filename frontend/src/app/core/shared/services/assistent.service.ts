import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssistentService {

  private baseUrl = 'https://s14-10-m-java-production.up.railway.app';

  constructor(private http: HttpClient) { }

  getAssistedList(): Observable<any> {
    const jwt = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('id');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });
    return this.http.get<any>(`${this.baseUrl}/assisted/all/${userId}?page=0&size=50&sort=asc`, { headers: headers });
  }
  getAssistant(): Observable<any> {
    const jwt = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('id');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });
    return this.http.get<any>(`${this.baseUrl}/assistent/id/${userId}`, { headers: headers });
  
  }

  getAssistentAppointments(): Observable<any> {
    const jwt = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('id');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });
    return this.http.get<any>(`${this.baseUrl}/appointment/assistent/${userId}?page=0&active=true`, { headers: headers });
  }
  getAssistedAppointments(asistedId: any): Observable<any> {
    const jwt = sessionStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });
    return this.http.get<any>(`${this.baseUrl}/appointment/assisted/${asistedId}?page=0&active=true`, { headers: headers });
  }

  addAssisted(formData: any): Observable<any>{
    const jwt = sessionStorage.getItem('token');
    if (!jwt) {
       throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
     'Authorization': `Bearer ${jwt}`
    });
    return this.http.post(`${this.baseUrl}/assisted/register`, formData , {headers})
  }

  confirmAppointment(formData: any): Observable<any>{
    const jwt = sessionStorage.getItem('token');
      if (!jwt) {
         throw new Error('JWT no encontrado en sessionStorage');
      }

      const headers = new HttpHeaders({
       'Authorization': `Bearer ${jwt}`
      });
    return this.http.post(`${this.baseUrl}/appointment/`, formData , {headers})
    
  }

  deleteAppointment(appointmentId: any) {
    const jwt = sessionStorage.getItem('token');
    if (!jwt) {
       throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    return this.http.put(`${this.baseUrl}/appointment/${appointmentId}`, {}, { headers });
  }

  deleteAssisted(assistedId: any) {
  
    const jwt = sessionStorage.getItem('token');
    const assistantId = sessionStorage.getItem('id');

    if (!jwt) {
       throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    return this.http.delete(`${this.baseUrl}/assisted/deleteRelation/${assistantId}/${assistedId}`, { headers });

  }

  editAssistant(editForm: any){

    const jwt = sessionStorage.getItem('token');
    if (!jwt) {
       throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    return this.http.put(`${this.baseUrl}/assistent/update`, editForm, { headers });
  }
  editAssisted(formData: any){

    const jwt = sessionStorage.getItem('token');
    if (!jwt) {
       throw new Error('JWT no encontrado en sessionStorage');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    return this.http.put(`${this.baseUrl}/assisted/update`, formData, { headers });
  }

}
