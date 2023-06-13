<template>
  <v-container class="container">
    <v-col>
      <v-row>
        <schedule-patient-own-table
            ref="personAdminTable"
            @provideRequestPerson="onProvideRequestPerson"
            @requestPerson="onPersonCreated"
            @row-clicked="handlePatientRowClicked"
        />
      </v-row>
    </v-col>
  </v-container>
</template>

<script lang="ts" setup>
import SchedulePatientOwnTable from "@/components/table/SchedulePatientOwnTable.vue";
</script>

<style scoped>
.container {
  display: grid;
  margin: 20pt auto 0;
}
</style>