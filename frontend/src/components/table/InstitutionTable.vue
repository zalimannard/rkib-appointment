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
      >
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch} from 'vue';
import {requiredRule} from "@/rules";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/table/CustomTable.vue";
import {showAlert} from "@/components/alert/AlertState";
import {checkFilter} from "@/utils";
import type {InstitutionRequest, InstitutionResponse} from '@/types/institutions';

export default defineComponent({
  components: {
    CustomTable,
    MaskedTextField
  },
  props: {
    searchInput: {
      type: String,
      default: ""
    }
  },
  setup(props, {emit}) {
    const institutionRequest = ref<InstitutionRequest>({
      name: ""
    });

    const institutions = ref<InstitutionResponse[]>([]);
    const filteredInstitutions = ref<InstitutionResponse[]>([]);
    const rules = {
      requiredRule
    };

    watch(() => props.searchInput, newVal => {
      institutionRequest.value.name = newVal;
      onEditFilter();
    }, {immediate: true});

    onMounted(requestInstitution);

    function updateSearch() {
      emit("updateSearchInput", institutionRequest.value.name);
      onEditFilter();
    }

    async function requestInstitution() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/institutions", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        institutions.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
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

    return {
      institutionRequest,
      institutions,
      filteredInstitutions,
      rules,
      updateSearch,
      requestInstitution,
      onEditFilter,
      filterInstitutions,
      filterInstitution,
      resetFilters
    }
  }
});
</script>

<style scoped>

</style>
