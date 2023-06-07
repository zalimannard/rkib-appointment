<template>
  <div class="d-flex align-center justify-center login-container">
    <v-col class="d-flex justify-center">
      <div class="card-container d-flex flex-column align-end">
        <v-card class="mx-auto" rounded="lg" variant="outlined" width="500">
          <v-form
              v-model="form"
              class="pa-6"
              @submit.prevent="onSubmit"
          >

            <base-text-field
                id="username"
                v-model="username"
                :readonly="loading"
                :rules="[rules.requiredRule, rules.usernameRule]"
                autofocus
                label="Логин"
            />

            <password-text-field
                id="password"
                v-model="password"
                :readonly="loading"
                :rules="[rules.requiredRule, rules.passwordRule]"
                autofocus
                label="Пароль"
            />

            <v-row>
              <v-col>
                <base-button
                    id="login"
                    :disabled="!form"
                    :loading="loading"
                    block
                    type="regular"
                    @click="onSubmit"
                >
                  Войти
                </base-button>
              </v-col>
            </v-row>
          </v-form>
        </v-card>
        <div class="text-right mt-3">
          <a class="employee-login-link" href="#" @click="onLoginAsEmployee">
            Войти как сотрудник
          </a>
        </div>
      </div>
    </v-col>
  </div>
</template>

<script lang="ts">
import axios, {AxiosError} from "axios";
import BaseTextField from "@/components/textfield/BaseTextField.vue";
import PasswordTextField from "@/components/textfield/PasswordTextField.vue";
import BaseButton from "@/components/button/CustomButton.vue";
import {defineComponent, inject, ref} from "vue";
import {showAlert} from "@/components/alert/AlertState";
import {useRouter} from "vue-router";
import {passwordRule, requiredRule, usernameRule} from "@/rules";

interface Role {
  key: string;
  value: string;
}

export default defineComponent({
  components: {
    BaseButton,
    BaseTextField,
    PasswordTextField
  },
  setup() {
    const alertState = inject('alertState');

    const username = ref("");
    const password = ref("");
    const passwordShow = ref(false);
    const role = ref<Role>({key: "Врач", value: "DOCTOR"});
    const roles = ref<Role[]>([
      {key: "Врач", value: "DOCTOR"},
      {key: "Регистратор", value: "REGISTRAR"},
      {key: "Админ", value: "ADMIN"}
    ]);
    const form = ref(false);
    const loading = ref(false);
    const errorText = ref("");
    const showErrorAlert = ref(false);
    const router = useRouter();

    const rules = {
      requiredRule,
      usernameRule,
      passwordRule
    };

    async function onLoginAsEmployee() {
      await router.push({name: "LoginView"});
    }

    async function onSubmit() {

      if (!form.value) return;

      let basicAuth = btoa(username.value + ":" + password.value);
      try {
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/people/me", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        if (response.data.patient != null) {
          console.log(response.data.patient)
          localStorage.setItem("auth", basicAuth);
          localStorage.setItem("role-api-name", "PATIENT");
          console.log("aboba")
          await router.push({name: "PatientView"});
        } else {
          showAlert("error", "У вас нет такой роли");
        }
      } catch (error) {
        if (error instanceof Error) {  // Проверка типа
          const axiosError = error as AxiosError;  // Утверждение типа
          if (axiosError && axiosError.response && axiosError.response.status === 401) {
            showAlert("error", "Неверный логин или пароль");
          }
        } else {
          showAlert("error", "Непредвиденная ошибка");
        }
      }
    }

    return {
      alertState,
      username,
      password,
      passwordShow,
      role,
      roles,
      form,
      loading,
      errorText,
      showErrorAlert,
      rules,
      onSubmit,
      onLoginAsEmployee
    };
  },
});
</script>

<style scoped>
.login-container {
  height: 100vh;
}

.employee-login-link {
  color: #1976d2;
  text-decoration: none;
  text-align: right;
}

.employee-login-link:hover {
  color: #0d47a1;
}

.card-container {
  width: 500px;
}

.align-end {
  align-items: flex-end;
}
</style>
