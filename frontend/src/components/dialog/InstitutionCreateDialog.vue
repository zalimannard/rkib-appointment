<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createInstitution"
      ok-button-text="Создать"
      title="Создание нового учреждения"
  >
    <masked-text-field
        v-model="institution.name"
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
    institutionRequest: {
      type: Object,
      required: true
    },
    closeDialog: {
      type: Function,
      required: true
    }
  },

  setup(props, {emit}) {
    const institution = ref({...props.institutionRequest});

    const rules = {
      requiredRule
    };

    watch(() => props.institutionRequest, (newVal) => {
      institution.value = {...newVal};
    });

    function createInstitution() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/institutions",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: institution.value.name
        }
      }).then(() => {
        showAlert("success", "Учреждение успешно создано");
        emit('institutionCreated');
      }).catch((error) => {
        console.error(error)
        showAlert("error", "Ошибка при добавлении учреждения");
      });
      emit("updateSearchInput", institution.value.name);
    }

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", institution.value.name);
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
