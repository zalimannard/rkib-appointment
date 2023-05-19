<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left fixed-col" scope="col">
          <masked-text-field
              v-model="procedure.name"
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
      >
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script>
import {requiredRule} from "@/rules";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/custom/table/CustomTable.vue";

export default {
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
  data() {
    return {
      procedure: {
        id: "",
        name: ""
      },
      procedures: [],
      filteredProcedures: [],
      rules: {
        requiredRule
      }
    };
  },
  watch: {
    searchInput: {
      immediate: true,
      handler(newVal) {
        this.procedure.name = newVal;
        this.onEditFilter();
      }
    }
  },
  async created() {
    await this.requestProcedures();
  },
  methods: {
    updateSearch() {
      this.$emit("updateSearchInput", this.procedure.name);
      this.onEditFilter();
    },
    async requestProcedures() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/procedures", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        this.procedures = response.data;
        await this.onEditFilter();
      } catch (error) {

        console.error("Ошибка при получении данных:", error);
      }
    },

    onEditFilter() {
      this.filteredProcedures = this.filterProcedures(this.procedures);
    },

    filterProcedures(procedures) {
      return procedures.filter(procedure => this.filterProcedure(procedure));
    },

    filterProcedure(procedure) {
      const checkFilter = (fieldValue, filterValue) => {
        if (filterValue === "") return true;
        if (!fieldValue && filterValue !== "") return false;
        if (fieldValue && typeof fieldValue === "string" && filterValue && typeof filterValue === "string") {
          return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
        }
        return false;
      };

      return (
          checkFilter(procedure.name, this.procedure.name)
      );
    },

    resetFilters() {
      this.procedure = {
        id: "",
        name: ""
      };
      this.onEditFilter();
    }
  }
};
</script>

<style scoped>

</style>
