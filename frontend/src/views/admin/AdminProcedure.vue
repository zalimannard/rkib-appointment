<template>
  <create-procedure-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :procedure-request="searchInput"
      @procedureCreated="onProcedureCreated"
      @provideSetData="onProvideSetData"
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
        <procedure-table
            ref="procedureTable"
            @provideRequestProcedure="onProvideRequestProcedure"
            @requestProcedure="onProcedureCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import ProcedureTable from "@/components/table/ProcedureTable.vue";
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateProcedureDialog from "@/components/dialog/ProcedureCreateDialog.vue";
import type {ProcedureResponse} from "@/types/procedures";

export default defineComponent({
  components: {
    CreateProcedureDialog,
    EntityTableActions,
    ProcedureTable
  },
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref({
      id: "",
      name: ""
    } as ProcedureResponse);
    const valid = ref(true);

    const updateSearchInput = (value: ProcedureResponse) => {
      searchInput.value = value;
    };

    let requestProcedure: (() => Promise<void>) | undefined;
    let setData: ((arg: ProcedureResponse) => Promise<void>) | undefined;

    const onProcedureCreated = () => {
      closeDialog()
      if (requestProcedure) {
        requestProcedure();
      }
    };

    const onProvideRequestProcedure = (func: () => Promise<void>) => {
      requestProcedure = func;
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
      onProcedureCreated,
      onProvideRequestProcedure,
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
