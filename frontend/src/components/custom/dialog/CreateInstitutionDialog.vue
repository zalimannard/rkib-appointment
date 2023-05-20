<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createProcedure"
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

<script>
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/custom/dialog/BaseDialog.vue";
import axios from "axios";
import {showAlert} from "@/components/custom/alert/AlertState";

export default {
  components: {BaseDialog, MaskedTextField},
  props: {
    value: Boolean,
    searchInput: String,
    onCreateEntity: {
      type: Function,
      required: false
    },
    closeDialog: {
      type: Function,
      required: true
    }
  },
  data() {
    return {
      institution: {
        inputName: this.searchInput
      },
      rules: {
        requiredRule
      }
    };
  },
  methods: {
    createProcedure() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/institutions",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: this.institution.inputName
        }
      }).then(() => {
        showAlert("success", "Учреждение успешно создано");
        this.onCreateEntity();
      }).catch(() => {
        showAlert("error", "Ошибка при добавлении учреждения");
      });
      this.$emit("updateSearchInput", this.institution.inputName);
    }
  },
  computed: {
    internalValue: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit("input", val);
        if (!val) {
          this.$emit("updateSearchInput", this.institution.inputName);
        }
      }
    }
  },
  watch: {
    searchInput(newVal) {
      this.institution.inputName = newVal;
    }
  }
};
</script>
