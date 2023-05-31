<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="scheduleStatusRequest.name"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Название"
              @input="updateSearch"
          />
        </th>
        <th class="text-left schedule-type-select-field" scope="col">
          <schedule-type-select
              :status="scheduleStatusRequest.type"
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              @update:status="scheduleStatusRequest.type = $event"
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

<script lang="ts">
import {defineComponent, ref, watch} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/table/CustomTable.vue";
import {showAlert} from "@/components/alert/AlertState";
import {checkFilter} from "@/utils";
import type {ScheduleStatusRequest, ScheduleStatusResponse} from "@/types/schedulestatus";
import {onMounted, provide} from "vue-demi";
import ScheduleTypeSelect from "@/components/select/ScheduleTypeSelect.vue";

export default defineComponent({
  components: {
    ScheduleTypeSelect,
    CustomTable,
    MaskedTextField
  },
  props: {
    searchInput: {
      type: Object,
      default: () => ({
        name: "",
        type: ""
      })
    }
  },
  setup(props, {emit}) {
    const scheduleStatusRequest = ref<ScheduleStatusRequest>({
      name: "",
      type: ""
    });

    const scheduleStatuses = ref<ScheduleStatusResponse[]>([]);
    const filteredScheduleStatuses = ref<ScheduleStatusResponse[]>([]);

    watch(() => props.searchInput, (newVal) => {
      scheduleStatusRequest.value.name = newVal.name;
      scheduleStatusRequest.value.type = newVal.type;
    }, {immediate: true});

    const requestScheduleStatus = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/scheduleStatuses", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        scheduleStatuses.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    }

    onMounted(() => {
      emit("provideRequestScheduleStatus", requestScheduleStatus);
      requestScheduleStatus();
    });

    function updateSearch() {
      emit("updateSearchInput", scheduleStatusRequest.value);
      onEditFilter();
    }

    function onEditFilter() {
      filteredScheduleStatuses.value = filterScheduleStatuses(scheduleStatuses.value);
    }

    function filterScheduleStatuses(scheduleStatuses: ScheduleStatusResponse[]) {
      return scheduleStatuses.filter(filterScheduleStatus);
    }

    function filterScheduleStatus(scheduleStatus: ScheduleStatusResponse) {
      return (
          checkFilter(scheduleStatus.name, scheduleStatusRequest.value.name) &&
          checkFilter(scheduleStatus.type, scheduleStatusRequest.value.type)
      );
    }

    function resetFilters() {
      scheduleStatusRequest.value = {
        name: "",
        type: ""
      }
      onEditFilter();
    }

    provide("requestScheduleStatus", requestScheduleStatus);

    return {
      scheduleStatusRequest,
      scheduleStatuses,
      filteredScheduleStatuses,
      updateSearch,
      requestScheduleStatus,
      onEditFilter,
      resetFilters
    }
  }
});
</script>

<style scoped>
.schedule-type-select-field {
  width: 200px;
}
</style>
