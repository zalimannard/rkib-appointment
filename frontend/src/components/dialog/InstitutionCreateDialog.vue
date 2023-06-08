<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createInstitution"
      ok-button-text="Создать"
      title="Создание нового учреждения"
  >
    <masked-text-field
        v-model="institutionName"
        :rules="[rules.required]"
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
import type {InstitutionRequest, InstitutionResponse} from "src/types/institution";
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
    const institution = ref<InstitutionRequest>();

    const rules = {
      required: requiredRule
    };

    const setData = (data: InstitutionResponse) => {
      institution.value = {
        ...institution.value,
        name: data.name
      };
    }

    const institutionName = computed({
      get: () => institution.value?.name || "",
      set: (value) => {
        institution.value = {...institution.value, name: value};
      }
    });

    function createInstitution() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/institutions",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: institution.value?.name
        }
      }).then(() => {
        showAlert("success", "Учреждение успешно создано");
        emit("institutionCreated");
      }).catch((error) => {
        console.error(error)
        showAlert("error", "Ошибка при добавлении учреждения");
      });
      emit("updateSearchInput", institution.value);
    }

    onMounted(() => {
      emit("provideSetData", setData);
    });

    const internalValue = computed({
      get: () => props.value,
      set: (val) => {
        emit("input", val);
        if (!val) {
          emit("updateSearchInput", institution.value);
        }
      }
    });

    provide("setData", setData);

    return {
      institution,
      rules,
      internalValue,
      institutionName,
      createInstitution,
      setData
    };
  }
});
</script>

<style scoped>

</style>
