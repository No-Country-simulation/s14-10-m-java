import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AssistentService } from 'src/app/core/shared/services/assistent.service';

@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html',
  styleUrls: ['./appointment-form.component.scss']
})
export class AppointmentFormComponent implements OnInit {
  isForSelf: boolean = true;
  motivoConsulta: string = '';
  telefonoContacto: string = '';
  titularSeleccionado: string = '';
  doctorData: any;
  assistedList: any;
  turnDate?: string | null;
  showPopup: boolean = false; // Variable para controlar la visibilidad del popup

  constructor(private route: ActivatedRoute,private assistentService :AssistentService, private router: Router) { }

  
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
    if(window.history.state.doctorData){
      this.doctorData = window.history.state.doctorData;
    }
    }
    );
    this.assistentService.getAssistedList().subscribe(
      (data: any) => {
        this.assistedList = data.content;
      },
      error => {
        console.error('Error al obtener las personas del usuario:', error);
      }
    );   
    const fechaAlmacenada = sessionStorage.getItem('fecha');
    if (fechaAlmacenada) {
      // Creamos un objeto Date con la fecha almacenada
      const dateObj = new Date(fechaAlmacenada);
      
      // Obtenemos los componentes de la fecha
      const year = dateObj.getFullYear();
      const month = String(dateObj.getMonth() + 1).padStart(2, '0');
      const day = String(dateObj.getDate()).padStart(2, '0');
      const hours = String(dateObj.getHours()).padStart(2, '0');
      const minutes = String(dateObj.getMinutes()).padStart(2, '0');
      const seconds = String(dateObj.getSeconds()).padStart(2, '0');
      
      // Creamos la cadena de texto en el formato deseado
      this.turnDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
    }

    console.log(this.assistedList);
  }

  toggleForSelf(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.value === 'opcion1') {
      this.isForSelf = true;
    } else {
      this.isForSelf = false;
    }
  }
  
  

  seleccionarTitular(value: string) {
    this.titularSeleccionado = value;
    if (value === 'new') {
      this.showPopup = true; // Mostrar el popup cuando se selecciona "Agregar nuevo asistido"
    }
  }
  confirmFormData(){

    let motivoTelefono = `Motivo de la consulta: ${this.motivoConsulta}\nTeléfono: ${this.telefonoContacto}`;
    let formData;
  if(this.isForSelf){
  formData = {
    doctorId: this.doctorData.id,
    assistentId: sessionStorage.getItem('id'),
    date: this.turnDate,
    observations: motivoTelefono
    }
  }else{
    formData = {
      doctorId: this.doctorData.id,
      assistedId: this.titularSeleccionado,
      date: this.turnDate,
      observations: motivoTelefono
      }
  }
  this.assistentService.confirmAppointment(formData).subscribe(
    () => {
      // Si la confirmación fue exitosa, navega a la página de confirmación
      this.router.navigate(['/appointment-confirmation'], { queryParams: { doctorData: JSON.stringify(this.doctorData) } });
    },
    error => {
      console.error('Error al confirmar el turno:', error);
      // Si la confirmación no fue exitosa, puedes mostrar un mensaje de error o
      // tomar otras acciones según tus necesidades, pero no navegas a la página de confirmación
    }
  );

  }
  isFormComplete(): boolean {
    if (!this.isForSelf) {
      // Si el turno es para alguien más, debes verificar si se ha seleccionado un titular del turno.
      return this.titularSeleccionado.trim() !== '' && this.motivoConsulta.trim() !== '' && this.telefonoContacto.trim() !== '';  
    } else {
      // Si el turno es para uno mismo, solo verifica si el motivo de la consulta y el teléfono de contacto están completos.
      return this.motivoConsulta.trim() !== '' && this.telefonoContacto.trim() !== '';
    }
  }

  
}
