<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="personRequest.lastName"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Фамилия"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="personRequest.firstName"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Имя"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="personRequest.patronymic"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Отчество"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="personRequest.email"
              class="header-cell"
              density="comfortable"
              label="Email"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="patientRequest.birthdate"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.dateMask"
              class="header-cell"
              density="comfortable"
              label="Дата рождения"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="patientRequest.phoneNumber"
              :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
              :mask="masks.phoneMask"
              class="header-cell"
              density="comfortable"
              label="Телефон"
              @input="updateSearch"
          />
        </th>
        <th class="text-left role-field" scope="col">
          <role-select
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              include-patient
              roles="employeeRequest.roles"
              @update:roles="employeeRequest.roles = $event"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredPeople"
          :key="item.id"
          :class="{ 'light-row': index % 2 === 0, 'dark-row': index % 2 !== 0 }"
      >
        <td>{{ item.lastName }}</td>
        <td>{{ item.firstName }}</td>
        <td>{{ item.patronymic }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.patient?.birthdate ? masks.dateMask(utils.fromIsoToDefault(item.patient.birthdate)) : "" }}</td>
        <td>{{ item.patient?.phoneNumber ? masks.phoneMask(item.patient.phoneNumber) : "" }}</td>
        <td>{{ calcRolesToShow(item) }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script lang="ts">

import {defineComponent, ref} from "vue";
import CustomTable from "@/components/table/CustomTable.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import type {PersonRequest, PersonResponse} from "@/types/person";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";
import {checkFilter, fromDefaultToIso, fromIsoToDefault, roleCodeToString} from "@/utils";
import {onMounted} from "vue-demi";
import type {PatientRequest} from "@/types/patient";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";
import RoleSelect from "@/components/select/RoleSelect.vue";
import type {EmployeeRequest} from "@/types/employee";

export default defineComponent({
  components: {
    RoleSelect,
    MaskedTextField,
    CustomTable
  },

  setup(props, {emit}) {
    const personRequest = ref<PersonRequest>({
      lastName: "",
      firstName: "",
      patronymic: "",
      email: "",
      password: ""
    });
    const patientRequest = ref<PatientRequest>({
      birthdate: "",
      phoneNumber: "+7(",
      address: "",
      occupation: "",
      personId: ""
    });
    const employeeRequest = ref<EmployeeRequest>({
      roles: [],
      personId: ""
    });

    const people = ref<PersonResponse[]>([]);
    const filteredPeople = ref<PersonResponse[]>([]);

    const masks = {
      dateMask,
      phoneMask
    };
    const backspaceHandlers = {
      handleBackspaceForDate,
      handleBackspaceForPhoneNumber
    };
    const utils = {
      fromIsoToDefault,
      fromDefaultToIso,
      roleCodeToString
    }

    const requestPerson = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/people", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        people.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    }

    onMounted(() => {
      emit("provideRequestPerson", requestPerson);
      requestPerson();
    });

    function calcRoles(item: PersonResponse) {
      let roles = []
      if (item.employee) {
        for (const element of item.employee.roles) {
          roles.push(element);
        }
      }
      if (item.patient) {
        roles.push("PATIENT")
      }
      return roles;
    }

    function calcRolesToShow(item: PersonResponse) {
      let roles = calcRoles(item);
      return roles
          ? roles.map(utils.roleCodeToString).join(", ")
          : "";
    }

    function updateSearch() {
      emit("updateSearchInput", {
        ...personRequest.value,
        patient: patientRequest.value,
        employee: employeeRequest.value
      });
      onEditFilter();
    }

    function onEditFilter() {
      filteredPeople.value = filterPeople(people.value);
    }

    function filterPeople(people: PersonResponse[]) {
      return people.filter(filterPerson);
    }

    function filterPerson(person: PersonResponse) {
      return (
          checkFilter(person.lastName, personRequest.value.lastName) &&
          checkFilter(person.firstName, personRequest.value.firstName) &&
          checkFilter(person.patronymic, personRequest.value.patronymic) &&
          checkFilter(person.email, personRequest.value.email) &&
          (person.patient
              ? (checkFilter(utils.fromIsoToDefault(person.patient.birthdate), patientRequest.value.birthdate))
              : !patientRequest.value.birthdate) &&
          (person.patient
              ? (checkFilter(masks.phoneMask(person.patient.phoneNumber), patientRequest.value.phoneNumber))
              : patientRequest.value.phoneNumber === "+7(") &&
          ((employeeRequest.value.roles.length === 0) || (employeeRequest.value.roles[0] == null))
          || (calcRoles(person).includes(employeeRequest.value.roles[0]))
      );
    }

    function resetFilters() {
      personRequest.value = {
        lastName: "",
        firstName: "",
        patronymic: "",
        email: "",
        password: ""
      }
      patientRequest.value = {
        birthdate: "",
        phoneNumber: "+7(",
        address: "",
        occupation: "",
        personId: ""
      };
      employeeRequest.value = {
        roles: [],
        personId: ""
      };
      onEditFilter();
    }

    return {
      people,
      filteredPeople,
      personRequest,
      patientRequest,
      employeeRequest,
      updateSearch,
      resetFilters,
      calcRoles,
      calcRolesToShow,
      utils,
      masks,
      backspaceHandlers
    }
  }
})
</script>

<style scoped>
.role-field {
  width: 200px;
}
</style>