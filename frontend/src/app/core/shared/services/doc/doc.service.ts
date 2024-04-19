import { HttpClient } from '@angular/common/http';
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
    return this.http.get(`${this.uri}/doctor/id/${id}`);
  }
}
