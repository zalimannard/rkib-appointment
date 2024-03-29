<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left fixed-col" scope="col">
          <masked-text-field
              v-model="institutionRequest.name"
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
          v-for="(item, index) in filteredInstitutions"
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
import type {InstitutionRequest, InstitutionResponse} from 'src/types/institution';
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
    const institutionRequest = ref<InstitutionRequest>({
      name: props.searchInput.name
    });

    const institutions = ref<InstitutionResponse[]>([]);
    const filteredInstitutions = ref<InstitutionResponse[]>([]);

    watch(() => props.searchInput, (newVal) => {
      institutionRequest.value.name = newVal.name;
      onEditFilter();
    }, {immediate: true});

    const requestInstitution = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/institutions", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        institutions.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные");
      }
    }

    onMounted(() => {
      emit("provideRequestInstitution", requestInstitution);
      requestInstitution();
    });

    function updateSearch() {
      emit("updateSearchInput", institutionRequest.value);
      onEditFilter();
    }

    function onEditFilter() {
      filteredInstitutions.value = filterInstitutions(institutions.value);
    }

    function filterInstitutions(institutions: InstitutionResponse[]) {
      return institutions.filter(filterInstitution);
    }

    function filterInstitution(institution: InstitutionResponse) {
      return (
          checkFilter(institution.name, institutionRequest.value.name)
      );
    }

    function resetFilters() {
      institutionRequest.value = {
        name: ""
      };
      onEditFilter();
    }

    const handleRowClick = (item: InstitutionResponse) => {
      emit("row-clicked", item);
    };

    provide("requestInstitution", requestInstitution);

    return {
      institutionRequest,
      institutions,
      filteredInstitutions,
      handleRowClick,
      updateSearch,
      requestInstitution,
      onEditFilter,
      resetFilters
    };
  },
});
</script>

<style scoped>

</style>
