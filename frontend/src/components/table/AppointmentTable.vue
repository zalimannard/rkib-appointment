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

export default defineComponent({
  components: {
    PatientTable,
    AppointmentStatusTable,
    PersonTable,
    CustomTable,
    InstitutionTable,
    MaskedTextField
  },
  setup() {
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
      selectedInstitutionPerson.value = row.lastName;
    };

    const handleInstitutionRowClicked = (row: InstitutionResponse) => {
      selectedInstitution.value = row;
      isInstitutionDialogOpened.value = false;
      selectedInstitutionName.value = row.name;
    };

    const handleAppointmentStatusRowClicked = (row: AppointmentStatusResponse) => {
      selectedStatus.value = row;
      isStatusDialogOpened.value = false;
      selectedInstitutionStatus.value = row.name;
    };

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