<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left header-cell" scope="col">
          <masked-text-field
            v-model="procedure.name"
            capitalize-first-letter
            class="header-cell"
            density="comfortable"
            label="Название"
            @input="editFilter"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
        v-for="(item, index) in filteredProcedures"
        :key="item"
        :class="index % 2 === 0 ? 'light-row' : 'dark-row'"
      >
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script>
import { requiredRule } from "@/rules";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/custom/table/CustomTable.vue";

export default {
  components: { CustomTable, MaskedTextField },
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
  async created() {
    await this.requestProcedures();
  },
  methods: {
    async requestProcedures() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/procedures", {
          headers: { "Authorization": "Basic " + basicAuth }
        });
        this.procedures = response.data.map(procedure => {
          return {
            id: procedure.id,
            name: procedure.name
          };
        });
        await this.editFilter();
      } catch (error) {
        console.error("Ошибка при получении данных:", error);
      }
    },

    async editFilter() {
      const checkFilter = (fieldValue, filterValue) => {
        if (!fieldValue && filterValue !== "") return false;
        return !(fieldValue && fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) === -1);
      };

      this.filteredProcedures = this.procedures.filter(procedure => {
        return (
          checkFilter(procedure.name, this.procedure.name)
        );
      });
    }
  }
};
</script>
