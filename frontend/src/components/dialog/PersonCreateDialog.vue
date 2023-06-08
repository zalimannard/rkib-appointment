<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createPerson"
      ok-button-text="Создать"
      title="Создание нового человека"
  >
    <v-col class="ma-0 pa-0">
      <v-row no-gutters>
        <v-col class="pr-4">
          <masked-text-field
              v-model="personLastName"
              :rules="[rules.required]"
              capitalize-first-letter
              label="Фамилия"
              required-asterisk
          />
        </v-col>
        <v-col class="px-2">
          <masked-text-field
              v-model="personFirstName"
              :rules="[rules.required]"
              capitalize-first-letter
              label="Имя"
              required-asterisk
          />
        </v-col>
        <v-col class="pl-4">
          <masked-text-field
              v-model="personPatronymic"
              capitalize-first-letter
              label="Отчество"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <role-select
              :multiple="true"
              :roles="selectedRoles"
              :update-search-input="nothing"
              include-patient
              @update:roles="selectedRoles = $event"
          />
        </v-col>
      </v-row>
      <v-row v-if="selectedRoles.includes('PATIENT')" no-gutters>
        <v-col class="pr-4" cols="4">
          <masked-text-field
              v-model="patientBirthdate"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.dateMask"
              :rules="[rules.birthdate]"
              capitalize-first-letter
              label="Дата рождения"
          />
        </v-col>
        <v-col class="px-2" cols="4">
          <masked-text-field
              v-model="patientPhoneNumber"
              :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
              :mask="masks.phoneMask"
              :rules="[rules.required, rules.phoneNumber]"
              capitalize-first-letter
              label="Телефон"
              required-asterisk
          />
        </v-col>
      </v-row>
      <v-row v-if="selectedRoles.includes('PATIENT')" no-gutters>
        <v-col>
          <masked-text-field
              v-model="patientAddress"
              capitalize-first-letter
              label="Адрес"
          />
        </v-col>
      </v-row>
      <v-row v-if="selectedRoles.includes('PATIENT')" no-gutters>
        <v-col>
          <masked-text-field
              v-model="patientOccupation"
              capitalize-first-letter
              label="Занятость"
          />
        </v-col>
      </v-row>
    </v-col>
  </base-dialog>
</template>

<script lang="ts">
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {computed, defineComponent, ref} from "vue";
import type {PersonRequest, PersonResponse} from "@/types/person";
import type {PatientRequest} from "@/types/patient";
import {dateRule, phoneRule, requiredRule} from "@/rules";
import {onMounted, provide} from "vue-demi";
import RoleSelect from "@/components/select/RoleSelect.vue";
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
    const patient = ref<PatientRequest>();

    const rules = {
      required: requiredRule,
      birthdate: dateRule,
      phoneNumber: phoneRule
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
        username: data.username,
        password: ""
      };
      console.log(data.patient)
      patient.value = {
        ...patient.value,
        personId: "",
        birthdate: data.patient.birthdate,
        phoneNumber: data.patient.phoneNumber,
        address: data.patient.address,
        occupation: data.patient.occupation,
      }
    };

    const selectedRoles = ref([]);

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
        person.value = {
          ...person.value,
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
        person.value = {
          ...person.value,
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
        person.value = {
          ...person.value,
          lastName: lastNameValue,
          firstName: firstNameValue,
          patronymic: value,
          username: usernameValue,
          password: passwordValue
        };
      }
    });

    const patientBirthdate = computed({
      get: () => patient.value?.birthdate || "",
      set: (value) => {
        let personIdValue = "";
        let phoneNumberValue = "";
        let addressValue = "";
        let occupationValue = "";
        if (patient.value) {
          personIdValue = patient.value?.personId;
          phoneNumberValue = patient.value?.phoneNumber;
          addressValue = patient.value?.address;
          occupationValue = patient.value?.occupation;
        }
        patient.value = {
          ...patient.value,
          personId: personIdValue,
          birthdate: value,
          phoneNumber: phoneNumberValue,
          address: addressValue,
          occupation: occupationValue,
        };
      }
    });

    const patientPhoneNumber = computed({
      get: () => patient.value?.phoneNumber || "",
      set: (value) => {
        let personIdValue = "";
        let birthdateValue = "";
        let addressValue = "";
        let occupationValue = "";
        if (patient.value) {
          personIdValue = patient.value?.personId;
          birthdateValue = patient.value?.birthdate;
          addressValue = patient.value?.address;
          occupationValue = patient.value?.occupation;
        }
        patient.value = {
          ...patient.value,
          personId: personIdValue,
          birthdate: birthdateValue,
          phoneNumber: value,
          address: addressValue,
          occupation: occupationValue,
        };
      }
    });

    const patientAddress = computed({
      get: () => patient.value?.address || "",
      set: (value) => {
        let personIdValue = "";
        let birthdateValue = "";
        let phoneNumberValue = "";
        let occupationValue = "";
        if (patient.value) {
          personIdValue = patient.value?.personId;
          birthdateValue = patient.value?.birthdate;
          phoneNumberValue = patient.value?.phoneNumber;
          occupationValue = patient.value?.occupation;
        }
        patient.value = {
          ...patient.value,
          personId: personIdValue,
          birthdate: birthdateValue,
          phoneNumber: phoneNumberValue,
          address: value,
          occupation: occupationValue,
        };
      }
    });

    const patientOccupation = computed({
      get: () => patient.value?.occupation || "",
      set: (value) => {
        let personIdValue = "";
        let birthdateValue = "";
        let phoneNumberValue = "";
        let addressValue = "";
        if (patient.value) {
          personIdValue = patient.value?.personId;
          birthdateValue = patient.value?.birthdate;
          phoneNumberValue = patient.value?.phoneNumber;
          addressValue = patient.value?.address;
        }
        patient.value = {
          ...patient.value,
          personId: personIdValue,
          birthdate: birthdateValue,
          phoneNumber: phoneNumberValue,
          address: addressValue,
          occupation: value,
        };
      }
    });

    function createPerson() {
      let basicAuth = localStorage.getItem("auth");

      console.log(selectedRoles.value);
    }

    function nothing() {
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
      internalValue,
      nothing,
      selectedRoles,
      masks,
      backspaceHandlers,
      patientBirthdate,
      patientPhoneNumber,
      patientAddress,
      patientOccupation
    };
  }
});
</script>

<style scoped>

</style>
