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
        <procedure-table ref="procedureTable" @updateSearchInput="updateSearchInput"/>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import ProcedureTable from "@/components/custom/table/ProcedureTable.vue";
import EntityTableActions from "@/components/custom/table/EntityTableActions.vue";
import CreateProcedureDialog from "@/components/custom/dialog/CreateProcedureDialog.vue";

export default {
  components: {CreateProcedureDialog, EntityTableActions, ProcedureTable},
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
