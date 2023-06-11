<template>
  <create-appointment-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onAppointmentCreated"
      @appointmentCreated="onAppointmentCreated"
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
        <appointment-table
            ref="appointmentAdminTable"
            @provideRequestAppointment="onProvideRequestAppointment"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import AppointmentTable from "@/components/table/AppointmentTable.vue";
import {defineComponent, ref} from "vue";
import CreateAppointmentDialog from "@/components/dialog/AppointmentCreateDialog.vue";
import CreatePersonDialog from "@/components/dialog/PersonCreateDialog.vue";
import PersonTable from "@/components/table/PersonTable.vue";

export default defineComponent({
  components: {
    PersonTable,
    CreatePersonDialog,
    AppointmentTable,
    CreateAppointmentDialog,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);

    let requestPerson: (() => Promise<void>) | undefined;

    const onAppointmentCreated = () => {
      closeDialog();
      if (requestPerson) {
        requestPerson();
      }
    };

    const onProvideRequestAppointment = (func: () => Promise<void>) => {
      requestPerson = func;
    };

    const handleRowClick = (item: any) => {
      console.log(item);
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
      onAppointmentCreated,
      onProvideRequestAppointment
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