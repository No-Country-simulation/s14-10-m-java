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
  turnDate?: Date | null;

  constructor(private route: ActivatedRoute,private assistentService :AssistentService, private router: Router) { }

  
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
    if(window.history.state.doctorData){
      this.doctorData = window.history.state.doctorData;
    }
    }
    );
    this.assistentService.getAssistedList().subscribe(
      (data: any[]) => {
        this.assistedList = data;
      },
      error => {
        console.error('Error al obtener las personas del usuario:', error);
      }
    );   
    const fechaAlmacenada = sessionStorage.getItem('fecha');
    if (fechaAlmacenada) {
      this.turnDate = new Date(fechaAlmacenada);
    }
    console.log(this.turnDate);
  }

  toggleForSelf(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.value === 'opcion1') {
      this.isForSelf = true;
    } else {
      this.isForSelf = false;
    }
  }
  
  onSelect(values: string) {
    if (values === 'new') {
      // Lógica para abrir el modal con el nuevo formulario
      // Puedes usar bibliotecas de modales como NgbModal o MatDialog para mostrar el modal
    }
  }
  seleccionarTitular(value: string) {
    this.titularSeleccionado = value;
    console.log(this.titularSeleccionado);
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
      
    }else{
    // Verifica si el motivo de la consulta y el teléfono de contacto están completos
    return this.motivoConsulta.trim() !== '' && this.telefonoContacto.trim() !== '';
    }
  }

  
}
