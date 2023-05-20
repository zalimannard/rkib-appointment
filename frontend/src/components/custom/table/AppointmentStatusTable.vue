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
              :status="appointmentStatus.type"
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              @update:status="appointmentStatus.type = $event"
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
        <td>{{ item.type }}</td>
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
      appointmentStatus: this.createDefaultAppointmentStatus(),
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
    createDefaultAppointmentStatus() {
      return {
        id: "",
        name: "",
        type: []
      };
    },
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
      this.filteredAppointmentStatuses = this.filterAppointmentStatuses(this.appointmentStatuses);
    },

    filterAppointmentStatuses(procedures) {
      return procedures.filter(procedure => this.filterAppointmentStatus(procedure));
    },

    filterAppointmentStatus(appointmentStatus) {
      const checkFilter = (fieldValue, filterValue) => {
        if (filterValue === "") return true;
        if (!fieldValue && filterValue !== "") return false;
        if (fieldValue && typeof fieldValue === "string" && filterValue && typeof filterValue === "string") {
          return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
        }
        return false;
      };

      return (
          checkFilter(appointmentStatus.name, this.appointmentStatus.name) &&
          ((this.appointmentStatus.type.length === 0) ||
              ((this.appointmentStatus.type.length === 1) && (this.appointmentStatus.type[0].value === null)) ||
              (this.appointmentStatus.type[0].value === appointmentStatus.type))
      );
    },

    resetFilters() {
      this.appointmentStatus = this.createDefaultAppointmentStatus()
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
