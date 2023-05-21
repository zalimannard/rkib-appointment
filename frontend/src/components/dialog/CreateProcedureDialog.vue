<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createProcedure"
      ok-button-text="Создать"
      title="Создание новой услуги"
  >
    <masked-text-field
        v-model="procedure.inputName"
        :rules="rules.requiredRule"
        capitalize-first-letter
        label="Название"
        required-asterisk
    />
  </base-dialog>
</template>

<script>
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/dialog/BaseDialog.vue";
import axios from "axios";
import {showAlert} from "@/components/alert/AlertState";

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
      procedure: {
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
        url: import.meta.env.VITE_API_URL + "/api/v1/procedures",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          name: this.procedure.inputName
        }
      }).then(() => {
        showAlert("success", "Процедура успешно создана");
        this.onCreateEntity();
      }).catch(() => {
        showAlert("error", "Ошибка при добавлении процедуры");
      });
      this.$emit("updateSearchInput", this.procedure.inputName);
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
          this.$emit("updateSearchInput", this.procedure.inputName);
        }
      }
    }
  },
  watch: {
    searchInput(newVal) {
      this.procedure.inputName = newVal;
    }
  }
};
</script>
