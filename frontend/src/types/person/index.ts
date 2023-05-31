import type {PatientResponse} from "@/types/patient";
import type {EmployeeResponse} from "@/types/employee";

export interface PersonRequest {
    lastName: string;
    firstName: string;
    patronymic: string;
    username: string;
    password: string;
}

export interface PersonResponse {
    id: string;
    lastName: string;
    firstName: string;
    patronymic: string;
    username: string;
    patient: PatientResponse;
    employee: EmployeeResponse;
}
