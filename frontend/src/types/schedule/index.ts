import type {EmployeeResponse} from "@/types/employee";
import type {ProcedureResponse} from "@/types/procedure";
import type {AppointmentResponse} from "@/types/appointment";
import type {ScheduleStatusResponse} from "@/types/schedulestatus";

export interface ScheduleRequest {
    appointmentTime: string;
    commentary: string;
    doctorId: string;
    procedureId: string;
    appointmentId: string;
    statusId: string;
}

export interface ScheduleResponse {
    id: string;
    appointmentTime: string;
    commentary: string;
    doctor: EmployeeResponse;
    procedure: ProcedureResponse;
    appointment: AppointmentResponse;
    status: ScheduleStatusResponse;
}
