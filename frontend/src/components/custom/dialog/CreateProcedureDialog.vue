<template>
  <base-dialog
    ok-button-text="Создать"
    title="Создание новой услуги"
    value
  >
    <masked-text-field
      capitalize-first-letter
      label="Название"
      required-asterisk
    />
  </base-dialog>
</template>

<script>
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";

import { requiredRule } from "@/rules";
import BaseDialog from "@/components/custom/dialog/BaseDialog.vue";

export default {
  components: { BaseDialog, MaskedTextField },
  props: {
    value: Boolean
  },
  data() {
    return {
      rules: {
        requiredRule
      }
    };
  }
};
</script>

<style scoped>

</style>