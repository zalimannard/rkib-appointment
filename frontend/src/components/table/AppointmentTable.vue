<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedInstitutionPerson"
              class="header-cell"
              density="comfortable"
              label="Пациент"
              @focus="openPatientDialog"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedInstitutionName"
              class="header-cell"
              density="comfortable"
              label="Учреждение"
              @focus="openInstitutionDialog"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedInstitutionStatus"
              class="header-cell"
              density="comfortable"
              label="Статус"
              @focus="openStatusDialog"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredAppointments"
          :key="item.id"
          :class="{ 'light-row': index % 2 === 0, 'dark-row': index % 2 !== 0 }"
          class="table-row"
      >
        <td>{{
            calcPersonPresentation(item.patient.person) + (item.patient.phoneNumber ? (item.patient.phoneNumber) : "")
          }}
        </td>
        <td>{{ item.institution.name }}</td>
        <td>{{ item.status.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>

  <v-dialog v-model="isPatientDialogOpened" max-width="1152px">
    <PatientTable
        :searchInput="searchInputPerson"
        @updateSearchInput="searchInputPerson = $event"
        @row-clicked="handlePersonRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isInstitutionDialogOpened" max-width="800px">
    <InstitutionTable
        :searchInput="searchInputInstitution"
        @updateSearchInput="searchInputInstitution = $event"
        @row-clicked="handleInstitutionRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isStatusDialogOpened" max-width="800px">
    <AppointmentStatusTable
        :searchInput="searchInputStatus"
        @updateSearchInput="searchInputStatus = $event"
        @row-clicked="handleAppointmentStatusRowClicked"
    />
  </v-dialog>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import type {InstitutionResponse} from "@/types/institution";
import InstitutionTable from "@/components/table/InstitutionTable.vue";
import CustomTable from "@/components/table/CustomTable.vue";
import type {AppointmentStatusResponse} from "@/types/appointmentstatus";
import type {PersonResponse} from "@/types/person";
import PersonTable from "@/components/table/PersonTable.vue";
import AppointmentStatusTable from "@/components/table/AppointmentStatusTable.vue";
import PatientTable from "@/components/table/PatientTable.vue";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";
import {onMounted} from "vue-demi";
import type {AppointmentResponse} from "@/types/appointment";
import {checkFilter} from "@/utils";

export default defineComponent({
  components: {
    PatientTable,
    AppointmentStatusTable,
    PersonTable,
    CustomTable,
    InstitutionTable,
    MaskedTextField
  },
  setup(props, {emit}) {
    const isPatientDialogOpened = ref(false);
    const isInstitutionDialogOpened = ref(false);
    const isStatusDialogOpened = ref(false);
    const selectedPerson = ref<PersonResponse | null>(null);
    const selectedInstitution = ref<InstitutionResponse | null>(null);
    const selectedStatus = ref<AppointmentStatusResponse | null>(null);

    const searchInputPerson = ref({name: ""});
    const searchInputInstitution = ref({name: ""});
    const searchInputStatus = ref({name: ""});

    const openPatientDialog = () => {
      setTimeout(() => {
        isPatientDialogOpened.value = true;
      }, 100);
    };

    const openInstitutionDialog = () => {
      setTimeout(() => {
        isInstitutionDialogOpened.value = true;
      }, 100);
    };

    const openStatusDialog = () => {
      setTimeout(() => {
        isStatusDialogOpened.value = true;
      }, 100);
    };

    const selectedInstitutionPerson = ref("Не выбрано");
    const selectedInstitutionName = ref("Не выбрано");
    const selectedInstitutionStatus = ref("Не выбрано");

    const handlePersonRowClicked = (row: PersonResponse) => {
      selectedPerson.value = row;
      isPatientDialogOpened.value = false;
      selectedInstitutionPerson.value = calcPersonPresentation(row)
          + (row.patient.phoneNumber ? (row.patient.phoneNumber) : "");
      updateSearch();
    };

    const handleInstitutionRowClicked = (row: InstitutionResponse) => {
      selectedInstitution.value = row;
      isInstitutionDialogOpened.value = false;
      selectedInstitutionName.value = row.name;
      updateSearch();
    };

    const handleAppointmentStatusRowClicked = (row: AppointmentStatusResponse) => {
      selectedStatus.value = row;
      isStatusDialogOpened.value = false;
      selectedInstitutionStatus.value = row.name;
      updateSearch();
    };

    const appointments = ref<AppointmentResponse[]>([]);
    const filteredAppointments = ref<AppointmentResponse[]>([]);

    const requestAppointment = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/appointments", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        appointments.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    }

    function onEditFilter() {
      filteredAppointments.value = filterPeople(appointments.value);
    }

    function filterPeople(appointments: AppointmentResponse[]) {
      return appointments.filter(filterPerson);
    }

    function filterPerson(appointment: AppointmentResponse) {
      console.log(appointment.patient.id + "  ;  " + (selectedPerson.value?.patient.id ?? ""))
      console.log(appointment.institution.id + "  ;  " + (selectedInstitution.value?.id ?? ""))
      console.log(appointment.status.id + "  ;  " + (selectedStatus.value?.id ?? ""))
      return (
          checkFilter(appointment.patient.id, selectedPerson.value?.patient.id ?? "") &&
          checkFilter(appointment.institution.id, selectedInstitution.value?.id ?? "") &&
          checkFilter(appointment.status.id, selectedStatus.value?.id ?? "")
      );
    }

    onMounted(() => {
      emit("provideRequestPerson", requestAppointment);
      requestAppointment();
    });

    function calcPersonPresentation(item: PersonResponse) {
      return item.lastName + " "
          + item.firstName[0] + "."
          + (item.patronymic ? (item.patronymic[0] + ". ") : " ");
    }

    function updateSearch() {
      onEditFilter();
    }

    return {
      isPatientDialogOpened,
      isInstitutionDialogOpened,
      isStatusDialogOpened,
      selectedInstitution,
      selectedInstitutionPerson,
      selectedInstitutionName,
      selectedInstitutionStatus,
      searchInputPerson,
      searchInputInstitution,
      searchInputStatus,
      filteredAppointments,
      calcPersonPresentation,
      handlePersonRowClicked,
      handleInstitutionRowClicked,
      handleAppointmentStatusRowClicked,
      openPatientDialog,
      openInstitutionDialog,
      openStatusDialog
    };
  },
});
</script>

<style scoped>

</style>