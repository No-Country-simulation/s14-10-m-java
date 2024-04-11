import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterPatientService {
  private readonly registerPatientUrl: string = `${environment.apiUrl}/assistent/register`;

  constructor(private readonly http: HttpClient,
  ) { }

  submitForm(formData: any) {
    // Aquí puedes ajustar la URL del servidor donde enviar los datos
    const apiUrl = this.registerPatientUrl;

    // Envía los datos del formulario al servidor utilizando una solicitud POST
    return this.http.post(apiUrl, formData);
  }
}
