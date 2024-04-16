import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssistentService {
  constructor(private http: HttpClient) {}

  getAssistedList(): Observable<any[]> {
    return this.http.get<any[]>('https://s14-10-m-java-production.up.railway.app/assisted/all/{id}');
  }
}
