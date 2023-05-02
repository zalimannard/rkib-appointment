import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import DoctorView from "../views/DoctorView.vue";
import RegistrarView from "../views/RegistrarView.vue";
import AdminView from "../views/AdminView.vue";

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
      component: DoctorView
    },
    {
      path: "/registrar",
      name: "RegistrarView",
      component: RegistrarView
    },
    {
      path: "/admin",
      name: "AdminView",
      component: AdminView
    }
  ]
});

export default router;
