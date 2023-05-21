<template>
  <base-select
      :density="density"
      :items="availableRoles"
      :multiple="multiple"
      :onChange="onSelectChange"
      :selected="roles"
      label="Роль"
  ></base-select>
</template>

<script>
import BaseSelect from './BaseSelect.vue'

export default {
  name: 'RoleSelect',
  components: {
    BaseSelect,
  },
  props: {
    roles: {
      type: Array,
      required: true,
    },
    includeNone: {
      type: Boolean,
      default: false,
    },
    includePatient: {
      type: Boolean,
      default: false,
    },
    multiple: {
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
      availableRoles: [
        {text: "Врач", value: "DOCTOR"},
        {text: "Регистратор", value: "REGISTRAR"},
        {text: "Админ", value: "ADMIN"},
      ],
    };
  },
  created() {
    if (this.includeNone) {
      this.availableRoles.reverse();
      this.availableRoles.push({text: "Не выбрано", value: null});
      this.availableRoles.reverse();
    }
    if (this.includePatient) {
      this.availableRoles.push({text: "Пациент", value: "PATIENT"});
    }
  },
  methods: {
    onSelectChange(roles) {
      this.$emit('update:roles', roles);
      this.updateSearchInput();
    },
  },
};
</script>
