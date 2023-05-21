<template>
  <v-select
      :density="computedDensity"
      :items="items"
      :label="label"
      :multiple="multiple"
      :value="selectedItems"
      item-title="text"
      item-value="value"
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
  name: 'BaseSelect',
  props: {
    items: {
      type: Array,
      required: true,
    },
    selected: {
      type: Array,
      required: true,
    },
    multiple: {
      type: Boolean,
      default: false,
    },
    density: {
      type: String,
      default: defaultDensity
    },
    label: {
      type: String,
      default: ''
    },
    onChange: {
      type: Function,
      required: true,
    },
  },
  methods: {
    onSelectChange(value) {
      let selectedItems = Array.isArray(value) ? value : [value];
      this.onChange(selectedItems);
    },
  },
  computed: {
    computedDensity() {
      return validDensity.includes(this.density) ? this.density : defaultDensity;
    },
    selectedItems() {
      if (!this.selected || !Array.isArray(this.selected)) {
        return [];
      }
      return this.selected.map(item => {
        if (item.value) {
          return this.items.find(availableItem => availableItem.value === item.value)
        }
        return null;
      });
    },
  }
};
</script>
