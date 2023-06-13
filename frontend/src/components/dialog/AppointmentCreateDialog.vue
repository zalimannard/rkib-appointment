<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createAppointment"
      ok-button-text="Создать"
      title="Создание нового обращения"
  >
    <v-col class="ma-0 pa-0">
      <v-row no-gutters>
        <v-col class="pr-3" cols="7">
          <masked-text-field
              v-model="selectedAppointmentPerson"
              label="Пациент"
              required-asterisk
              @focus="openPatientDialog"
          />
        </v-col>
        <v-col class="pl-3" cols="5">
          <masked-text-field
              v-model="selectedAppointmentStatus"
              label="Статус"
              required-asterisk
              @focus="openStatusDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="selectedAppointmentInstitutionName"
              label="Направшее учреждение"
              @focus="openInstitutionDialog"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="doctorNote"
              label="Направление"
          />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <masked-text-field
              v-model="diagnosis"
              label="Диагноз"
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

  <v-dialog v-model="isPatientDialogOpened" max-width="1152px">
    <PatientTable
        :searchInput="searchInputPerson"
        @updateSearchInput="searchInputPerson = $event"
        @row-clicked="handlePersonRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isInstitutionDialogOpened" max-width="800px">
    <InstitutionTable
        :searchInput="searchInputInstitution"
        @updateSearchInput="searchInputInstitution = $event"
        @row-clicked="handleInstitutionRowClicked"
    />
  </v-dialog>

  <v-dialog v-model="isStatusDialogOpened" max-width="800px">
    <AppointmentStatusTable
        :searchInput="searchInputStatus"
        @updateSearchInput="searchInputStatus = $event"
        @row-clicked="handleAppointmentStatusRowClicked"
    />
  </v-dialog>
</template>

<script lang="ts">
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {computed, defineComponent, ref} from "vue";
import AppointmentStatusTable from "@/components/table/AppointmentStatusTable.vue";
import PatientTable from "@/components/table/PatientTable.vue";
import InstitutionTable from "@/components/table/InstitutionTable.vue";
import type {AppointmentStatusResponse} from "@/types/appointmentstatus";
import type {InstitutionResponse} from "@/types/institution";
import type {PersonResponse} from "@/types/person";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";

export default defineComponent({
  components: {
    InstitutionTable,
    PatientTable,
    AppointmentStatusTable,
    BaseDialog,
    MaskedTextField
  },
  props: {
    modelValue: Boolean,
    closeDialog: {
      type: Function,
      required: true
    }
  },
  emits: ['appointmentCreated', 'updateSearchInput', 'update:modelValue'],
  setup(props, {emit}) {
    const isPatientDialogOpened = ref(false);
    const isInstitutionDialogOpened = ref(false);
    const isStatusDialogOpened = ref(false);
    const selectedPerson = ref<PersonResponse | null>(null);
    const selectedInstitution = ref<InstitutionResponse | null>(null);
    const selectedStatus = ref<AppointmentStatusResponse | null>(null);

    const searchInputPerson = ref({name: ""});
    const searchInputInstitution = ref({name: ""});
    const searchInputStatus = ref({name: ""});

    const doctorNote = ref("");
    const diagnosis = ref("");
    const comment = ref("");

    const internalValue = computed({
      get: () => props.modelValue,
      set: (val) => {
        emit("update:modelValue", val);
      }
    });

    const selectedAppointmentPerson = ref("Не выбрано");
    const selectedAppointmentInstitutionName = ref("Не выбрано");
    const selectedAppointmentStatus = ref("Не выбрано");

    const handlePersonRowClicked = (row: PersonResponse) => {
      selectedPerson.value = row;
      isPatientDialogOpened.value = false;
      selectedAppointmentPerson.value = calcPersonPresentation(row)
          + (row.patient.phoneNumber ? (row.patient.phoneNumber) : "");
    };

    const handleInstitutionRowClicked = (row: InstitutionResponse) => {
      selectedInstitution.value = row;
      isInstitutionDialogOpened.value = false;
      selectedAppointmentInstitutionName.value = row.name;
    };

    const handleAppointmentStatusRowClicked = (row: AppointmentStatusResponse) => {
      selectedStatus.value = row;
      isStatusDialogOpened.value = false;
      selectedAppointmentStatus.value = row.name;
    };

    const openPatientDialog = () => {
      setTimeout(() => {
        isPatientDialogOpened.value = true;
      }, 100);
    };

    const openInstitutionDialog = () => {
      setTimeout(() => {
        isInstitutionDialogOpened.value = true;
      }, 100);
    };

    const openStatusDialog = () => {
      setTimeout(() => {
        isStatusDialogOpened.value = true;
      }, 100);
    };

    function createAppointment() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/appointments",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          patientId: selectedPerson.value?.patient.id,
          institutionId: selectedInstitution.value?.id === ""
              ? null
              : selectedInstitution.value?.id,
          statusId: selectedStatus.value?.id === ""
              ? null
              : selectedStatus.value?.id,
          doctorNote: doctorNote.value === ""
              ? null
              : doctorNote.value,
          diagnosis: diagnosis.value === ""
              ? null
              : diagnosis.value,
          commentary: comment.value === ""
              ? null
              : comment.value,
        }
      }).then(() => {
        showAlert("success", "Обращение успешно создано");
        emit("appointmentCreated");
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

    return {
      doctorNote,
      diagnosis,
      comment,
      internalValue,
      createAppointment,
      isPatientDialogOpened,
      isInstitutionDialogOpened,
      isStatusDialogOpened,
      selectedInstitution,
      selectedAppointmentPerson,
      selectedAppointmentInstitutionName,
      selectedAppointmentStatus,
      searchInputPerson,
      searchInputInstitution,
      searchInputStatus,
      calcPersonPresentation,
      handlePersonRowClicked,
      handleInstitutionRowClicked,
      handleAppointmentStatusRowClicked,
      openPatientDialog,
      openInstitutionDialog,
      openStatusDialog
    };
  }
});
</script>

<style scoped>

</style>
