<template>
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
            ref="personAdminTable"
            @provideRequestPerson="onProvideRequestAppointment"
            @requestPerson="onAppointmentCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import AppointmentTable from "@/components/table/AppointmentTable.vue";
import {defineComponent, ref} from "vue";
import CreateAppointmentDialog from "@/components/dialog/CreateAppointmentDialog.vue";

export default defineComponent({
  components: {
    AppointmentTable,
    CreateAppointmentDialog,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);
    // const searchInput = ref({
    //   id: "",
    //   lastName: "",
    //   firstName: "",
    //   patronymic: "",
    //   email: "",
    //   patient: {
    //     id: "",
    //     birthdate: "",
    //     phoneNumber: "",
    //     address: "",
    //     occupation: ""
    //   } as PatientResponse,
    //   employee: {
    //     id: "",
    //     roles: []
    //   } as EmployeeResponse
    // } as PersonResponse);
    const valid = ref(true);

    // const updateSearchInput = (value: PersonResponse) => {
    //   searchInput.value = value;
    // };

    let requestPerson: (() => Promise<void>) | undefined;
    // let setData: ((arg: PersonResponse) => Promise<void>) | undefined;

    const onPersonCreated = () => {
      console.log("onPersonCreated")
      closeDialog();
      if (requestPerson) {
        requestPerson();
      }
    };

    const onProvideRequestPerson = (func: () => Promise<void>) => {
      requestPerson = func;
    };

    // const onProvideSetData = (func: () => Promise<void>) => {
    //   setData = func;
    // };

    const handleRowClick = (item: any) => {
      console.log(item);
    };

    // const openCreateDialog = () => {
    //   if (setData) {
    //     setData(searchInput.value)
    //   }
    //   showCreateDialog.value = true;
    // };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      // searchInput,
      valid,
      // updateSearchInput,
      handleRowClick,
      // openCreateDialog,
      closeDialog,
      onPersonCreated,
      onProvideRequestPerson,
      // onProvideSetData
    };
  }
});

</script>

<style>

.container {
  display: grid;
  width: 1152px;
  margin: 20pt auto 0;
}
</style>