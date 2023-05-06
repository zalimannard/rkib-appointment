<template>

  <div
    class="d-flex align-center justify-center login-container"
  >

    <v-col>

      <CustomAlert
        :alertText="errorText"
        :showAlert="showErrorAlert"
        type="error"
      />

      <v-card
        class="mx-auto px-6 pt-6 pb-4"
        rounded="lg"
        variant="outlined"
        width="500"
      >

        <v-form
          v-model="form"
          @submit.prevent="onSubmit"
        >
          <v-text-field
            v-model="username"
            :readonly="loading"
            :rules="[rules.required, rules.latinOrDigitOnly]"
            autofocus
            class="mb-2"
            clearable
            label="Логин"
            rounded="lg"
            variant="outlined"
          ></v-text-field>

          <v-text-field
            v-model="password"
            :append-inner-icon="passwordShow ?'mdi-eye':'mdi-eye-off'"
            :readonly="loading"
            :rules="[rules.required, rules.latinOrDigitOnly]"
            :type="passwordShow ?'text': 'password'"
            clearable
            label="Пароль"
            variant="outlined"
            @click:append-inner="passwordShow=!passwordShow"
          ></v-text-field>

          <v-row>
            <v-col class="pt-5 pb-0">
              <v-select
                v-model="role"
                :items="roles"
                item-title="key"
                item-value="value"
                label="Роль"
                persistent-hint
                return-object
                single-line
                variant="outlined"
              ></v-select>
            </v-col>
            <v-col class="pt-5 pb-0">
              <v-btn
                :disabled="!form"
                :loading="loading"
                block
                color="indigo"
                rounded="lg"
                size="x-large"
                type="submit"
                variant="elevated"
                @click="submit"
              >Войти
              </v-btn>
            </v-col>
          </v-row>

        </v-form>
      </v-card>
    </v-col>
  </div>
</template>

<style scoped>
.login-container {
    height: 100vh;
}
</style>

<script>

import axios from "axios";
import CustomAlert from "@/components/CustomAlert.vue";

export default {
  data() {
    return {
      username: "",
      password: "",
      passwordShow: false,
      role:
        { key: "Врач", value: "DOCTOR" },
      roles: [
        { key: "Врач", value: "DOCTOR" },
        { key: "Регистратор", value: "REGISTRAR" },
        { key: "Админ", value: "ADMIN" }
      ],
      form: false,
      loading: false,
      errorText: "",
      showErrorAlert: false,

      rules: {
        required: value => !!value || "Поле не должно быть пустым.",
        latinOrDigitOnly: value => {
          const pattern = /^[a-zA-Z0-9]+$/;
          return pattern.test(value) || "Недопустимые символы";
        }
      }

    };
  },
  components: {
    CustomAlert
  },
  methods: {
    async onSubmit() {
      if (!this.form) return;

      let basicAuth = btoa(this.username + ":" + this.password);
      try {
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/employees/me", {
          headers: { "Authorization": "Basic " + basicAuth }
        });

        if (response.data.roles.includes(this.role.value)) {
          localStorage.setItem("auth", basicAuth);
          localStorage.setItem("role-api-name", this.role.value);
          if (this.role.value === "DOCTOR") {
            this.$router.push({ name: "DoctorView" });
          } else if (this.role.value === "REGISTRAR") {
            this.$router.push({ name: "RegistrarView" });
          } else if (this.role.value === "ADMIN") {
            this.$router.push({ name: "AdminView" });
          }

        } else {
          this.showErrorAlert = true;
          this.errorText = "У вас нет такой роли";
          setTimeout(() => (this.showErrorAlert = false), 5000);

        }
      } catch (error) {
        try {
          if (error.response.status === 401) {
            this.showErrorAlert = true;
            this.errorText = "Неверный логин или пароль";
            setTimeout(() => (this.showErrorAlert = false), 5000);
          }
        } catch (error) {
          this.showErrorAlert = true;
          this.errorText = "Непредвиденная ошибка";
          setTimeout(() => (this.showErrorAlert = false), 5000);
        }
      } finally {
        this.loading = false;
      }
    },

    submit() {

    }
  }
};
</script>
