<template>
  <create-institution-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :institution-request="searchInput"
      @institutionCreated="onInstitutionCreated"
      @provideSetData="onProvideSetData"
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
        <institution-table
            ref="institutionTable"
            @provideRequestInstitution="onProvideRequestInstitution"
            @requestInstitution="onInstitutionCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import InstitutionTable from "@/components/table/InstitutionTable.vue";
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateInstitutionDialog from "@/components/dialog/InstitutionCreateDialog.vue";
import type {InstitutionResponse} from "@/types/institutions";

export default defineComponent({
  components: {
    CreateInstitutionDialog,
    EntityTableActions,
    InstitutionTable
  },
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref({
      id: "",
      name: ""
    } as InstitutionResponse);
    const valid = ref(true);

    const updateSearchInput = (value: InstitutionResponse) => {
      searchInput.value = value;
    };

    let requestInstitution: (() => Promise<void>) | undefined;
    let setData: ((arg: InstitutionResponse) => Promise<void>) | undefined;

    const onInstitutionCreated = () => {
      closeDialog()
      if (requestInstitution) {
        requestInstitution();
      }
    };

    const onProvideRequestInstitution = (func: () => Promise<void>) => {
      requestInstitution = func;
    };

    const onProvideSetData = (func: () => Promise<void>) => {
      setData = func;
    };

    const openCreateDialog = () => {
      if (setData) {
        setData(searchInput.value);
      }
      showCreateDialog.value = true;
    };

    const closeDialog = () => {
      showCreateDialog.value = false;
    };

    return {
      showCreateDialog,
      searchInput,
      valid,
      updateSearchInput,
      openCreateDialog,
      closeDialog,
      onInstitutionCreated,
      onProvideRequestInstitution,
      onProvideSetData
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
