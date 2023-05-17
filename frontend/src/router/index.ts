import {createRouter, createWebHistory} from "vue-router";
import LoginView from "../views/LoginView.vue";
import DoctorView from "../views/DoctorView.vue";
import RegistrarView from "../views/RegistrarView.vue";
import AdminView from "../views/AdminView.vue";
import DoctorHome from "@/components/doctor/DoctorHome.vue";
import DoctorSchedule from "@/components/doctor/DoctorSchedule.vue";
import RegistrarDoctor from "@/components/registrar/RegistrarDoctor.vue";
import RegistrarPatient from "@/components/registrar/RegistrarPatient.vue";
import AdminAppointment from "@/components/admin/AdminAppointment.vue";
import AdminAppointmentStatus from "@/components/admin/AdminAppointmentStatus.vue";
import AdminInstitution from "@/components/admin/AdminInstitution.vue";
import AdminPerson from "@/components/admin/AdminPerson.vue";
import AdminProcedure from "@/components/admin/AdminProcedure.vue";
import AdminSchedule from "@/components/admin/AdminSchedule.vue";
import AdminScheduleStatus from "@/components/admin/AdminScheduleStatus.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/login",
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
                },
                {
                    path: "schedule",
                    component: DoctorSchedule
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
        }
    ]
});

export default router;
