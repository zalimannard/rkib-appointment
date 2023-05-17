<template>
    <v-fade-transition>
        <v-alert
                v-model="internalValue"
                :class="computedAlertClass"
                :text="alertText"
                :type="computedAlertType"
                rounded="lg"
                variant="elevated"
                width="500"
        />
    </v-fade-transition>
</template>

<script>
const validTypes = ["error", "warning", "info", "success"];
const defaultType = "error";

export default {
    model: {
        prop: "value",
        event: "update:value"
    },
    props: {
        value: {
            type: Boolean,
            default: false
        },
        alertText: {
            type: String,
            default: ""
        },
        alertType: {
            type: String,
            default: defaultType
        }
    },
    data() {
        return {
            internalValue: this.value
        };
    },
    watch: {
        value(newVal) {
            this.internalValue = newVal;
        },
        internalValue(newVal) {
            this.$emit("update:value", newVal);
        }
    },
    computed: {
        computedAlertType() {
            return this.ensureValidType(this.alertType);
        },
        computedAlertClass() {
            return `alert-${this.computedAlertType}`;
        }
    },
    methods: {
        ensureValidType(type) {
            return validTypes.includes(type) ? type : defaultType;
        }
    }
};
</script>

<style scoped>
.v-alert {
    position: fixed;
    top: 10px;
    left: 50%;
    transform: translateX(-50%);
    z-index: 10;
}
</style>
