<template>
  <base-text-field
    v-model="formattedValue"
    :rules="rules"
    v-bind="$attrs"
    @input="handleInput"
    @keydown="handleKeyDown"
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
    },
    rules: {
      type: Array,
      default: () => []
    },
    handleBackspace: {
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
      } else if (this.capitalizeFirstLetter && value) {
        return this.capitalize(value);
      }
      return value;
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    handleInput(event) {
      const oldValue = event.target.value;
      const newValue = this.applyMaskOrCapitalization(oldValue);

      if (newValue !== oldValue) {
        event.target.value = newValue;
        this.$emit("update:modelValue", newValue);
      }

      // Если маска задана и значение соответствует маске, вызываем пользовательское событие mask-valid
      if (this.mask && this.mask(newValue) === newValue) {
        this.$emit("mask-valid");
      }
    },
    handleKeyDown(event) {
      if (event.key === "Backspace") {
        if (this.handleBackspace) {
          // Если обработчик нажатия Backspace передан, вызываем его
          this.handleBackspace(event);
        } else {
          // Если обработчик не передан, выполняем действия по умолчанию
          if (event.target.value.slice(-1) === ".") {
            event.preventDefault();
            this.$emit("update:modelValue", event.target.value.slice(0, -2));
          }
        }
      }
    }
  }
};
</script>
