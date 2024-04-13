import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RegisterDoctorService {

  private readonly registerDoctorUrl: string = `${environment.apiUrl}/doctor/register`;

  constructor(private http: HttpClient) { }

  sendFormData(formData: any) {
    // Aquí puedes ajustar la URL del servidor donde enviar los datos
    const apiUrl = this.registerDoctorUrl;

    // Envía los datos del formulario al servidor utilizando una solicitud POST
    return this.http.post(apiUrl, formData);
  }
}
