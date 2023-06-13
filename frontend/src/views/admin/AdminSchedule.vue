<template>
  <schedule-create-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onScheduleCreated"
      @scheduleCreated="onScheduleCreated"
  />

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions
            @openCreateDialog="openCreateDialog"
            @resetFilters="$refs.personAdminTable.resetFilters()"
        />
      </v-row>
      <v-row>
        <schedule-table
            ref="appointmentAdminTable"
            @provideRequestSchedule="onProvideRequestSchedule"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import AppointmentTable from "@/components/table/AppointmentTable.vue";
import {defineComponent, ref} from "vue";
import PersonTable from "@/components/table/PersonTable.vue";
import ScheduleTable from "@/components/table/ScheduleTable.vue";
import ScheduleCreateDialog from "@/components/dialog/ScheduleCreateDialog.vue";

export default defineComponent({
  components: {
    ScheduleCreateDialog,
    ScheduleTable,
    PersonTable,
    AppointmentTable,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);

    let requestSchedule: (() => Promise<void>) | undefined;

    const onScheduleCreated = () => {
      closeDialog();
      if (requestSchedule) {
        requestSchedule();
      }
    };

    const onProvideRequestSchedule = (func: () => Promise<void>) => {
      requestSchedule = func;
    };

    const handleRowClick = (item: any) => {

    };

    const openCreateDialog = () => {
      showCreateDialog.value = true;
    };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      handleRowClick,
      openCreateDialog,
      closeDialog,
      onScheduleCreated,
      onProvideRequestSchedule
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