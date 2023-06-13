<template>
  <schedule-create-doctor-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onPersonCreated"
      :search-person="searchInput"
      @personCreated="onPersonCreated"
      @provideSetData="onProvideSetData"
  />

  <set-schedule-doctor-dialog
      v-model="showCreateScheduleDialog"
      :close-dialog="closeScheduleDialog"
      :on-create-entity="onPersonCreated"
      :search-person="searchInput"
      @provideSetData="onProvideSetData"
      @scheduleCreated="onPersonCreated"
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
            @row-clicked="handlePatientRowClicked"
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
import SetScheduleDialog from "@/components/dialog/SetScheduleDialog.vue";
import SetScheduleDoctorDialog from "@/components/dialog/SetScheduleDoctorDialog.vue";
import ScheduleRegistrarTable from "@/components/table/ScheduleRegistrarTable.vue";
import type {ScheduleResponse} from "@/types/schedule";

export default defineComponent({
  components: {
    ScheduleRegistrarTable,
    SetScheduleDoctorDialog,
    SetScheduleDialog,
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
    const showCreateScheduleDialog = ref(false);
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
    let setData: ((arg: string) => Promise<void>) | undefined;

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
      showCreateDialog.value = true;
    };

    const handlePatientRowClicked = (item: ScheduleResponse) => {
      if (setData) {
        setData(item.id)
      }
      showCreateScheduleDialog.value = true;
    };

    const closeDialog = () => {
      showCreateScheduleDialog.value = false;
    };

    const closeScheduleDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      showCreateScheduleDialog,
      searchInput,
      valid,
      handlePatientRowClicked,
      updateSearchInput,
      handleRowClick,
      openCreateDialog,
      closeDialog,
      closeScheduleDialog,
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
