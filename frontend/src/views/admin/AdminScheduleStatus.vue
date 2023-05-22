<template>
  <create-schedule-status-dialog
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
        <schedule-status-table ref="procedureTable" @updateSearchInput="updateSearchInput"/>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateScheduleStatusDialog from "@/components/dialog/CreateScheduleStatusDialog.vue";
import ScheduleStatusTable from "@/components/table/ScheduleStatusTable.vue";

export default {
  components: {
    ScheduleStatusTable,
    CreateScheduleStatusDialog,
    EntityTableActions
  },
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
