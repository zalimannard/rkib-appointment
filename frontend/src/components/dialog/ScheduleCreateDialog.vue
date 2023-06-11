<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createSchedule"
      ok-button-text="Создать"
      title="Создание нового элемента графика"
  >
    <v-col class="ma-0 pa-0">
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="selectedScheduleDoctor"
              label="Доктор"
              required-asterisk
              @focus="openDoctorDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="selectedScheduleProcedure"
              label="Услуга"
              required-asterisk
              @focus="openProcedureDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="selectedScheduleAppointment"
              label="Обращение"
              @focus="openAppointmentDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="selectedScheduleStatus"
              label="Статус"
              required-asterisk
              @focus="openStatusDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="comment"
              label="Комментарий"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col class="pr-3" cols="6">
          <masked-text-field
              v-model="scheduleDate"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.dateMask"
              label="Дата"
              required-asterisk
          />
        </v-col>
        <v-col class="pl-3" cols="6">
          <masked-text-field
              v-model="scheduleTime"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.timeMask"
              label="Время"
              required-asterisk
          />
        </v-col>
      </v-row>
    </v-col>
  </base-dialog>

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

import {computed, defineComponent, ref} from "vue";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import ProcedureTable from "@/components/table/ProcedureTable.vue";
import ScheduleStatusTable from "@/components/table/ScheduleStatusTable.vue";
import AppointmentTable from "@/components/table/AppointmentTable.vue";
import DoctorTable from "@/components/table/DoctorTable.vue";
import type {PersonResponse} from "@/types/person";
import type {ProcedureResponse} from "@/types/procedure";
import type {AppointmentResponse} from "@/types/appointment";
import type {ScheduleStatusResponse} from "@/types/schedulestatus";
import {dateMask, phoneMask, timeMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";
import {fromDefaultToIso, fromIsoToDefault, roleCodeToString} from "@/utils";
import {requiredRule} from "@/rules";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";

export default defineComponent({
  components: {
    DoctorTable, AppointmentTable, ScheduleStatusTable, ProcedureTable,
    MaskedTextField,
    BaseDialog
  },
  props: {
    modelValue: Boolean,
    closeDialog: {
      type: Function,
      required: true
    }
  },
  emits: ['scheduleCreated', 'updateSearchInput', 'update:modelValue'],
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
    const scheduleDate = ref("");
    const scheduleTime = ref("");
    const comment = ref("");


    const rules = {
      required: requiredRule,
      date: requiredRule,
      time: requiredRule
    };
    const masks = {
      dateMask,
      timeMask,
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

    const handleDoctorRowClicked = (row: PersonResponse) => {
      selectedDoctor.value = row;
      isDoctorDialogOpened.value = false;
      selectedScheduleDoctor.value = calcPersonPresentation(row);
    };

    const handleProcedureRowClicked = (row: ProcedureResponse) => {
      selectedProcedure.value = row;
      isProcedureDialogOpened.value = false;
      selectedScheduleProcedure.value = row.name;
    };

    const handleAppointmentRowClicked = (row: AppointmentResponse) => {
      selectedAppointment.value = row;
      isAppointmentDialogOpened.value = false;
      selectedScheduleAppointment.value = calcAppointmentPresentation(row);
    };

    const handleStatusRowClicked = (row: ScheduleStatusResponse) => {
      selectedStatus.value = row;
      isStatusDialogOpened.value = false;
      selectedScheduleStatus.value = row.name;
    };

    const internalValue = computed({
      get: () => props.modelValue,
      set: (val) => {
        emit("update:modelValue", val);
      }
    });

    function createSchedule() {
      let basicAuth = localStorage.getItem("auth");
      let formattedTime = utils.fromDefaultToIso(scheduleDate.value) + "T"
          + scheduleTime.value + "Z";
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/schedules",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          doctorId: selectedDoctor.value?.employee.id,
          procedureId: selectedProcedure.value?.id,
          appointmentId: selectedAppointment.value?.id === ""
              ? null
              : selectedAppointment.value?.id,
          statusId: selectedStatus.value?.id,
          appointmentTime: formattedTime,
          commentary: comment.value === ""
              ? null
              : comment.value,
        }
      }).then(() => {
        showAlert("success", "Обращение успешно создано");
        emit("scheduleCreated");
      }).catch((error) => {
        console.error(error)
        showAlert("error", "Ошибка при добавлении обращения");
      });
      emit("updateSearchInput", "");
    }

    function calcPersonPresentation(item: PersonResponse) {
      return item.lastName + " "
          + item.firstName[0] + "."
          + (item.patronymic ? (item.patronymic[0] + ". ") : " ");
    }

    function calcAppointmentPresentation(item: AppointmentResponse) {
      return calcPersonPresentation(item.patient.person)
          + (item.doctorNote != null ? " / " + item.doctorNote : "")
          + " / Статус: " + item.status.name;
    }

    return {
      rules,
      masks,
      backspaceHandlers,
      utils,
      comment,
      scheduleDate,
      scheduleTime,
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
      selectedScheduleDoctor,
      selectedScheduleProcedure,
      selectedScheduleAppointment,
      selectedScheduleStatus,
      internalValue,
      createSchedule,
      calcPersonPresentation,
      calcAppointmentPresentation,
      handleDoctorRowClicked,
      handleProcedureRowClicked,
      handleAppointmentRowClicked,
      handleStatusRowClicked,
      openDoctorDialog,
      openProcedureDialog,
      openAppointmentDialog,
      openStatusDialog
    };
  }
})
</script>

<style scoped>

</style>