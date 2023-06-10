<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="appointmentStatusRequest.name"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Название"
              @input="updateSearch"
          />
        </th>
        <th class="text-left schedule-type-select-field" scope="col">
          <appointment-type-select
              :status="appointmentStatusRequest.type"
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              @update:status="appointmentStatusRequest.type = $event"
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
          class="table-row"
          @click="handleRowClick(item)"
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
import type {AppointmentStatusRequest, AppointmentStatusResponse} from "@/types/appointmentstatus";
import {onMounted, provide} from "vue-demi";
import AppointmentTypeSelect from "@/components/select/AppointmentTypeSelect.vue";

export default defineComponent({
  components: {
    AppointmentTypeSelect,
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
    const appointmentStatusRequest = ref<AppointmentStatusRequest>({
      name: "",
      type: ""
    });

    const appointmentStatuses = ref<AppointmentStatusResponse[]>([]);
    const filteredAppointmentStatuses = ref<AppointmentStatusResponse[]>([]);

    watch(() => props.searchInput, (newVal) => {
      appointmentStatusRequest.value.name = newVal.name;
      appointmentStatusRequest.value.type = newVal.type;
    }, {immediate: true});

    const requestAppointmentStatus = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/appointmentStatuses", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        appointmentStatuses.value = response.data;
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    }

    onMounted(() => {
      emit("provideRequestAppointmentStatus", requestAppointmentStatus);
      requestAppointmentStatus();
    });

    function updateSearch() {
      emit("updateSearchInput", appointmentStatusRequest.value);
      onEditFilter();
    }

    function onEditFilter() {
      filteredAppointmentStatuses.value = filterAppointmentStatuses(appointmentStatuses.value);
    }

    function filterAppointmentStatuses(appointmentStatuses: AppointmentStatusResponse[]) {
      return appointmentStatuses.filter(filterAppointmentStatus);
    }


    function filterAppointmentStatus(appointmentStatus: AppointmentStatusResponse) {
      return checkFilter(appointmentStatus.name, appointmentStatusRequest.value.name) &&
          checkFilter(appointmentStatus.type, appointmentStatusRequest.value.type);
    }


    function resetFilters() {
      appointmentStatusRequest.value = {
        name: "",
        type: ""
      }
      onEditFilter();
    }

    const handleRowClick = (item: AppointmentStatusResponse) => {
      emit("row-clicked", item);
    };

    provide("requestAppointmentStatus", requestAppointmentStatus);

    return {
      appointmentStatusRequest,
      appointmentStatuses,
      filteredAppointmentStatuses,
      handleRowClick,
      updateSearch,
      requestAppointmentStatus,
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
