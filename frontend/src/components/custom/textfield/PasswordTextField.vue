<template>
  <base-text-field
    v-model="innerValue"
    :append-inner-icon="isPasswordVisible ?'mdi-eye':'mdi-eye-off'"
    :type="isPasswordVisible ?'text': 'password'"
    v-bind="$attrs"
    @click:append-inner="isPasswordVisible=!isPasswordVisible"
  />
</template>

<script>
import BaseTextField from "./BaseTextField.vue";

export default {
  components: {
    BaseTextField
  },
  inheritAttrs: false,
  data() {
    return {
      isPasswordVisible: false
    };
  },
  computed: {
    innerValue: {
      get() {
        return this.$attrs.modelValue;
      },
      set(value) {
        this.$emit("update:modelValue", value);
      }
    }
  }
};
</script>