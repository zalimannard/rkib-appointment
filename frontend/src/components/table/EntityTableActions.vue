<template>
  <v-row justify="end">
    <custom-button
        class="button-margin"
        size="compact"
        type="confirm"
        width="200"
        @click="$emit('openCreateDialog')"
    >
      Создать
    </custom-button>

    <custom-button
        class="button-margin"
        size="compact"
        type="regular"
        width="200"
        @click="$emit('resetFilters')"
    >
      Очистить фильтры
    </custom-button>
  </v-row>
</template>

<script>

import {defineComponent} from "vue";
import CustomButton from "@/components/button/CustomButton.vue";

export default defineComponent({
  components: {CustomButton}
});
</script>

<style scoped>
.button-margin {
  margin-right: 12px; /* Change this value to the desired space */
}
</style>