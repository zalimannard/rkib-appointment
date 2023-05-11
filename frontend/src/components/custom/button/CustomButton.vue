<template>
  <v-btn
    :block="block"
    :color="computedColor"
    :disabled="disabled"
    :loading="loading"
    :size="computedSize"
    :width="width"
    v-bind="$attrs"
    variant="flat"
    @click="emitCustomClick"
  >
    <slot></slot>
  </v-btn>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    buttonType: {
      type: String,
      default: "button"
    },
    block: {
      type: Boolean,
      default: false
    },
    loading: {
      type: Boolean,
      default: false
    },
    size: {
      type: String,
      default: "default"
    },
    type: {
      type: String,
      default: "regular"
    },
    disabled: {
      type: Boolean,
      default: false
    },
    width: {
      type: Number,
      default: null
    }
  },
  computed: {
    computedSize() {
      switch (this.size) {
        case "default":
          return "x-large";
        case "comfortable":
          return "large";
        case "compact":
          return "default";
        default:
          return "x-large";
      }
    },
    computedColor() {
      switch (this.type) {
        case "regular":
          return "indigo";
        case "confirm":
          return "green";
        case "cancel":
          return "red";
        default:
          return "indigo";
      }
    }
  },
  methods: {
    emitCustomClick(event) {
      this.$emit("custom-click", event);
    }
  }
};
</script>
