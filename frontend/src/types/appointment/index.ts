import type {PatientResponse} from "@/types/patient";
import type {InstitutionResponse} from "@/types/institution";
import type {AppointmentStatusResponse} from "@/types/appointmentstatus";

export interface AppointmentRequest {
    doctorNote: string;
    diagnosis: string;
    commentary: string;
    patientId: string;
    institutionId: string;
    statusId: string;
}

export interface AppointmentResponse {
    id: string;
    doctorNote: string;
    diagnosis: string;
    commentary: string;
    patient: PatientResponse;
    institution: InstitutionResponse;
    status: AppointmentStatusResponse;
}
