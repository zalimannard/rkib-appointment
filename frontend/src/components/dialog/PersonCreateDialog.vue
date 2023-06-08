<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createPerson"
      ok-button-text="Создать"
      title="Создание нового человека"
  >
    <v-row>
      <v-col>
        <masked-text-field
            v-model="personLastName"
            :rules="[rules.required]"
            capitalize-first-letter
            label="Фамилия"
            required-asterisk
        />
      </v-col>
      <v-col>
        <masked-text-field
            v-model="personFirstName"
            :rules="[rules.required]"
            capitalize-first-letter
            label="Имя"
            required-asterisk
        />
      </v-col>
      <v-col>
        <masked-text-field
            v-model="personPatronymic"
            capitalize-first-letter
            label="Отчество"
        />
      </v-col>
    </v-row>
  </base-dialog>
</template>

<script lang="ts">
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {computed, defineComponent, ref} from "vue";
import type {PersonRequest, PersonResponse} from "@/types/person";
import type {PatientRequest} from "@/types/patient";
import type {EmployeeRequest} from "@/types/employee";
import {requiredRule} from "@/rules";
import {onMounted, provide} from "vue-demi";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";

export default defineComponent({
  components: {
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
    const person = ref<PersonRequest>();
    const patient = ref<PatientRequest>();
    const employee = ref<EmployeeRequest>();

    const rules = {
      required: requiredRule
    };

    const setData = (data: PersonResponse) => {
      person.value = {
        ...person.value,
        lastName: data.lastName,
        firstName: data.firstName,
        patronymic: data.patronymic,
        username: data.username,
        password: ""
      }
    };

    const personLastName = computed({
      get: () => person.value?.lastName || "",
      set: (value) => {
        let firstNameValue = "";
        let patronymicValue = "";
        let usernameValue = "";
        let passwordValue = "";
        if (person.value) {
          firstNameValue = person.value?.firstName;
          patronymicValue = person.value?.patronymic;
          usernameValue = person.value?.username;
          passwordValue = person.value?.password;
        }
        person.value = {...person.value,
          lastName: value,
          firstName: firstNameValue,
          patronymic: patronymicValue,
          username: usernameValue,
          password: passwordValue
        };
      }
    });

    const personFirstName = computed({
      get: () => person.value?.firstName || "",
      set: (value) => {
        let lastNameValue = "";
        let patronymicValue = "";
        let usernameValue = "";
        let passwordValue = "";
        if (person.value) {
          lastNameValue = person.value?.lastName;
          patronymicValue = person.value?.patronymic;
          usernameValue = person.value?.username;
          passwordValue = person.value?.password;
        }
        person.value = {...person.value,
          lastName: lastNameValue,
          firstName: value,
          patronymic: patronymicValue,
          username: usernameValue,
          password: passwordValue
        };
      }
    });

    const personPatronymic = computed({
      get: () => person.value?.patronymic || "",
      set: (value) => {
        let lastNameValue = "";
        let firstNameValue = "";
        let usernameValue = "";
        let passwordValue = "";
        if (person.value) {
          lastNameValue = person.value?.lastName;
          firstNameValue = person.value?.firstName;
          usernameValue = person.value?.username;
          passwordValue = person.value?.password;
        }
        person.value = {...person.value,
          lastName: lastNameValue,
          firstName: firstNameValue,
          patronymic: value,
          username: usernameValue,
          password: passwordValue
        };
      }
    });

    function createPerson() {
      let basicAuth = localStorage.getItem("auth");
      // axios({
      //   method: "post",
      //   url: import.meta.env.VITE_API_URL + "/api/v1/people",
      //   headers: {"Authorization": "Basic " + basicAuth},
      //   data: {
      //     name: appointmentStatus.value?.name,
      //     type: appointmentStatus.value?.type
      //   }
      // }).then(() => {
      //   showAlert("success", "Статус обращений успешно создан");
      //   emit("appointmentStatusCreated")
      // }).catch((error) => {
      //   error.value.console.error(error)
      //   showAlert("error", "Ошибка при добавлении статуса обращений");
      // });
      emit("updateSearchInput", person.value);
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", person.value);
        }
      }
    });

    provide("setData", setData);

    return {
      rules,
      personLastName,
      personFirstName,
      personPatronymic,
      createPerson,
      internalValue
    };
  }
});
</script>

<style scoped>

</style>
