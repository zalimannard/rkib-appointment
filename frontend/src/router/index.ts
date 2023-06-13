import {createRouter, createWebHistory} from "vue-router";
import LoginView from "../views/LoginView.vue";
import DoctorView from "../views/DoctorView.vue";
import RegistrarView from "../views/RegistrarView.vue";
import AdminView from "../views/AdminView.vue";
import DoctorHome from "@/views/doctor/DoctorHome.vue";
import RegistrarDoctor from "@/views/registrar/RegistrarDoctor.vue";
import RegistrarPatient from "@/views/registrar/RegistrarPatient.vue";
import AdminAppointment from "@/views/admin/AdminAppointment.vue";
import AdminAppointmentStatus from "@/views/admin/AdminAppointmentStatus.vue";
import AdminInstitution from "@/views/admin/AdminInstitution.vue";
import AdminPerson from "@/views/admin/AdminPerson.vue";
import AdminProcedure from "@/views/admin/AdminProcedure.vue";
import AdminSchedule from "@/views/admin/AdminSchedule.vue";
import AdminScheduleStatus from "@/views/admin/AdminScheduleStatus.vue";
import PatientView from "@/views/PatientView.vue";
import PatientHome from "@/views/patient/PatientHome.vue";
import PatientSchedule from "@/views/patient/PatientSchedule.vue";
import UserLoginView from "@/views/UserLoginView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/login",
            name: "UserLoginView",
            component: UserLoginView
        },
        {
            path: "/employeelogin",
            name: "LoginView",
            component: LoginView
        },
        {
            path: "/doctor",
            name: "DoctorView",
            component: DoctorView,
            children: [
                {
                    path: "home",
                    component: DoctorHome
                }
            ]
        },
        {
            path: "/registrar",
            name: "RegistrarView",
            component: RegistrarView,
            children: [
                {
                    path: "doctor",
                    component: RegistrarDoctor
                },
                {
                    path: "patient",
                    component: RegistrarPatient
                }
            ]
        },
        {
            path: "/admin",
            name: "AdminView",
            component: AdminView,
            children: [
                {
                    path: "appointment",
                    component: AdminAppointment
                },
                {
                    path: "appointmentstatus",
                    component: AdminAppointmentStatus
                },
                {
                    path: "institution",
                    component: AdminInstitution
                },
                {
                    path: "person",
                    component: AdminPerson
                },
                {
                    path: "procedure",
                    component: AdminProcedure
                },
                {
                    path: "schedule",
                    component: AdminSchedule
                },
                {
                    path: "schedulestatus",
                    component: AdminScheduleStatus
                }
            ]
        },
        {
            path: "/patient",
            name: "PatientView",
            component: PatientView,
            children: [
                {
                    path: "home",
                    component: PatientHome
                },
                {
                    path: "schedule",
                    component: PatientSchedule
                }
            ]
        },
    ]
});

export default router;
