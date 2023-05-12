<script>
import { requiredRule } from "@/rules";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import axios from "axios";

export default {
  components: { MaskedTextField },
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
    }
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
            name: procedure.name,
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
    },
  }
}
</script>

<template>
  <v-container class="main-container" fluid>
    <v-table class="sticky-header table-container" fixed-footer>
      <thead>
      <tr>
        <th class="text-left table-column" scope="col">
          <masked-text-field
            v-model="procedure.name"
            capitalize-first-letter
            class="table-header"
            label="Название"
            @input="editFilter"
          />
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="(item, index) in filteredProcedures"
        :key="item"
        :class="index % 2 === 0 ? 'light-row' : 'dark-row'"
      >
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </v-table>
  </v-container>
</template>

<style scoped>
.main-container {
    width: 1152px;
    margin: 5vh auto 0;
}

.table-container {
    border: 1px solid #ccc;
    border-radius: 5pt;
}
.table-header {
    margin-top: 2vh;
}
.light-row {
    background-color: #FFFFFF;
}
.dark-row {
    background-color: #ECEFF1;
}
</style>