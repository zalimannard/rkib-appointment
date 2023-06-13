<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedScheduleDoctor"
              class="header-cell"
              density="comfortable"
              label="Доктор"
              @focus="openDoctorDialog"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedScheduleProcedure"
              class="header-cell"
              density="comfortable"
              label="Услуга"
              @focus="openProcedureDialog"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedScheduleAppointment"
              class="header-cell"
              density="comfortable"
              label="Обращение"
              @focus="openAppointmentDialog"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedScheduleDate"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.dateMask"
              class="header-cell"
              density="comfortable"
              label="Дата"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="selectedScheduleStatus"
              class="header-cell"
              density="comfortable"
              label="Статус"
              @focus="openStatusDialog"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredSchedules"
          :key="item.id"
          :class="{ 'light-row': index % 2 === 0, 'dark-row': index % 2 !== 0 }"
          class="table-row"
      >
        <td>{{ calcPersonPresentation(item.doctor.person) }}</td>
        <td>{{ item.procedure.name }}</td>
        <td>{{ calcAppointmentPresentation(item.appointment) }}</td>
        <td>{{ calcDate(item) }}</td>
        <td>{{ item.status.name }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>

  <v-dialog v-model="isDoctorDialogOpened" max-width="1152px">
    <DoctorTable
        :searchInput="searchInputDoctor"
        @updateSearchInput="searchInputDoctor = $event"
        @row-clicked="handleDoctorRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isProcedureDialogOpened" max-width="1152px">
    <ProcedureTable
        :searchInput="searchInputProcedure"
        @updateSearchInput="searchInputProcedure = $event"
        @row-clicked="handleProcedureRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isAppointmentDialogOpened" max-width="1152px">
    <AppointmentTable
        :searchInput="searchInputAppointment"
        @updateSearchInput="searchInputAppointment = $event"
        @row-clicked="handleAppointmentRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isStatusDialogOpened" max-width="1152px">
    <ScheduleStatusTable
        :searchInput="searchInputStatus"
        @updateSearchInput="searchInputStatus = $event"
        @row-clicked="handleStatusRowClicked"
    />
  </v-dialog>
</template>

<script lang="ts">

import {defineComponent, ref} from "vue";
import CustomTable from "@/components/table/CustomTable.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import type {PersonResponse} from "@/types/person";
import type {ProcedureResponse} from "@/types/procedure";
import type {AppointmentResponse} from "@/types/appointment";
import type {ScheduleStatusResponse} from "@/types/schedulestatus";
import type {ScheduleResponse} from "@/types/schedule";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";
import {checkFilter, fromDateTimeToIsoDate, fromDefaultToIso, fromIsoToDefault, roleCodeToString} from "@/utils";
import {onMounted} from "vue-demi";
import PatientTable from "@/components/table/PatientTable.vue";
import ScheduleStatusTable from "@/components/table/ScheduleStatusTable.vue";
import AppointmentTable from "@/components/table/AppointmentTable.vue";
import ProcedureTable from "@/components/table/ProcedureTable.vue";
import DoctorTable from "@/components/table/DoctorTable.vue";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";

export default defineComponent({
  components: {
    DoctorTable,
    ProcedureTable,
    AppointmentTable,
    ScheduleStatusTable,
    PatientTable,
    MaskedTextField,
    CustomTable
  },

  setup(props, {emit}) {
    const isDoctorDialogOpened = ref(false);
    const isProcedureDialogOpened = ref(false);
    const isAppointmentDialogOpened = ref(false);
    const isStatusDialogOpened = ref(false);

    const selectedDoctor = ref<PersonResponse | null>(null);
    const selectedProcedure = ref<ProcedureResponse | null>(null);
    const selectedAppointment = ref<AppointmentResponse | null>(null);
    const selectedStatus = ref<ScheduleStatusResponse | null>(null);

    const searchInputDoctor = ref({name: ""});
    const searchInputProcedure = ref({name: ""});
    const searchInputAppointment = ref({name: ""});
    const searchInputStatus = ref({name: ""});

    const openDoctorDialog = () => {
      setTimeout(() => {
        isDoctorDialogOpened.value = true;
      }, 100);
    };

    const openProcedureDialog = () => {
      setTimeout(() => {
        isProcedureDialogOpened.value = true;
      }, 100);
    };

    const openAppointmentDialog = () => {
      setTimeout(() => {
        isAppointmentDialogOpened.value = true;
      }, 100);
    };

    const openStatusDialog = () => {
      setTimeout(() => {
        isStatusDialogOpened.value = true;
      }, 100);
    };

    const selectedScheduleDoctor = ref("Не выбрано");
    const selectedScheduleProcedure = ref("Не выбрано");
    const selectedScheduleAppointment = ref("Не выбрано");
    const selectedScheduleStatus = ref("Не выбрано");
    const selectedScheduleDate = ref("");

    const handleDoctorRowClicked = (row: PersonResponse) => {
      selectedDoctor.value = row;
      isDoctorDialogOpened.value = false;
      selectedScheduleDoctor.value = calcPersonPresentation(row);
      updateSearch();
    };

    const handleProcedureRowClicked = (row: ProcedureResponse) => {
      selectedProcedure.value = row;
      isProcedureDialogOpened.value = false;
      selectedScheduleProcedure.value = row.name;
      updateSearch();
    };

    const handleAppointmentRowClicked = (row: AppointmentResponse) => {
      selectedAppointment.value = row;
      isAppointmentDialogOpened.value = false;
      selectedScheduleAppointment.value = calcAppointmentPresentation(row);
      updateSearch();
    };

    const handleStatusRowClicked = (row: ScheduleStatusResponse) => {
      selectedStatus.value = row;
      isStatusDialogOpened.value = false;
      selectedScheduleStatus.value = row.name;
      updateSearch();
    };

    const schedules = ref<ScheduleResponse[]>([]);
    const filteredSchedules = ref<ScheduleResponse[]>([]);

    const masks = {
      dateMask,
      phoneMask
    };
    const backspaceHandlers = {
      handleBackspaceForDate,
      handleBackspaceForPhoneNumber
    };
    const utils = {
      fromIsoToDefault,
      fromDefaultToIso,
      roleCodeToString
    }

    const requestSchedule = async () => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/schedules", {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        schedules.value = response.data.map((schedule: ScheduleResponse) => {
          return {
            ...schedule,
            appointmentTime: fromDateTimeToIsoDate(schedule.appointmentTime),
          };
        });
        onEditFilter();
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    }

    function onEditFilter() {
      filteredSchedules.value = filterPeople(schedules.value);
    }

    function filterPeople(schedules: ScheduleResponse[]) {
      return schedules.filter(filterPerson);
    }

    function filterPerson(schedule: ScheduleResponse) {
      return (
          checkFilter(schedule.doctor.id, selectedDoctor.value?.employee.id ?? "") &&
          checkFilter(schedule.procedure.id, selectedProcedure.value?.id ?? "") &&
          (schedule.appointment == null || checkFilter(schedule.appointment.id, selectedAppointment.value?.id ?? "")) &&
          checkFilter(schedule.status.id, selectedStatus.value?.id ?? "") &&
          checkFilter(utils.fromIsoToDefault(schedule.appointmentTime),
              selectedScheduleDate.value)
      );
    }

    onMounted(() => {
      emit("provideRequestSchedule", requestSchedule);
      requestSchedule();
    });

    function calcPersonPresentation(item: PersonResponse) {
      return item.lastName + " "
          + item.firstName[0] + "."
          + (item.patronymic ? (item.patronymic[0] + ". ") : " ");
    }

    function calcAppointmentPresentation(item: AppointmentResponse) {
      if (item == null) {
        return "";
      }
      return calcPersonPresentation(item.patient.person)
          + (item.doctorNote != null ? " / " + item.doctorNote : "")
          + " / Статус: " + item.status.name;
    }

    function calcDate(item: ScheduleResponse) {
      return utils.fromIsoToDefault(item.appointmentTime)
    }

    function updateSearch() {
      onEditFilter();
    }

    return {
      isDoctorDialogOpened,
      isProcedureDialogOpened,
      isAppointmentDialogOpened,
      isStatusDialogOpened,
      selectedDoctor,
      selectedProcedure,
      selectedAppointment,
      selectedStatus,
      searchInputDoctor,
      searchInputProcedure,
      searchInputAppointment,
      searchInputStatus,
      filteredSchedules,
      selectedScheduleDoctor,
      selectedScheduleProcedure,
      selectedScheduleAppointment,
      selectedScheduleStatus,
      selectedScheduleDate,
      updateSearch,
      calcPersonPresentation,
      calcAppointmentPresentation,
      calcDate,
      handleDoctorRowClicked,
      handleProcedureRowClicked,
      handleAppointmentRowClicked,
      handleStatusRowClicked,
      openDoctorDialog,
      openProcedureDialog,
      openAppointmentDialog,
      openStatusDialog,
      utils,
      masks,
      backspaceHandlers
    };
  }
});
</script>

<style scoped>

</style>