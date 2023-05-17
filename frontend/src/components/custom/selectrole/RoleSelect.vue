<template>
    <v-select
            :items="availableRoles"
            :value="selectedRole"
            density="comfortable"
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
        }
    },
    computed: {
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
