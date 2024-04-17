import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssistentService {

  constructor(private http: HttpClient) { }

  getAssistedList(): Observable<any[]> {
    // Obtener el token JWT de sessionStorage
    const jwt = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('id');

    // Verificar si el JWT existe
    if (!jwt) {
      throw new Error('JWT no encontrado en sessionStorage');
    }

    // Crear los encabezados de la solicitud con el JWT
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${jwt}`
    });

    // Realizar la solicitud HTTP con el ID de usuario y los encabezados
    return this.http.get<any[]>(`https://s14-10-m-java-production.up.railway.app/assisted/all/${userId}`, { headers });
  }
}
