<template>
  <procedure-table

  />
</template>

<style>
@media (min-width: 1024px) {
    .about {
        min-height: 100vh;
        display: flex;
        align-items: center;
    }
}
</style>
<script lang="ts" setup>
import ProcedureTable from "@/components/tables/ProcedureTable.vue";
</script>