<template>
  <div
    class="d-flex align-center justify-center"
    style="height: 100vh"
  >
    <v-card
      class="mx-auto px-6 pt-6 pb-4"
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
          :rules="[required]"
          class="mb-2"
          clearable
          label="Логин"
          variant="outlined"
        ></v-text-field>

        <v-text-field
          v-model="password"
          :append-inner-icon="passwordShow ?'mdi-eye':'mdi-eye-off'"
          @click:append-inner="passwordShow=!passwordShow"
          :readonly="loading"
          :rules="[required]"
          :type="passwordShow ?'text': 'password'"
          clearable
          label="Пароль"
          variant="outlined"
        ></v-text-field>

        <v-row>
          <v-col class="pt-5 pb-0">
            <v-select
              v-model="role"
              :items="roles"
              :rules="[required]"
              label="Роль"
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      passwordShow: false,
      role: "",
      roles: ["Врач", "Регистратор", "Админ"],
      form: false,
      loading: false
    };
  },
  methods: {
    onSubmit() {
      if (!this.form) return;

      this.loading = true;

      setTimeout(() => (this.loading = false), 2000);
    },

    required(v) {
      return !!v;
    },

    submit() {
      console.log("Логин:", this.username);
      console.log("Пароль:", this.password);
      console.log("Роль:", this.role);
    }
  }
};
</script>
