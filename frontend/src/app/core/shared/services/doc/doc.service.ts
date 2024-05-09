import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DocService {
  http = inject(HttpClient);

  uri = environment.apiUrl;

  // https://s14-10-m-java-production.up.railway.app/doctor/id/1
  getDocById(id: number): Observable<any> {
    const jwt = sessionStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });
    return this.http.get(`${this.uri}/doctor/id/${id}`, { headers: headers });
  }
}
