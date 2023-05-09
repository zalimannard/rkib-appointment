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
  mounted() {
    this.$nextTick(() => {
      this.validateField();
    });
  },
  computed: {
    innerValue: {
      get() {
        return this.formattedValue;
      },
      set(value) {
        this.updateValue({ target: { value } });
      }
    },
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
    validateField() {
      if (this.$refs[this.refValue]) {
        this.$refs[this.refValue].validate();
      }
    },
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
    }
  }
};
</script>

<template>
  <v-text-field
    :ref="refValue"
    v-model="innerValue"
    :rules="rules"
    :validate-on-blur="validateOnBlur"
    class="base-text-field"
    density="comfortable"
    v-bind="$attrs"
    variant="outlined"
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
