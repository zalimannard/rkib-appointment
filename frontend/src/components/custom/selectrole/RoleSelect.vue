<template>
  <v-select
      :density="computedDensity"
      :items="availableRoles"
      :multiple="multiple"
      :value="selectedRoles"
      item-title="text"
      item-value="value"
      label="Роль"
      persistent-hint
      return-object
      variant="outlined"
      @update:modelValue="onSelectChange"
  ></v-select>
</template>

<script>
const validDensity = ["default", "comfortable", "compact"];
const defaultDensity = "default";

export default {
  name: 'RoleSelect',
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
      default: defaultDensity
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
    onSelectChange(value) {
      let roles = Array.isArray(value) ? value : [value];
      this.$emit('update:roles', roles);
      this.updateSearchInput();
    },
  },
  computed: {
    computedDensity() {
      return validDensity.includes(this.density) ? this.density : defaultDensity;
    },
    selectedRoles() {
      if (!this.roles || !Array.isArray(this.roles)) {
        return [];
      }
      return this.roles.map(role => {
        if (role.value) {
          return this.availableRoles.find(availRole => availRole.value === role.value)
        }
        return null;
      });
    },
  }
};
</script>

<style scoped>

</style>
