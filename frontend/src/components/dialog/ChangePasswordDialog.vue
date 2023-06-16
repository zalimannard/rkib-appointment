<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createInstitution"
      ok-button-text="Сохранить"
      title="Первый вход в систему. Смените пароль"
  >
    <password-text-field
        v-model="institutionName"
        :rules="[rules.required]"
        label="Новый пароль"
        required-asterisk
    />
  </base-dialog>
</template>

<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios, {AxiosError} from "axios";
import {showAlert} from "@/components/alert/AlertState";
import type {InstitutionRequest, InstitutionResponse} from "src/types/institution";
import {onMounted, provide} from "vue-demi";
import PasswordTextField from "@/components/textfield/PasswordTextField.vue";
import {useRouter} from "vue-router";

export default defineComponent({
  components: {
    PasswordTextField,
    BaseDialog,
    MaskedTextField
  },
  props: {
    value: Boolean,
    closeDialog: {
      type: Function,
      required: true
    }
  },

  setup(props, {emit}) {
    const institution = ref<InstitutionRequest>();
    const router = useRouter();

    const rules = {
      required: requiredRule
    };

    const setData = (data: InstitutionResponse) => {
      institution.value = {
        ...institution.value,
        name: data.name
      };
    }

    const institutionName = computed({
      get: () => institution.value?.name || "",
      set: (value) => {
        institution.value = {...institution.value, name: value};
      }
    });

    async function createInstitution() {
      let basicAuth = localStorage.getItem("auth");
      try {
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/people/me", {
          headers: {"Authorization": "Basic " + basicAuth}
        });

        axios({
          method: "put",
          url: import.meta.env.VITE_API_URL + "/api/v1/people/" + response.data.id,
          headers: {"Authorization": "Basic " + basicAuth},
          data: {
            password: institutionName.value,
            email: response.data.email,
            lastName: response.data.lastName,
            firstName: response.data.firstName,
            patronymic: response.data.patronymic
          }
        }).then(async () => {
          await router.push({name: "UserLoginView"});
          emit("personCreated")
        }).catch((error) => {
          console.log(error)
        });

      } catch (error) {
        if (error instanceof Error) {
          const axiosError = error as AxiosError;
          if (axiosError && axiosError.response && axiosError.response.status === 401) {
            showAlert("error", "Неверный логин или пароль");
          }
        } else {
          showAlert("error", "Непредвиденная ошибка");
        }
      }
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", institution.value);
        }
      }
    });

    provide("setData", setData);

    return {
      institution,
      rules,
      internalValue,
      institutionName,
      createInstitution,
      setData
    };
  }
});
</script>

<style scoped>

</style>
