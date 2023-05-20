<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="appointmentStatus.name"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Название"
              @input="updateSearch"
          />
        </th>
        <th class="text-left appointment-type-select-field" scope="col">
          <appointment-type-select
              :update-search-input="updateSearch"
              roles=""
              class="header-cell"
              density="comfortable"
              include-none
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredAppointmentStatuses"
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
import {showAlert} from "@/components/custom/alert/AlertState";
import AppointmentTypeSelect from "@/components/custom/select/AppointmentTypeSelect.vue";

export default {
  components: {
    AppointmentTypeSelect,
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
      appointmentStatus: {
        id: "",
        name: "",
        type: []
      },
      appointmentStatuses: [],
      filteredAppointmentStatuses: [],
      rules: {
        requiredRule
      }
    };
  },
  watch: {
    searchInput: {
      immediate: true,
      handler(newVal) {
        this.appointmentStatus.name = newVal;
        this.onEditFilter();
      }
    }
  },
  async created() {
    await this.requestProcedures();
  },
  methods: {
    updateSearch() {
      this.$emit("updateSearchInput", this.appointmentStatus.name);
      this.onEditFilter();
    },
    async requestProcedures() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/appointmentStatuses", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        this.appointmentStatuses = response.data;
        await this.onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    },

    onEditFilter() {
      this.filteredAppointmentStatuses = this.filterProcedures(this.appointmentStatuses);
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
          checkFilter(procedure.name, this.appointmentStatus.name)
      );
    },

    resetFilters() {
      this.appointmentStatus = {
        id: "",
        name: ""
      };
      this.onEditFilter();
    }
  }
};
</script>

<style scoped>
.appointment-type-select-field {
  width: 200px;
}
</style>
