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
          to="/patient/home"
          width="150">
        Мои приёмы
      </v-tab>

      <v-tab
          color="white"
          slider-color="white"
          to="/patient/schedule"
          width="150">
        Расписание
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
      this.$router.push({name: "UserLoginView"});
    },
    async fetchUserData() {
      let basicAuth = localStorage.getItem("auth");

      try {
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/people/me", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        this.lastName = response.data.lastName;
        this.firstName = response.data.firstName;
        this.patronymic = response.data.patronymic;
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