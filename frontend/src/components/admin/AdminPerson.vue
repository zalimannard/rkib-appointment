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
        <person-admin-table
          ref="personAdminTable"
          @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import EntityTableActions from "@/components/tables/EntityTableActions.vue";
import PersonAdminTable from "@/components/tables/PersonAdminTable.vue";
import CreatePersonDialog from "@/components/custom/dialog/CreatePersonDialog.vue";

export default {
  components: { CreatePersonDialog, PersonAdminTable, EntityTableActions },
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

<style>
.container {
    display: grid;
    width: 1152px;
    margin: 20pt auto 0;
}
</style>
