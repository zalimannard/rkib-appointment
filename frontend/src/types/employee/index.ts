import type {PersonResponse} from "@/types/person";
import type {AppointmentResponse} from "@/types/appointment";

export interface EmployeeRequest {
    personId: string;
    roles: string[];
}

export interface EmployeeResponse {
    id: string;
    roles: string[];
    person: PersonResponse;
    appointment: AppointmentResponse;
}
