<template>
  <v-dialog
    v-model="internalValue"
    width="700"
  >
    <v-card variant="flat">
      <v-card-title class="mb-2">
        {{ title }}
      </v-card-title>

      <v-card-text>
        <v-form>
          <slot>

          </slot>
          <v-col class="pl-0 pr-0">
            <v-row>
              <v-col class="pt-0">
                <custom-button
                  block
                  size="default"
                  type="confirm"
                >
                  {{ okButtonText }}
                </custom-button>
              </v-col>
              <v-col class="pt-0">
                <custom-button
                  block
                  size="default"
                  type="cancel"
                >
                  {{ cancelButtonText }}
                </custom-button>
              </v-col>
            </v-row>
          </v-col>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import CustomButton from "@/components/custom/button/CustomButton.vue";

export default {
  components: { CustomButton },
  props: {
    value: Boolean,
    person: Object,
    patient: Object,
    title: {
      type: String,
      default: "BaseDialogTitle"
    },
    okButtonText: {
      type: String,
      default: "Применить"
    },
    cancelButtonText: {
      type: String,
      default: "Отмена"
    }
  },
  data() {
    return {
      valid: true
    };
  },
  computed: {
    internalValue: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      }
    }
  }
};
</script>

<style scoped>
.v-card-title {
    padding-top: 8pt;
    padding-bottom: 8pt;
    background-color: #3F51B5;
    font-size: 16pt;
    color: #FFFFFF;
    text-align: center;
}
</style>