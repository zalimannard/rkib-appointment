<template>

  <div
    class="d-flex align-center justify-center login-container"
  >

    <v-col>

      <v-fade-transition>
        <v-alert
          v-show="showAlert"
          :text="alertText"
          class="alert-style"
          color="red"
          location="top"
          position="fixed"
          rounded="lg"
          type="error"
          variant="elevated"
          width="500"
        ></v-alert>
      </v-fade-transition>

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

.alert-style {
    margin-top: 10pt;
}
</style>

<script>

import axios from "axios";

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
      alertText: "",
      showAlert: false,

      rules: {
        required: value => !!value || "Поле не должно быть пустым.",
        counter: value => value.length <= 20 || "Max 20 characters",
        latinOrDigitOnly: value => {
          const pattern = /^[a-zA-Z0-9]+$/;
          return pattern.test(value) || "Недопустимые символы";
        }
      }

    };
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
          localStorage.setItem("role-ui-name", this.role.key);
          localStorage.setItem("role-api-name", this.role.value);
        } else {
          this.showAlert = true;
          this.alertText = "У вас нет такой роли";
          setTimeout(() => (this.showAlert = false), 5000);
        }
      } catch (error) {
        this.showAlert = true;
        this.alertText = "Неверный логин или пароль";
        setTimeout(() => (this.showAlert = false), 5000);
      } finally {
        this.loading = false;
      }
    },

    submit() {
      console.log("Логин:", this.username);
      console.log("Пароль:", this.password);
      console.log("Роль:", this.role);
      console.log("Идентификатор:", this.userId);
    }
  }
};
</script>
