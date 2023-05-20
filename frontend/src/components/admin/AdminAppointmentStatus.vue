<template>
  <create-appointment-status-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onCreateEntity"
      :search-input="searchInput"
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
        <appointment-status-table ref="procedureTable" @updateSearchInput="updateSearchInput"/>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import InstitutionTable from "@/components/custom/table/InstitutionTable.vue";
import EntityTableActions from "@/components/custom/table/EntityTableActions.vue";
import CreateAppointmentStatusDialog from "@/components/custom/dialog/CreateAppointmentStatusDialog.vue";
import AppointmentStatusTable from "@/components/custom/table/AppointmentStatusTable.vue";

export default {
  components: {AppointmentStatusTable, CreateAppointmentStatusDialog, EntityTableActions, InstitutionTable},
  data() {
    return {
      showCreateDialog: false,
      searchInput: "",
      valid: true
    };
  },
  methods: {
    updateSearchInput(value) {
      this.searchInput = value;
    },
    onCreateEntity() {
      this.showCreateDialog = false;
      this.$refs.procedureTable.requestProcedures();
    },
    openCreateDialog() {
      this.showCreateDialog = true;
    },
    closeDialog() {
      this.showCreateDialog = false;
    }
  }
};
</script>

<style scoped>
.container {
  display: grid;
  width: 1152px;
  margin: 20pt auto 0;
}
</style>
