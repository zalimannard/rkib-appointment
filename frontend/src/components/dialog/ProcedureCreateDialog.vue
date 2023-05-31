<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createProcedure"
      ok-button-text="Создать"
      title="Создание новой процедуры"
  >
    <masked-text-field
        v-model="procedureName"
        :rules="rules.requiredRule"
        capitalize-first-letter
        label="Название"
        required-asterisk
    />
  </base-dialog>
</template>

<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";
import type {ProcedureRequest, ProcedureResponse} from "@/types/procedures";
import {onMounted, provide} from "vue-demi";

export default defineComponent({
  components: {
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
    const procedure = ref<ProcedureRequest>();

    const rules = {
      requiredRule
    };

    const setData = (data: ProcedureResponse) => {
      procedure.value = {
        ...procedure.value,
        name: data.name
      };
    }

    const procedureName = computed({
      get: () => procedure.value?.name || "",
      set: (value) => {
        procedure.value = {...procedure.value, name: value};
      }
    });

    function createProcedure() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/procedures",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: procedure.value?.name
        }
      }).then(() => {
        showAlert("success", "Услуга успешно создана");
        emit("procedureCreated");
      }).catch((error) => {
        console.error(error)
        showAlert("error", "Ошибка при добавлении услуги");
      });
      emit("updateSearchInput", procedure.value);
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", procedure.value);
        }
      }
    });

    provide("setData", setData);

    return {
      procedure,
      rules,
      internalValue,
      procedureName,
      createProcedure,
      setData
    };
  }
});
</script>

<style scoped>

</style>
