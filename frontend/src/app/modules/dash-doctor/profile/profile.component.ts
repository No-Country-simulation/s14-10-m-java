import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceGetDoctorService } from '../service/service-get-doctor.service'
import { ServiceAppointmentDoctorService } from "../service/service-appointment-doctor.service";
import { ServicePutDoctorService } from "../service/service-put-doctor.service";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  doctorId: number = 0;
  doctor$: Observable<any> = new Observable<any>();

  isPopupVisible: boolean = false;

  upcomingDays: string[] = [];

  selectedDay: string | null = null;

  foundAppointmentData = []

  filteredAppointments: any[] = [];

  // Params in the appointment
  // page: number = 1;
  active: boolean = true;
  id: number = 30

  availableAppointmentDates: string[] = [];

  selectedAppointment: any;


  updateForm: FormGroup | any = new FormGroup({});;
  // updateForm:any = {
  //   id: 0,
  //   firstName: '',
  //   secondName: '',
  //   lastName: '',
  //   DNI: '',
  //   dateOfBirth: '',
  //   specialty: '',
  //   phoneNumber: 0,
  //   morning: true,
  //   afternoon: true,
  //   night: true,
  //   postalCode: 0,
  //   licenseNumber: 0,
  //   address: ''
  // };

  doctorData: any;


  // console.log()
  constructor(
    private doctorService: ServiceGetDoctorService,
    private appointmentService: ServiceAppointmentDoctorService,
    private formBuilder: FormBuilder,
    private doctorPutService: ServicePutDoctorService
  ) {

    const today = new Date();
    for (let i = 1; i <= 5; i++) {
      const nextDay = new Date(today);
      nextDay.setDate(today.getDate() + i);
      this.upcomingDays.push(nextDay.toLocaleDateString('es-ES', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }));
    }
  }

  ngOnInit(): void {

    const userDataString = sessionStorage.getItem('id');
    if (userDataString) {
      this.doctorId = Number(userDataString);
      this.doctor$ = this.doctorService.getDoctorById(this.doctorId);

      // Suscribirse a la observable para obtener los datos del doctor
      this.doctor$.subscribe({
        next: (data) => {
          console.log('Datos del doctor:', data);
          this.doctorData = data; // Asigna los datos del doctor directamente a la variable doctorData

          // Inicializa el formulario con los datos del doctor
          this.updateForm = this.formBuilder.group({
            id: [this.doctorData.id || 0],
            firstName: [this.doctorData.firstName || '', Validators.required],
            secondName: [this.doctorData.secondName || ''],
            lastName: [this.doctorData.lastName || '', Validators.required],
            DNI: [this.doctorData.DNI || '', [
              Validators.required,
              Validators.pattern(/^\d{7,9}$/)
            ]],
            dateOfBirth: [this.doctorData.dateOfBirth || '', Validators.required],
            specialty: [this.doctorData.specialty || '', Validators.required],
            phoneNumber: [this.doctorData.phoneNumber || 0, Validators.required],
            morning: [this.doctorData.morning || false],
            afternoon: [this.doctorData.afternoon || false],
            night: [this.doctorData.night || false],
            postalCode: [this.doctorData.postalCode || 0, Validators.required],
            licenseNumber: [this.doctorData.licenseNumber || 0, Validators.required],
            address: [this.doctorData.address || '', Validators.required]
          });
          console.log(this.doctorData.dateOfBirth)
        },
        error: (error) => {
          console.error('Error al obtener los datos del doctor:', error);
        }
      });
    } else {
      console.error('No se encontró el ID del doctor en sessionStorage.');
    }


    // DATA APPOINTMENT
    this.appointmentService.getDoctorAppointmentId(this.id, this.active).subscribe(
      (response: any) => {
        console.log('API Response:', response);
        this.availableAppointmentDates = response.map((item: any) => {
          const date = new Date(item.date);
          return {
            date: date.toLocaleDateString('es-ES', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }),
            hour: date.toLocaleTimeString(undefined, {
              hour: 'numeric',
              minute: 'numeric',
              hour12: true, // Para usar el formato de 12 horas (true) o de 24 horas (false)
            }),
            patient: `${item.assistent.firstName} ${item.assistent.lastName}` || `${item.assisted.firstName}${item.assisted.lastName}`
          };
        });
        console.log('Available Appointment Dates:', this.availableAppointmentDates);
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  selectDay(selectedDay: string) {
    this.selectedDay = selectedDay;
    // console.log(selectedDay);

    // Verificar si la fecha seleccionada está en el arreglo de fechas disponibles
    this.selectedAppointment = this.availableAppointmentDates.find((item: any) => item.date === selectedDay);
    // if (this.selectedAppointment) {
    //   console.log(`¡Cita encontrada para el día ${selectedDay} a las ${this.selectedAppointment.hour}!`);
    // } else {
    //   console.log('No hay citas para el día seleccionado.');
    // }
  }

  // POPUP
  openPopup(): void {
    this.isPopupVisible = true;
  }

  closePopup(): void {
    this.isPopupVisible = false;
  }

  // Submit
  get formControls() {
    return this.updateForm.controls;
  }


  // Función para obtener los datos del doctor
  getDoctorData(): void {
    this.doctor$.subscribe({
      next: (data) => {
        this.doctorData = data;
      },
      error: (error) => {
        console.error('Error al obtener los datos del doctor:', error);
      }
    });
  }

  onSubmit() {
    if (this.updateForm.valid) {
      const doctorData = this.updateForm.value;
      this.doctorPutService.putDoctor(doctorData).subscribe(
        (response) => {
          console.log('Doctor updated successfully:', response);
          // Puedes agregar lógica adicional aquí después de la actualización exitosa
          this.closePopup();
          // Llama a la función para obtener los datos del doctor actualizados
          this.getDoctorData();
        },
        (error) => {
          console.error('Error updating doctor:', error);
        }
      );
    } else {
      console.error('Form is invalid. Please check the fields.');
      alert("Error en algun campo de texto!")
    }
  }

}
