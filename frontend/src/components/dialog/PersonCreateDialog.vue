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
            :rules="rules.requiredRule"
            capitalize-first-letter
            label="Фамилия"
            required-asterisk
        />
      </v-col>
      <v-col>
        <masked-text-field
            v-model="personFirstName"
            :rules="rules.requiredRule"
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

    <v-row no-gutters>
      <v-col class="pr-4" cols="4">
        <masked-text-field
            v-model="patientBirthdate"
            :rules="rules.dateRule"
            :mask="masks.dateMask"
            :handle-backspace="backspaceHandlers.handleBackspaceForDate"
            label="Дата рождения"
            placeholder="ДД.ММ.ГГГГ"
        />
      </v-col>
      <v-col class="px-2" cols="4">
        <masked-text-field
            v-model="patientPhoneNumber"
            :rules="rules.phoneRule"
            :mask="masks.phoneMask"
            :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
            label="Телефон"
            required-asterisk
        />
      </v-col>
    </v-row>

    <v-row no-gutters>
      <v-col>
        <masked-text-field
            v-model="patientAddress"
            label="Адрес"
        />
      </v-col>
    </v-row>

    <v-row no-gutters>
      <v-col>
        <masked-text-field
            v-model="patientOccupation"
            capitalize-first-letter
            label="Занятость"
        />
      </v-col>
    </v-row>
  </base-dialog>
</template>

<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {dateRule, phoneRule, requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios from "axios";
import RoleSelect from "@/components/select/RoleSelect.vue";
import {showAlert} from "@/components/alert/AlertState";
import type {PersonRequest, PersonResponse} from "@/types/person";
import type {PatientRequest, PatientResponse} from "@/types/patient";
import type {EmployeeRequest, EmployeeResponse} from "@/types/employee";
import {onMounted, provide} from "vue-demi";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";

export default defineComponent({
  components: {
    RoleSelect,
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
    const patient = ref<PatientRequest>({
      birthdate: "",
      phoneNumber: "+7(",
      address: "",
      occupation: "",
      personId: ""
    });
    const employee = ref<EmployeeRequest>();

    const rules = {
      requiredRule,
      dateRule,
      phoneRule
    };
    const masks = {
      dateMask,
          phoneMask
    };
    const backspaceHandlers = {
      handleBackspaceForDate,
          handleBackspaceForPhoneNumber
    };

    const setData = (data: PersonResponse) => {
      person.value = {
        ...person.value,
        lastName: data.lastName,
        firstName: data.firstName,
        patronymic: data.patronymic,
        username: "",
        password: ""
      };
      patient.value = {
        ...patient.value,
        birthdate: "",
        phoneNumber: "",
        address: "",
        occupation: "",
        personId: ""
      };
    }

    const personLastName = computed({
      get: () => person.value?.lastName || "",
      set: (value) => {
        person.value = {...person.value,
          lastName: value,
          firstName: person.value?.firstName || "",
          patronymic: person.value?.patronymic || "",
          username: person.value?.username || "",
          password: person.value?.password || ""};
      }
    });

    const personFirstName = computed({
      get: () => person.value?.firstName || "",
      set: (value) => {
        person.value = {...person.value,
          lastName: person.value?.lastName || "",
          firstName: value,
          patronymic: person.value?.patronymic || "",
          username: person.value?.username || "",
          password: person.value?.password || ""};
      }
    });

    const personPatronymic = computed({
      get: () => person.value?.patronymic || "",
      set: (value) => {
        person.value = {...person.value,
          lastName: person.value?.lastName || "",
          firstName: person.value?.firstName || "",
          patronymic: value,
          username: person.value?.username || "",
          password: person.value?.password || ""};
      }
    });

    const personUsername = computed({
      get: () => person.value?.username || "",
      set: (value) => {
        person.value = {...person.value,
          lastName: person.value?.lastName || "",
          firstName: person.value?.firstName || "",
          patronymic: person.value?.patronymic || "",
          username: value,
          password: person.value?.password || ""};
      }
    });

    const personPassword = computed({
      get: () => person.value?.password || "",
      set: (value) => {
        person.value = {...person.value,
          lastName: person.value?.lastName || "",
          firstName: person.value?.firstName || "",
          patronymic: person.value?.patronymic || "",
          username: person.value?.username || "",
          password: value}
      }
    });

    const patientBirthdate = computed({
      get: () => patient.value?.birthdate || "",
      set: (value) => {
        patient.value = {...patient.value,
          birthdate: value,
          phoneNumber: patient.value?.phoneNumber || "+7(",
          address: patient.value?.address || "",
          occupation: patient.value?.occupation || "",
          personId: ""};
      }
    });

    const patientPhoneNumber = computed({
      get: () => patient.value?.phoneNumber || "",
      set: (value) => {
        patient.value = {...patient.value,
          birthdate: patient.value?.birthdate || "",
          phoneNumber: value,
          address: patient.value?.address || "",
          occupation: patient.value?.occupation || "",
          personId: ""};
      }
    });

    const patientAddress = computed({
      get: () => patient.value?.address || "",
      set: (value) => {
        patient.value = {...patient.value,
          birthdate: patient.value?.birthdate || "",
          phoneNumber: patient.value?.phoneNumber || "+7(",
          address: value,
          occupation: patient.value?.occupation || "",
          personId: ""};
      }
    });

    const patientOccupation = computed({
      get: () => patient.value?.occupation || "",
      set: (value) => {
        patient.value = {...patient.value,
          birthdate: patient.value?.birthdate || "",
          phoneNumber: patient.value?.phoneNumber || "+7(",
          address: patient.value?.address || "",
          occupation: value,
          personId: ""};
      }
    });

    function createPerson() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/people",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          // TODO: Потом убрать username и password отсюда вообще
          username: (Math.random() + 1).toString(36).substring(7),
          password: "password",
          lastName: person.value?.lastName,
          firstName: person.value?.firstName,
          patronymic: person.value?.patronymic
        }
      }).then((response) => {
        let personId = response.data.id;
        let phoneNumber = patient.value?.phoneNumber
            .replace(/\(/g, '')
            .replace(/\)/g, '')
            .replace(/-/g, '');
        let birthdate = null;
        if (patient.value?.birthdate) {
          let dateParts = patient.value?.birthdate.split(".");
          if (dateParts.length === 3) {
            birthdate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
          }
        }
        axios({
          method: "post",
          url: import.meta.env.VITE_API_URL + "/api/v1/people",
          headers: {"Authorization": "Basic " + basicAuth},
          data: {
            personId: personId,
            phoneNumber: phoneNumber,
            birthdate: birthdate,
            address: patient.value?.address,
            occupation: patient.value?.occupation
          }
        }).then(() => {
          showAlert("success", "Человек успешно создан");
          emit("appointmentStatusCreated")
        }).catch((error) => {
          error.value.console.error(error)
          showAlert("error", "Ошибка при создании человека");
        });

        showAlert("success", "Человек успешно создан");
        emit("personCreated")
      }).catch((error) => {
        error.value.console.error(error)
        showAlert("error", "Ошибка при создании человека");
      });
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
      personLastName,
      personFirstName,
      personPatronymic,
      personUsername,
      personPassword,
      patientBirthdate,
      patientPhoneNumber,
      patientAddress,
      patientOccupation,
      internalValue,
      setData,
      createPerson,
      rules,
      masks,
      backspaceHandlers
    };
  }
});

</script>
