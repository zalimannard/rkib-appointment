<template>
  <base-select
      :density="density"
      :items="availableStatuses"
      :onChange="onSelectChange"
      :selected="statusAsArray"
      label="Статус"
  ></base-select>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import BaseSelect from './BaseSelect.vue'

export default defineComponent({
  components: {
    BaseSelect,
  },
  props: {
    status: {
      type: String,
      required: true,
      default: "",
    },
    includeNone: {
      type: Boolean,
      default: false,
    },
    density: {
      type: String,
      default: "default",
    },
    updateSearchInput: {
      type: Function,
      required: false,
    },
  },
  data() {
    return {
      availableStatuses: [
        {text: "Активно", value: "ACTIVE"},
        {text: "Ожидает", value: "PENDING"},
        {text: "Отменено", value: "CANCELED"},
      ],
    };
  },
  computed: {
    statusAsArray() {
      return [this.status];
    },
  },
  created() {
    if (this.includeNone) {
      this.availableStatuses.unshift({text: "Не выбрано", value: ""});
    }
  },
  methods: {
    onSelectChange(statuses: string[]) {
      this.$emit('update:status', statuses[0] || '');
      if (this.updateSearchInput) {
        this.updateSearchInput();
      }
    },
  },
});
</script>
