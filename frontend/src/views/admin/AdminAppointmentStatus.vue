<template>
  <create-appointment-status-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :institution-request="searchInput"
      @institutionCreated="onAppointmentStatusCreated"
      @provideSetData="onProvideSetData"
      @updateSearchInput="updateSearchInput"
  />

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions
            @openCreateDialog="openCreateDialog"
            @resetFilters="$refs.procedureTable.resetFilters()"
        />
      </v-row>
      <v-row>
        <appointment-status-table
            ref="institutionTable"
            @provideRequestAppointmentStatus="onProvideRequestAppointmentStatus"
            @requestAppointmentStatus="onAppointmentStatusCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateAppointmentStatusDialog from "@/components/dialog/AppointmentStatusCreateDialog.vue";
import AppointmentStatusTable from "@/components/table/AppointmentStatusTable.vue";
import type {AppointmentStatusResponse} from "@/types/appointmentstatus";

export default defineComponent({
  components: {
    AppointmentStatusTable,
    CreateAppointmentStatusDialog,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref({
      id: "",
      name: "",
      type: "",
    } as AppointmentStatusResponse);
    const valid = ref(true);

    const updateSearchInput = (value: AppointmentStatusResponse) => {
      searchInput.value = value;
    };

    let requestAppointmentStatus: (() => Promise<void>) | undefined;
    let setData: ((arg: AppointmentStatusResponse) => Promise<void>) | undefined;

    const onAppointmentStatusCreated = () => {
      closeDialog()
      if (requestAppointmentStatus) {
        requestAppointmentStatus();
      }
    };

    const onProvideRequestAppointmentStatus = (func: () => Promise<void>) => {
      requestAppointmentStatus = func;
    };

    const onProvideSetData = (func: () => Promise<void>) => {
      setData = func;
    };

    const openCreateDialog = () => {
      if (setData) {
        setData(searchInput.value);
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
      openCreateDialog,
      closeDialog,
      onAppointmentStatusCreated,
      onProvideRequestAppointmentStatus,
      onProvideSetData
    };
  }
});
</script>

<style scoped>
.container {
  display: grid;
  width: 1152px;
  margin: 20pt auto 0;
}
</style>
