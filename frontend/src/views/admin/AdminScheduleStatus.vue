<template>
  <create-schedule-status-dialog
      v-model="showCreateDialog"
      :close-dialog="closeDialog"
      :schedule-status-request="searchInput"
      @provideSetData="onProvideSetData"
      @scheduleStatusCreated="onScheduleStatusCreated"
      @updateSearchInput="updateSearchInput"
  />

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions
            @openCreateDialog="openCreateDialog"
            @resetFilters="$refs.scheduleStatusTable.resetFilters()"
        />
      </v-row>
      <v-row>
        <schedule-status-table
            ref="scheduleStatusTable"
            @provideRequestScheduleStatus="onProvideRequestScheduleStatus"
            @requestScheduleStatus="onScheduleStatusCreated"
            @updateSearchInput="updateSearchInput"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import EntityTableActions from "@/components/table/EntityTableActions.vue";
import CreateScheduleStatusDialog from "@/components/dialog/ScheduleStatusCreateDialog.vue";
import ScheduleStatusTable from "@/components/table/ScheduleStatusTable.vue";
import type {ScheduleStatusResponse} from "@/types/schedulestatus";

export default defineComponent({
  components: {
    ScheduleStatusTable,
    CreateScheduleStatusDialog,
    EntityTableActions
  },
  setup() {
    const showCreateDialog = ref(false);
    const searchInput = ref({
      id: "",
      name: "",
      type: "",
    } as ScheduleStatusResponse);
    const valid = ref(true);

    const updateSearchInput = (value: ScheduleStatusResponse) => {
      searchInput.value = value;
    };

    let requestScheduleStatus: (() => Promise<void>) | undefined;
    let setData: ((arg: ScheduleStatusResponse) => Promise<void>) | undefined;

    const onScheduleStatusCreated = () => {
      closeDialog()
      if (requestScheduleStatus) {
        requestScheduleStatus();
      }
    };

    const onProvideRequestScheduleStatus = (func: () => Promise<void>) => {
      requestScheduleStatus = func;
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
      onScheduleStatusCreated,
      onProvideRequestScheduleStatus,
      onProvideSetData
    };
  }
});
</script>

<style scoped>
.container {
  display: grid;
  width: 1152px;
  margin: 20pt auto 0;
}
</style>
