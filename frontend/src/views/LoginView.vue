<template>

  <custom-alert
    v-model="showErrorAlert"
    alertType="error"
  />

  <div
    class="d-flex align-center justify-center login-container"
  >

    <v-col>

      <v-card
        class="mx-auto"
        rounded="lg"
        variant="outlined"
        width="500"
      >

        <v-form
          v-model="form"
          class="pa-6"
          @submit.prevent="onSubmit"
        >

          <base-text-field
            v-model="username"
            :readonly="loading"
            :rules="[rules.required, rules.username]"
            autofocus
            label="Логин"
          />

          <password-text-field
            v-model="password"
            :readonly="loading"
            :rules="[rules.required, rules.password]"
            autofocus
            label="Пароль"
          />

          <v-row>
            <v-col class="pb-0 mb-0">
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
            <v-col>
              <base-button
                :disabled="!form"
                :loading="loading"
                block
                type="regular"
                @click="onSubmit"
              >
                Войти
              </base-button>

              <!--              <v-btn-->
              <!--                :disabled="!form"-->
              <!--                :loading="loading"-->
              <!--                block-->
              <!--                color="indigo"-->
              <!--                rounded="lg"-->
              <!--                size="x-large"-->
              <!--                type="submit"-->
              <!--                variant="elevated"-->
              <!--                @click="submit"-->
              <!--              >Войти-->
              <!--              </v-btn>-->
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
import CustomAlert from "@/components/custom/alert/CustomAlert.vue";
import BaseTextField from "@/components/custom/textfield/BaseTextField.vue";
import PasswordTextField from "@/components/custom/textfield/PasswordTextField.vue";
import BaseButton from "@/components/custom/button/CustomButton.vue";

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
        required: value => !!value || "Не должно быть пустым",
        username: value => {
          const pattern = /^[a-zA-Z0-9]+$/;
          return pattern.test(value) || "Недопустимые символы";
        },
        password: value => {
          const pattern = /^[a-zA-Z0-9!@#$%^&*]+$/;
          return pattern.test(value) || "Недопустимые символы";
        }
      }
    };
  },
  components: {
    BaseButton,
    BaseTextField,
    PasswordTextField,
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
    }
  }
};
</script>
