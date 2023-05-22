<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="scheduleStatus.name"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Название"
              @input="updateSearch"
          />
        </th>
        <th class="text-left schedule-type-select-field" scope="col">
          <schedule-type-select
              :status="scheduleStatus.type"
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              @update:status="scheduleStatus.type = $event"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredScheduleStatuses"
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
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/table/CustomTable.vue";
import {showAlert} from "@/components/alert/AlertState";
import ScheduleTypeSelect from "@/components/select/ScheduleTypeSelect.vue";

export default {
  components: {
    ScheduleTypeSelect,
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
      scheduleStatus: this.createDefaultScheduleStatus(),
      scheduleStatuses: [],
      filteredScheduleStatuses: [],
      rules: {
        requiredRule
      }
    };
  },
  watch: {
    searchInput: {
      immediate: true,
      handler(newVal) {
        this.scheduleStatus.name = newVal;
        this.onEditFilter();
      }
    }
  },
  async created() {
    await this.requestProcedures();
  },
  methods: {
    createDefaultScheduleStatus() {
      return {
        id: "",
        name: "",
        type: []
      };
    },
    updateSearch() {
      this.$emit("updateSearchInput", this.scheduleStatus.name);
      this.onEditFilter();
    },
    async requestProcedures() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/scheduleStatuses", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        this.scheduleStatuses = response.data;
        await this.onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    },

    onEditFilter() {
      this.filteredScheduleStatuses = this.filterScheduleStatuses(this.scheduleStatuses);
    },

    filterScheduleStatuses(procedures) {
      return procedures.filter(procedure => this.filterScheduleStatus(procedure));
    },

    filterScheduleStatus(scheduleStatus) {
      const checkFilter = (fieldValue, filterValue) => {
        if (filterValue === "") return true;
        if (!fieldValue && filterValue !== "") return false;
        if (fieldValue && typeof fieldValue === "string" && filterValue && typeof filterValue === "string") {
          return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
        }
        return false;
      };

      return (
          checkFilter(scheduleStatus.name, this.scheduleStatus.name) &&
          ((this.scheduleStatus.type.length === 0) ||
              ((this.scheduleStatus.type.length === 1) && (this.scheduleStatus.type[0].value === null)) ||
              (this.scheduleStatus.type[0].value === scheduleStatus.type))
      );
    },

    resetFilters() {
      this.scheduleStatus = this.createDefaultScheduleStatus()
      this.onEditFilter();
    }
  }
};
</script>

<style scoped>
.schedule-type-select-field {
  width: 200px;
}
</style>
