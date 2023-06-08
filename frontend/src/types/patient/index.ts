export interface PatientRequest {
    phoneNumber: string;
    birthdate: string;
    address: string;
    occupation: string;
    personId: string;
}

export interface PatientResponse {
    id: string;
    phoneNumber: string;
    birthdate: string;
    address: string;
    occupation: string;
}
