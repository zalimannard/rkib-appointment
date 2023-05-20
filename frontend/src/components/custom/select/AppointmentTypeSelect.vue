<template>
  <base-select
      :density="density"
      :items="availableStatuses"
      :onChange="onSelectChange"
      :selected="status"
      label="Статус"
  ></base-select>
</template>

<script>
import BaseSelect from './BaseSelect.vue'

export default {
  name: 'AppointmentStatus',
  components: {
    BaseSelect,
  },
  props: {
    status: {
      type: Array,
      required: true,
    },
    includeNone: {
      type: Boolean,
      default: false,
    },
    density: {
      type: String,
      default: "default"
    },
    updateSearchInput: {
      type: Function,
      required: true,
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
  created() {
    if (this.includeNone) {
      this.availableStatuses.reverse();
      this.availableStatuses.push({text: "Не выбрано", value: null});
      this.availableStatuses.reverse();
    }
  },
  methods: {
    onSelectChange(statuses) {
      this.$emit('update:status', statuses);
      this.updateSearchInput();
    },
  },
};
</script>
