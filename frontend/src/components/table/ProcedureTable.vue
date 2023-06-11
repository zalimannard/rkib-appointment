<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left fixed-col" scope="col">
          <masked-text-field
              v-model="procedureRequest.name"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Название"
              @input="updateSearch"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredProcedures"
          :key="item.id"
          :class="{ 'light-row': index % 2 === 0, 'dark-row': index % 2 !== 0 }"
          class="table-row"
          @click="handleRowClick(item)"
      >
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script lang="ts">
import {defineComponent, ref, watch} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/table/CustomTable.vue";
import {showAlert} from "@/components/alert/AlertState";
import {checkFilter} from "@/utils";
import type {ProcedureRequest, ProcedureResponse} from 'src/types/procedure';
import {onMounted, provide} from "vue-demi";

export default defineComponent({
  components: {
    CustomTable,
    MaskedTextField
  },
  props: {
    searchInput: {
      type: Object,
      default: () => ({
        name: ""
      })
    }
  },
  setup(props, {emit}) {
    const procedureRequest = ref<ProcedureRequest>({
      name: props.searchInput.name
    });

    const procedures = ref<ProcedureResponse[]>([]);
    const filteredProcedures = ref<ProcedureResponse[]>([]);

    watch(() => props.searchInput, (newVal) => {
      procedureRequest.value.name = newVal.name;
      onEditFilter();
    }, {immediate: true});

    const requestProcedure = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/procedures", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        procedures.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные");
      }
    }

    onMounted(() => {
      emit("provideRequestProcedure", requestProcedure);
      requestProcedure();
    });

    function updateSearch() {
      emit("updateSearchInput", procedureRequest.value);
      onEditFilter();
    }

    function onEditFilter() {
      filteredProcedures.value = filterProcedures(procedures.value);
    }

    function filterProcedures(procedures: ProcedureResponse[]) {
      return procedures.filter(filterProcedure);
    }

    function filterProcedure(procedure: ProcedureResponse) {
      return (
          checkFilter(procedure.name, procedureRequest.value.name)
      );
    }

    function resetFilters() {
      procedureRequest.value = {
        name: ""
      };
      onEditFilter();
    }

    provide("requestProcedure", requestProcedure);

    const handleRowClick = (item: ProcedureResponse) => {
      emit("row-clicked", item);
    };

    return {
      procedureRequest,
      procedures,
      filteredProcedures,
      updateSearch,
      requestProcedure,
      onEditFilter,
      resetFilters,
      handleRowClick
    };
  },
});
</script>

<style scoped>

</style>
