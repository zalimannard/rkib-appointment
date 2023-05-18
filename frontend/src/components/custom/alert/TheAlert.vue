<template>
  <v-fade-transition>
    <v-alert
        v-model="alertState.show"
        :class="computedAlertClass"
        :text="alertState.text"
        :type="computedAlertType"
        rounded="lg"
        variant="elevated"
        width="500"
    />
  </v-fade-transition>
</template>

<script lang="ts">
import {computed} from 'vue';
import alertState from "@/components/custom/alert/AlertState";

export default {
  setup() {
    const validTypes = ["error", "warning", "info", "success"];
    const defaultType = "error";

    function ensureValidType(type: string): "error" | "success" | "warning" | "info" {
      return validTypes.includes(type) ? type as "error" | "success" | "warning" | "info" : defaultType;
    }

    const computedAlertType = computed(() => ensureValidType(alertState.type));
    const computedAlertClass = computed(() => `alert-${computedAlertType.value}`);

    return {
      alertState,
      computedAlertType,
      computedAlertClass,
    };
  }
}
</script>


<style scoped>
.v-alert {
  position: fixed;
  top: 2px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 1000;
}
</style>
