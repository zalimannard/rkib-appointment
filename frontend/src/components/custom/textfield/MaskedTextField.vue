<template>
  <base-text-field
    v-model="formattedValue"
    v-bind="$attrs"
    @keypress="handleKeypress"
  />
</template>

<script>
import BaseTextField from "./BaseTextField.vue";

export default {
  components: {
    BaseTextField: BaseTextField
  },
  inheritAttrs: false,
  props: {
    capitalizeFirstLetter: {
      type: Boolean,
      default: false
    },
    mask: {
      type: Function,
      default: null
    }
  },
  computed: {
    formattedValue: {
      get() {
        return this.applyMaskOrCapitalization(this.$attrs.modelValue);
      },
      set(value) {
        this.$emit("update:modelValue", this.applyMaskOrCapitalization(value));
      }
    }
  },
  methods: {
    applyMaskOrCapitalization(value) {
      if (this.mask) {
        return this.mask(value);
      } else if (this.capitalizeFirstLetter) {
        return this.capitalize(value);
      }
      return value;
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    handleKeypress(event) {
      if (this.mask) {
        const oldValue = event.target.value;
        const newChar = String.fromCharCode(event.charCode);
        const newValue = oldValue + newChar;
        const maskedValue = this.mask(newValue);

        if (maskedValue.length < newValue.length) {
          event.preventDefault();
        }
      }
    }
  }
};
</script>