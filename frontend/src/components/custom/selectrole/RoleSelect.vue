<template>
    <v-select
            v-model="selectedRole"
            :items="availableRoles"
            density="comfortable"
            item-title="key"
            item-value="value"
            label="Роль"
            persistent-hint
            return-object
            variant="outlined"
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
    },
    data() {
        return {
            availableRoles: [
                {key: "Врач", value: "DOCTOR"},
                {key: "Регистратор", value: "REGISTRAR"},
                {key: "Админ", value: "ADMIN"},
            ],
        };
    },
    computed: {
        selectedRole: {
            get() {
                return this.role;
            },
            set(value) {
                this.$emit('update:role', value);
            },
        },
    },
    created() {
        if (this.includePatient) {
            this.availableRoles.push({key: "Пациент", value: "PATIENT"});
        }
    },
};
</script>

<style scoped>

</style>
