<script>
export default {
  name: "BaseTextField",
  inheritAttrs: false,
  props: {
    modelValue: {
      type: [String],
      default: ""
    },
    label: {
      type: [String],
      default: ""
    },
    requiredMark: {
      type: [Boolean],
      default: false
    },
    rules: {
      type: Array,
      default: () => []
    },
    capitalizeFirstLetter: {
      type: [Boolean],
      default: false
    },
    mask: {
      type: Function,
      default: null
    },
    handleBackspace: {
      type: Function,
      default: null
    },
    refValue: {
      type: String,
      default: ""
    },
    validateOnBlur: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    formattedValue() {
      if (this.mask) {
        return this.mask(this.modelValue);
      } else if (this.capitalizeFirstLetter) {
        return this.capitalize(this.modelValue);
      }
      return this.modelValue;
    }
  },
  methods: {
    updateValue(event) {
      let value;
      if (this.mask) {
        value = this.mask(event.target.value);
      } else {
        value = event.target.value;
      }
      if (this.capitalizeFirstLetter && value.length === 1) {
        value = this.capitalize(value);
      }
      this.$emit("update:modelValue", value);
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
    },
    reset() {
      this.refValue = "";
      this.$refs[this.refValue].reset();
    }
  }
};
</script>

<template>
  <v-text-field
    :ref="refValue"
    :rules="rules"
    :validate-on-blur="validateOnBlur"
    :value="formattedValue"
    class="base-text-field"
    density="comfortable"
    v-bind="$attrs"
    variant="outlined"
    @input="updateValue"
    @keydown="handleBackspace"
    @keypress="handleKeypress"
  >
    <template #label>
      <span>{{ label }}</span>
      <span v-if="requiredMark" class="required-asterisk">*</span>
    </template>
    <slot></slot>
  </v-text-field>
</template>

<style scoped>
.required-asterisk {
    color: #F44336;
}
</style>
