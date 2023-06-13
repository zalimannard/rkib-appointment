<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createSchedule"
      ok-button-text="Сохранить"
      title="Запись на приём"
  >
    <v-col class="ma-0 pa-0">
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="procedure"
              label="Услуга"
              readonly
              required-asterisk
              @focus="openProcedureDialog"
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
    </v-col>
  </base-dialog>

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
import {onMounted, provide} from "vue-demi";
import type {ScheduleResponse} from "@/types/schedule";
import CustomButton from "@/components/button/CustomButton.vue";

export default defineComponent({
  components: {
    CustomButton,
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

    const schedule = ref<ScheduleResponse | null>(null);

    const searchInputDoctor = ref({name: ""});
    const searchInputProcedure = ref({name: ""});
    const searchInputAppointment = ref({name: ""});
    const searchInputStatus = ref({name: ""});

    const doctor = ref("");
    const procedure = ref("");
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

    const setData = async (data: string) => {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/schedules/" + data, {
          headers: {"Authorization": "Basic " + basicAuth}
        });
        schedule.value = response.data;

        let date = new Date(schedule.value?.appointmentTime || "");
        scheduleDate.value = utils.fromIsoToDefault((schedule.value?.appointmentTime || "").split("T")[0]);
        scheduleTime.value = date.toTimeString().split(' ')[0];
        doctor.value = calcPersonPresentation(schedule.value?.doctor.person);
        procedure.value = schedule.value?.procedure.name || "";
        comment.value = schedule.value?.commentary || "";
      } catch (error) {
        showAlert("error", "Не удалось получить данные")
      }
    };

    onMounted(() => {
      emit("provideSetData", setData);
    });

    provide("setData", setData);

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

    const selectedScheduleStatus = ref("Не выбрано");

    const handleAppointmentRowClicked = (row: AppointmentResponse) => {
      selectedAppointment.value = row;
      isAppointmentDialogOpened.value = false;
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
      axios({
        method: "put",
        url: import.meta.env.VITE_API_URL + "/api/v1/schedules/" + schedule.value?.id,
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          doctorId: schedule.value?.doctor.id,
          procedureId: schedule.value?.procedure.id,
          appointmentId: schedule.value?.appointment != null
              ? schedule.value?.appointment.id
              : null,
          statusId: selectedStatus.value?.id,
          appointmentTime: schedule.value?.appointmentTime,
          commentary: schedule.value?.commentary
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

    function calcPersonPresentation(item: PersonResponse | undefined) {
      if (item === undefined) {
        return "";
      }
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
      doctor,
      procedure,
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
      selectedScheduleStatus,
      internalValue,
      schedule,
      createSchedule,
      calcPersonPresentation,
      calcAppointmentPresentation,
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