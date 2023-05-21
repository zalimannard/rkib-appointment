<template>
  <create-institution-dialog
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
            @resetFilters="$refs.institutionTable.resetFilters()"
        />
      </v-row>
      <v-row>
        <institution-table ref="institutionTable" @updateSearchInput="updateSearchInput"/>
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import InstitutionTable from "@/components/table/InstitutionTable.vue";
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateInstitutionDialog from "@/components/dialog/InstitutionCreateDialog.vue";

export default defineComponent({
  name: 'AdminInstitution',
  components: {CreateInstitutionDialog, EntityTableActions, InstitutionTable},
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref('');
    const valid = ref(true);
    const institutionTableRef = ref<typeof InstitutionTable | null>(null);

    const updateSearchInput = (value: string) => {
      searchInput.value = value;
    };

    const onCreateEntity = () => {
      showCreateDialog.value = false;
      if (institutionTableRef.value) {
        institutionTableRef.value.requestInstitution();
      }
    };

    const openCreateDialog = () => {
      showCreateDialog.value = true;
    };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      searchInput,
      valid,
      institutionTableRef,
      updateSearchInput,
      onCreateEntity,
      openCreateDialog,
      closeDialog
    };
  },
});
</script>

<style scoped>
.container {
  display: grid;
  width: 1152px;
  margin: 20pt auto 0;
}
</style>
