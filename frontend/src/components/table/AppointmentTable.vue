<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              class="header-cell"
              density="comfortable"
              label="Пациент"
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
              class="header-cell"
              density="comfortable"
              label="Статус"
          />
        </th>
      </tr>
      </thead>
    </template>
  </custom-table>

  <v-dialog v-model="openDialog" max-width="800px">
    <InstitutionTable
        :searchInput="searchInput"
        @updateSearchInput="searchInput = $event"
        @row-clicked="handleRowClicked"
    />
  </v-dialog>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import type {InstitutionResponse} from "@/types/institution";
import InstitutionTable from "@/components/table/InstitutionTable.vue";
import CustomTable from "@/components/table/CustomTable.vue";
import type {PatientResponse} from "@/types/patient";
import type {AppointmentStatusResponse} from "@/types/appointmentstatus";

export default defineComponent({
  components: {
    CustomTable,
    InstitutionTable,
    MaskedTextField
  },
  setup() {
    const openDialog = ref(false);
    const selectedPatient = ref<PatientResponse | null>(null);
    const selectedInstitution = ref<InstitutionResponse | null>(null);
    const selectedStatus = ref<AppointmentStatusResponse | null>(null);
    const searchInput = ref({name: ""});

    const openInstitutionDialog = () => {
      setTimeout(() => {
        openDialog.value = true;
      }, 100);
    };

    const selectedInstitutionName = ref("Не выбрано");

    const handleRowClicked = (row: InstitutionResponse) => {
      selectedInstitution.value = row;
      openDialog.value = false;
      selectedInstitutionName.value = row.name;
    };

    return {
      openDialog,
      selectedInstitution,
      selectedInstitutionName,
      searchInput,
      handleRowClicked,
      openInstitutionDialog
    };
  },
});
</script>

<style scoped>
.v-dialog {
  position: fixed !important;
  top: 0;
  z-index: 9999;
}
</style>