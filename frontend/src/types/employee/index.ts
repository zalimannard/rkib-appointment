import type {PersonResponse} from "@/types/person";

export interface EmployeeRequest {
    personId: string;
    roles: string[];
}

export interface EmployeeResponse {
    id: string;
    roles: string[];
    person: PersonResponse;
}
