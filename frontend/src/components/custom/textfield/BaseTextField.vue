<template>
    <v-text-field
            v-model="innerValue"
            :autofocus="autofocus"
            :density="computedDensity"
            :placeholder="placeholder"
            :readonly="readonly"
            :rules="rules"
            v-bind="$attrs"
            variant="outlined"
    >
        <template #label>
            <span>{{ label }}</span>
            <span v-if="requiredAsterisk" class="required-asterisk">*</span>
        </template>
    </v-text-field>
</template>

<script>
const validDensity = ["default", "comfortable", "compact"];
const defaultDensity = "default";

export default {
    inheritAttrs: false,
    props: {
        label: {
            type: String,
            default: ""
        },
        requiredAsterisk: {
            type: Boolean,
            default: false
        },
        placeholder: {
            type: String,
            default: ""
        },
        density: {
            type: String,
            default: defaultDensity
        },
        autofocus: {
            type: Boolean,
            default: false
        },
        readonly: {
            type: Boolean,
            default: false
        },
        rules: {
            type: Array,
            default: () => []
        }
    },
    computed: {
        computedDensity() {
            return this.ensureValidDensity(this.density);
        },
        innerValue: {
            get() {
                return this.$attrs.modelValue;
            },
            set(value) {
                this.$emit("update:modelValue", value);
            }
        }
    },
    methods: {
        ensureValidDensity(type) {
            return validDensity.includes(type) ? type : defaultDensity;
        }
    }
};
</script>

<style>
.required-asterisk {
    color: #F44336;
}
</style>
