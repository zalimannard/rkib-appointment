<script>
import ProcedureTable from "@/components/tables/ProcedureTable.vue";
import EntityTableActions from "@/components/tables/EntityTableActions.vue";
import CreateProcedureDialog from "@/components/custom/dialog/CreateProcedureDialog.vue";

export default {
  components: { CreateProcedureDialog, EntityTableActions, ProcedureTable },
  data() {
    return {
      valid: true
    };
  }
};
</script>

<template>
  <create-procedure-dialog/>

  <v-container class="container">
    <v-col>
      <v-row>
        <entity-table-actions />
      </v-row>
      <v-row>
        <procedure-table />
      </v-row>
    </v-col>
  </v-container>
</template>

<style>
.container {
    display: grid;
    width: 1152px;
    margin: 20pt auto 0;
}
</style>
