<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createScheduleStatus"
      ok-button-text="Создать"
      title="Создание нового статуса элемента графика"
  >
    <v-col>
      <v-row>
        <masked-text-field
            v-model="scheduleStatusName"
            :rules="[rules.required]"
            capitalize-first-letter
            label="Название"
            required-asterisk
        />
      </v-row>
      <v-row>
        <schedule-type-select
            :status="scheduleStatusTypeName"
            @update:status="scheduleStatusTypeName = $event"
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
import type {ScheduleStatusRequest, ScheduleStatusResponse} from "@/types/schedulestatus";
import {onMounted, provide} from "vue-demi";
import ScheduleTypeSelect from "@/components/select/ScheduleTypeSelect.vue";

export default defineComponent({
  components: {
    ScheduleTypeSelect,
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
    const scheduleStatus = ref<ScheduleStatusRequest>();

    const rules = {
      required: requiredRule
    };

    const setData = (data: ScheduleStatusResponse) => {
      scheduleStatus.value = {
        ...scheduleStatus.value,
        name: data.name,
        type: data.type
      };
    }

    const scheduleStatusName = computed({
      get: () => scheduleStatus.value?.name || "",
      set: (value) => {
        let typeValue = '';
        if (scheduleStatus.value) {
          typeValue = scheduleStatus.value.type;
        }
        scheduleStatus.value = {...scheduleStatus.value, name: value, type: typeValue};
      }
    });

    const scheduleStatusTypeName = computed({
      get: () => scheduleStatus.value?.type || "",
      set: (value) => {
        let nameValue = '';
        if (scheduleStatus.value) {
          nameValue = scheduleStatus.value.name;
        }
        scheduleStatus.value = {...scheduleStatus.value, name: nameValue, type: value};
      }
    });

    function createScheduleStatus() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/scheduleStatuses",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: scheduleStatus.value?.name,
          type: scheduleStatus.value?.type
        }
      }).then(() => {
        showAlert("success", "Статус элемента графика успешно создан");
        emit("scheduleStatusCreated")
      }).catch((error) => {
        error.value.console.error(error)
        showAlert("error", "Ошибка при добавлении статуса элемента графика");
      });
      emit("updateSearchInput", scheduleStatus.value);
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", scheduleStatus.value);
        }
      }
    });

    provide("setData", setData);

    return {
      scheduleStatus,
      rules,
      internalValue,
      scheduleStatusName,
      scheduleStatusTypeName,
      createScheduleStatus,
      setData
    };
  }
});
</script>

<style scoped>

</style>
