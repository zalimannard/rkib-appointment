<template>
  <div class="d-flex align-center justify-center login-container">
    <v-col class="d-flex justify-center">
      <div class="card-container d-flex flex-column align-end">
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
                id="email"
                v-model="email"
                :readonly="loading"
                :rules="[rules.required]"
                autofocus
                label="Электронная почта"
            />

            <password-text-field
                id="password"
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
              </v-col>
            </v-row>
          </v-form>
        </v-card>
        <div class="text-right mt-3">
          <a id="loginAsPatient" class="employee-login-link" href="#" @click.prevent="onLoginAsPatient">
            Войти как пациент
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
import {passwordRule, requiredRule} from "@/rules";

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
    const router = useRouter();

    const email = ref("");
    const password = ref("");
    const role = ref<Role>({key: "Врач", value: "DOCTOR"});
    const roles = ref<Role[]>([
      {key: "Врач", value: "DOCTOR"},
      {key: "Регистратор", value: "REGISTRAR"},
      {key: "Админ", value: "ADMIN"}
    ]);
    const form = ref(false);
    const loading = ref(false);

    const rules = {
      required: requiredRule,
      password: passwordRule
    };

    async function onLoginAsPatient() {
      try {
        await router.push({name: "UserLoginView"});
      } catch (error) {
        console.error("Ошибка при переходе на UserLoginView:", error);
      }
    }

    async function onSubmit() {
      if (!form.value) return;

      let basicAuth = btoa(email.value + ":" + password.value);
      try {
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/employees/me", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        localStorage.setItem("auth", basicAuth);
        localStorage.setItem("role-api-name", role.value.value);

        if (password.value === "password") {
          await router.push({path: "/changepassword"});
        } else {
          if (response.data.roles.includes(role.value.value)) {
            if (role.value.value === "DOCTOR") {
              await router.push({path: "/doctor/home"});
            } else if (role.value.value === "REGISTRAR") {
              await router.push({path: "/registrar/doctor"});
            } else if (role.value.value === "ADMIN") {
              await router.push({path: "/admin/person"});
            }
          } else {
            showAlert("error", "У вас нет такой роли");
          }
        }

      } catch (error) {
        const axiosError = error as AxiosError;
        try {
          if (axiosError.response?.status === 401) {
            showAlert("error", "Неверный логин или пароль");
          }
        } catch (error) {
          showAlert("error", "Непредвиденная ошибка");
        }
      } finally {
        loading.value = false;
      }
    }

    return {
      alertState,
      email,
      password,
      role,
      roles,
      form,
      loading,
      rules,
      onSubmit,
      onLoginAsPatient
    };
  },
});
</script>

<style scoped>
.v-select {
  margin-bottom: -15px;
}

.login-container {
  height: 100vh;
}

.employee-login-link {
  color: #2196F3;
  text-decoration: none;
  text-align: right;
}

.employee-login-link:hover {
  color: #3F51B5;
}

.card-container {
  width: 500px;
}

.align-end {
  align-items: flex-end;
}
</style>
