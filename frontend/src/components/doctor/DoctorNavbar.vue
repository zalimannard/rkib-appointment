<template>
    <v-app-bar app>
        <v-spacer></v-spacer>
        <v-tabs v-model="activeTab">
            <v-tab to="/doctor/home">Главная</v-tab>
            <v-tab to="/doctor/schedule">Расписание</v-tab>
        </v-tabs>
        <v-spacer></v-spacer>
        <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on">{{ formattedName }}</v-btn>
            </template>
            <v-list>
                <v-list-item @click="changeRole">Сменить роль</v-list-item>
                <v-list-item @click="logout">Выйти</v-list-item>
            </v-list>
        </v-menu>
    </v-app-bar>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            activeTab: null,
            lastName: "Фамилия",
            firstName: "Имя",
            patronymic: "Отчество"
        };
    },
    methods: {
        changeRole() {
            // Реализуйте смену роли здесь
        },
        logout() {
            // Реализуйте выход из системы здесь
        },
        async fetchUserData() {
            let basicAuth = localStorage.getItem("auth");

            try {
                const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/employees/me", {
                    headers: {"Authorization": "Basic " + basicAuth}
                });
                this.lastName = response.data.person.lastName;
                this.firstName = response.data.person.firstName;
                this.patronymic = response.data.person.patronymic;
            } catch (error) {
                console.error("Ошибка при получении данных пользователя:", error);
            }
        }
    },
    mounted() {
        this.fetchUserData();
    },
    computed: {
        formattedName() {
            const nameInitial = this.firstName.charAt(0) + ".";
            const patronymicInitial = this.patronymic ? this.patronymic.charAt(0) + "." : "";
            return `${this.lastName} ${nameInitial}${patronymicInitial}`;
        }
    }
};
</script>