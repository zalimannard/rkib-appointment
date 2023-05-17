<template>
    <v-app-bar
            app
            color="indigo">

        <v-tabs
                v-model="activeTab"
                align-tabs="title">
            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/institution">
                Учреждения
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/procedure">
                Услуги
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/person">
                Люди
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/schedule">
                Графики
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/appointment">
                Обращения
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/appointmentstatus">
                Статусы обращений
            </v-tab>

            <v-tab
                    color="white"
                    slider-color="white"
                    to="/admin/schedulestatus">
                Статусы графиков
            </v-tab>
        </v-tabs>

        <v-spacer>

        </v-spacer>

        <v-menu>
            <template
                    v-slot:activator="{ props }">
                <v-btn
                        color="white"
                        dark
                        v-bind="props">
                    {{ formattedName }}
                </v-btn>
            </template>

            <v-list>
                <v-list-item
                        @click="logout">
                    Выйти
                </v-list-item>
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
        logout() {
            localStorage.clear();
            this.$router.push({name: "LoginView"});
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