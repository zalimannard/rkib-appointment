<template>
  <create-procedure-dialog
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
        <person-admin-table
          ref="personAdminTable"
          @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import ProcedureTable from "@/components/tables/ProcedureTable.vue";
import EntityTableActions from "@/components/tables/EntityTableActions.vue";
import CreateProcedureDialog from "@/components/custom/dialog/CreateProcedureDialog.vue";
import PersonAdminTable from "@/components/tables/PersonAdminTable.vue";

export default {
  components: { PersonAdminTable, CreateProcedureDialog, EntityTableActions, ProcedureTable },
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

<style>
.container {
    display: grid;
    width: 1152px;
    margin: 20pt auto 0;
}
</style>
