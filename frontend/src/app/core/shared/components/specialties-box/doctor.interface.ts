export interface Doctor {
    email: string;
    firstName: string | null;
    secondName: string | null;
    lastName: string | null;
    DNI: string;
    specialty: string;
    phoneNumber: number | null;
    morning: boolean | null;
    afternoon: boolean | null;
    night: boolean | null;
    postalCode: number | null;
    licenseNumber: string | null;
    address: string | null;
}