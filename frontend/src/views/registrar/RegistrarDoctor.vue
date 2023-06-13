<template>
  <set-schedule-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onPersonCreated"
      :search-person="searchInput"
      @provideSetData="onProvideSetData"
      @scheduleCreated="onPersonCreated"
  />

  <schedule-create-dialog
      v-model="showCreateScheduleDialog"
      :close-dialog="closeScheduleDialog"
      :on-create-entity="onScheduleCreated"
      @scheduleCreated="onScheduleCreated"
  />

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions
            @openCreateDialog="openCreateScheduleDialog"
        />
      </v-row>
      <v-row>
        <schedule-registrar-table
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
import ScheduleRegistrarTable from "@/components/table/ScheduleRegistrarTable.vue";
import SetScheduleDialog from "@/components/dialog/SetScheduleDialog.vue";
import type {ScheduleResponse} from "@/types/schedule";
import ScheduleCreateDialog from "@/components/dialog/ScheduleCreateDialog.vue";

export default defineComponent({
  components: {
    ScheduleCreateDialog,
    SetScheduleDialog,
    ScheduleRegistrarTable,
    CreatePersonDialog,
    PersonTable,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);
    const showCreateScheduleDialog = ref(false);
    const searchInput = ref({id: ""});
    const valid = ref(true);

    let requestPerson: (() => Promise<void>) | undefined;
    let setData: ((arg: string) => Promise<void>) | undefined;

    const onPersonCreated = () => {
      closeDialog();
      if (requestPerson) {
        requestPerson();
      }
    };

    const onScheduleCreated = () => {
      closeScheduleDialog();
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

    const openCreateScheduleDialog = () => {
      showCreateScheduleDialog.value = true;
    };

    const handlePatientRowClicked = (item: ScheduleResponse) => {
      if (setData) {
        setData(item.id)
      }
      showCreateDialog.value = true;
    };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    const closeScheduleDialog = () => {
      showCreateScheduleDialog.value = false;
    };

    return {
      showCreateDialog,
      showCreateScheduleDialog,
      searchInput,
      valid,
      handlePatientRowClicked,
      closeDialog,
      closeScheduleDialog,
      onPersonCreated,
      onScheduleCreated,
      onProvideRequestPerson,
      openCreateScheduleDialog,
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