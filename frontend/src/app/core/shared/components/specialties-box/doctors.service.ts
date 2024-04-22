import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, filter, map, of, switchMap, tap } from 'rxjs';
import { Doctor } from './doctor.interface';
import { checkToken } from '../../interceptors/token.interceptor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  postalCodeSubject: BehaviorSubject<Doctor[]> = new BehaviorSubject<Doctor[]>([]);
  specialitySubject: BehaviorSubject<Doctor[]> = new BehaviorSubject<Doctor[]>([]);
  surnameSubject: BehaviorSubject<Doctor[]> = new BehaviorSubject<Doctor[]>([]);

  postalCode$: Observable<Doctor[]> | null | undefined;
  speciality$: Observable<Doctor[]> | null | undefined;
  surname$: Observable<Doctor[]> | null | undefined;

  private api = 'https://s14-10-m-java-production.up.railway.app/doctor';
  private apiUrl = 'https://s14-10-m-java-production.up.railway.app/doctor/all/true?page=0&size=1&sort=string';

  constructor(private http: HttpClient) {}

  getDoctors(): Observable<Doctor[]> {
    return this.http.get<{ content: Doctor[] }>(this.apiUrl, { context: checkToken() })
      .pipe(
        map(response => response.content)
      );
  }

  getDoctorsByPostalCode(postalCode: string): Observable<Doctor[]> {
    const code = Number(postalCode);
    const url = `${this.api}/findByPostalCode`;
    const params = new HttpParams().set('postalCode', code);
    this.postalCode$ = this.http.get<Doctor[]>(url, { params, context: checkToken() });
    this.postalCode$.subscribe(doctors => {
      this.postalCodeSubject.next(doctors);
    })
    return this.postalCode$;
  }

  getDoctorsBySpecialty(specialty: string): Observable<Doctor[]> {
    const speciality = specialty.toUpperCase();
    const url = `${this.api}/findBySpecialty`;
    const params = new HttpParams().set('specialty', speciality);
    this.speciality$ = this.http.get<Doctor[]>(url, { params, context: checkToken() });
    this.speciality$.subscribe(doctors => {
      this.specialitySubject.next(doctors);
    })
    return this.speciality$;
  }

  getDoctorsBySurname(surname: string): Observable<Doctor[]> {
    const url = `${this.api}/findBySurname/${true}`;

    this.surname$ = this.http.get<any>(url, { context: checkToken() }).pipe(
      map((response: any) => {
        return response.content.filter((doctor: Doctor) =>
          doctor.lastName.toLowerCase().includes(surname.toLowerCase())
        );
      })
    );
    this.surname$.subscribe(doctors => {
      this.surnameSubject.next(doctors);
    })
    return this.surname$;
  }


  getPostalCodeDoctors$(){
    return this.postalCodeSubject.asObservable();
  }

  getSpecialtyDoctors$() {
    return this.specialitySubject.asObservable();
  }

  getSurnameDoctors$() {
    return this.surnameSubject.asObservable();
  }

}
