<template>
  <v-select
      :density="computedDensity"
      :items="availableRoles"
      :value="selectedRole"
      item-title="key"
      item-value="value"
      label="Роль"
      persistent-hint
      return-object
      variant="outlined"
      @update:modelValue="updateRole"
  ></v-select>
</template>

<script>
const validDensity = ["default", "comfortable", "compact"];
const defaultDensity = "default";

export default {
  name: 'RoleSelect',
  props: {
    role: {
      type: Object,
      required: true,
    },
    includePatient: {
      type: Boolean,
      default: false,
    },
    fieldWidth: {
      type: Number,
      default: null,
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
        {key: "Не выбрано", value: "NONE"},
        {key: "Врач", value: "DOCTOR"},
        {key: "Регистратор", value: "REGISTRAR"},
        {key: "Админ", value: "ADMIN"},
      ],
    };
  },
  created() {
    if (this.includePatient) {
      this.availableRoles.push({key: "Пациент", value: "PATIENT"});
    }
  },
  methods: {
    updateRole(value) {
      this.selectedRole = value;
    },
    ensureValidDensity(type) {
      return validDensity.includes(type) ? type : defaultDensity;
    }
  },
  computed: {
    computedDensity() {
      return this.ensureValidDensity(this.density);
    },
    selectedRole: {
      get() {
        return this.availableRoles.find(role => role.value === this.role);
      },
      set(value) {
        this.$emit('update:role', value.value);
        this.updateSearchInput();
      },
    },
  }
};
</script>

<style scoped>

</style>
