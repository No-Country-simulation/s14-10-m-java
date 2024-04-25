import { Card } from "./interface/card.interface";
export const cardList: Card[] = [
    {
        id: 1,
        image: "Nurse.svg",
        title: "Elige un profesional",
        description: "Busca por especialidad médica o elige tu médico de preferencia entre la lista de profesionales de tu localidad.",
    },
    {
        id: 2,
        image: "calendar.svg",
        title: "Reserva un turno",
        description:"Selecciona en la agenda la fecha y hora que mejor te convenga para tu turno médico.",
    },
    {
        id: 3,
        image: "notification.svg",
        title: "Recibe recordatorio",
        description:"Dentro de las 48 hs. previas a tu cita recibirás un correo electrónico con los detalles de tu turno médico.",
    },
    {
        id: 4,
        image: "check.svg",
        title: "Cancela tu Turno",
        description:"Podés cancelar tu turnos con 24hs. de antelación.",
    }
]
