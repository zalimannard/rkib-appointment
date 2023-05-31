<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createAppointmentStatus"
      ok-button-text="Создать"
      title="Создание нового статуса обращений"
  >
    <v-col>
      <v-row>
        <masked-text-field
            v-model="appointmentStatusName"
            :rules="rules.requiredRule"
            capitalize-first-letter
            label="Название"
            required-asterisk
        />
      </v-row>
      <v-row>
        <appointment-type-select
            :status="appointmentStatusTypeName"
            @update:status="appointmentStatusTypeName = $event"
        />
      </v-row>
    </v-col>
  </base-dialog>
</template>

<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";
import type {AppointmentStatusRequest, AppointmentStatusResponse} from "@/types/appointmentstatus";
import {onMounted, provide} from "vue-demi";
import AppointmentTypeSelect from "@/components/select/AppointmentTypeSelect.vue";

export default defineComponent({
  components: {
    AppointmentTypeSelect,
    BaseDialog,
    MaskedTextField
  },
  props: {
    value: Boolean,
    closeDialog: {
      type: Function,
      required: true
    }
  },

  setup(props, {emit}) {
    const appointmentStatus = ref<AppointmentStatusRequest>();

    const rules = {
      requiredRule
    };

    const setData = (data: AppointmentStatusResponse) => {
      appointmentStatus.value = {
        ...appointmentStatus.value,
        name: data.name,
        type: data.type
      };
    }

    const appointmentStatusName = computed({
      get: () => appointmentStatus.value?.name || "",
      set: (value) => {
        let typeValue = "";
        if (appointmentStatus.value) {
          typeValue = appointmentStatus.value.type;
        }
        appointmentStatus.value = {...appointmentStatus.value, name: value, type: typeValue};
      }
    });

    const appointmentStatusTypeName = computed({
      get: () => appointmentStatus.value?.type || "",
      set: (value) => {
        let nameValue = "";
        if (appointmentStatus.value) {
          nameValue = appointmentStatus.value.name;
        }
        appointmentStatus.value = {...appointmentStatus.value, name: nameValue, type: value};
      }
    });

    function createAppointmentStatus() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/appointmentStatuses",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: appointmentStatus.value?.name,
          type: appointmentStatus.value?.type
        }
      }).then(() => {
        showAlert("success", "Статус обращений успешно создан");
        emit("appointmentStatusCreated")
      }).catch((error) => {
        error.value.console.error(error)
        showAlert("error", "Ошибка при добавлении статуса обращений");
      });
      emit("updateSearchInput", appointmentStatus.value);
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", appointmentStatus.value);
        }
      }
    });

    provide("setData", setData);

    return {
      appointmentStatus,
      rules,
      internalValue,
      appointmentStatusName,
      appointmentStatusTypeName,
      createAppointmentStatus,
      setData
    };
  }
});
</script>

<style scoped>

</style>
