<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createInstitution"
      ok-button-text="Создать"
      title="Создание нового учреждения"
  >
    <masked-text-field
        v-model="institution.inputName"
        :rules="rules.requiredRule"
        capitalize-first-letter
        label="Название"
        required-asterisk
    />
  </base-dialog>
</template>

<script lang="ts">
import {computed, defineComponent, ref, watch} from 'vue';
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";

export default defineComponent({
  components: {BaseDialog, MaskedTextField},
  props: {
    value: Boolean,
    searchInput: String,
    onCreateEntity: {
      type: Function,
      default: () => {
      },
    },
    closeDialog: {
      type: Function,
      required: true
    }
  },
  setup(props, {emit}) {
    const institution = ref({
      inputName: props.searchInput
    });

    const rules = {
      requiredRule
    };

    watch(() => props.searchInput, (newVal) => {
      institution.value.inputName = newVal;
    });

    function createInstitution() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/institutions",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: institution.value.inputName
        }
      }).then(() => {
        showAlert("success", "Учреждение успешно создано");
        props.onCreateEntity();
      }).catch((error) => {
        console.error(error)
        showAlert("error", "Ошибка при добавлении учреждения");
      });
      emit("updateSearchInput", institution.value.inputName);
    }

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", institution.value.inputName);
        }
      }
    });

    return {
      institution,
      rules,
      createInstitution,
      internalValue
    }
  }
});
</script>

<style scoped>

</style>
