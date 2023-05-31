<template>
  <create-person-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :on-create-entity="onCreateEntity"
      :search-person="searchInput"
      @updateSearchInput="updateSearchInput"
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
        <person-table
            ref="personAdminTable"
            @rowClick="handleRowClick"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import PersonTable from "@/components/table/PersonTable.vue";
import CreatePersonDialog from "@/components/dialog/PersonCreateDialog.vue";

export default {
  components: {CreatePersonDialog, PersonTable, EntityTableActions},
  data() {
    return {
      showCreateDialog: false,
      searchInput: "",
      valid: true
    };
  },
  methods: {
    handleRowClick(item) {
      console.log(item);
    },
    updateSearchInput(value) {
      this.searchInput = value;
    },
    onCreateEntity() {
      this.showCreateDialog = false;
      this.$refs.personAdminTable.requestPeople();
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
  margin: 20pt auto 0;
}
</style>
