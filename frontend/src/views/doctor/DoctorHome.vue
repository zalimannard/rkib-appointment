<template>
  <schedule-create-doctor-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onPersonCreated"
      :search-person="searchInput"
      @personCreated="onPersonCreated"
      @provideSetData="onProvideSetData"
      @updateSearchInput="updateSearchInput"
  />

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions
            @openCreateDialog="openCreateDialog"
        />
      </v-row>
      <v-row>
        <schedule-doctor-table
            ref="personAdminTable"
            @provideRequestPerson="onProvideRequestPerson"
            @requestPerson="onPersonCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import PersonTable from "@/components/table/PersonTable.vue";
import CreatePersonDialog from "@/components/dialog/PersonCreateDialog.vue";
import type {PersonResponse} from "@/types/person";
import type {PatientResponse} from "@/types/patient";
import PatientTable from "@/components/table/PatientTable.vue";
import PatientCreateDialog from "@/components/dialog/PatientCreateDialog.vue";
import ScheduleDoctorTable from "@/components/table/ScheduleDoctorTable.vue";
import ScheduleCreateDoctorDialog from "@/components/dialog/ScheduleCreateDoctorDialog.vue";

export default defineComponent({
  components: {
    ScheduleCreateDoctorDialog,
    ScheduleDoctorTable,
    PatientCreateDialog,
    PatientTable,
    CreatePersonDialog,
    PersonTable,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref({
      id: "",
      lastName: "",
      firstName: "",
      patronymic: "",
      email: "",
      patient: {
        id: "",
        birthdate: "",
        phoneNumber: "",
        address: "",
        occupation: ""
      } as PatientResponse
    } as PersonResponse);
    const valid = ref(true);

    const updateSearchInput = (value: PersonResponse) => {
      searchInput.value = value;
    };

    let requestPerson: (() => Promise<void>) | undefined;
    let setData: ((arg: PersonResponse) => Promise<void>) | undefined;

    const onPersonCreated = () => {
      closeDialog();
      if (requestPerson) {
        requestPerson();
      }
    };

    const onProvideRequestPerson = (func: () => Promise<void>) => {
      requestPerson = func;
    };

    const onProvideSetData = (func: () => Promise<void>) => {
      setData = func;
    };

    const handleRowClick = (item: any) => {

    };

    const openCreateDialog = () => {
      if (setData) {
        setData(searchInput.value)
      }
      showCreateDialog.value = true;
    };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      searchInput,
      valid,
      updateSearchInput,
      handleRowClick,
      openCreateDialog,
      closeDialog,
      onPersonCreated,
      onProvideRequestPerson,
      onProvideSetData
    };
  }
});
</script>

<style scoped>
.container {
  display: grid;
  margin: 20pt auto 0;
}
</style>
