export interface Doctor {
    id: string;
    email: string;
    firstName: string;
    secondName: string;
    lastName: string;
    DNI: string;
    specialty: string;
    phoneNumber: number;
    morning: boolean;
    afternoon: boolean;
    night: boolean;
    postalCode: number;
    licenseNumber: string;
    address: string;
    imageUrl?: string,
    validUser: boolean
}
