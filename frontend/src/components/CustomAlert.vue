<template>
  <v-fade-transition>
    <v-alert
      v-show="showAlert"
      :color="alertColor"
      :text="alertText"
      :type="type"
      class="alert-style"
      location="top"
      position="fixed"
      rounded="lg"
      variant="elevated"
      width="500"
    ></v-alert>
  </v-fade-transition>
</template>

<script>
export default {
  props: {
    alertText: String,
    showAlert: Boolean,
    type: {
      type: String,
      default: "error"
    }
  },
  computed: {
    alertColor() {
      switch (this.type) {
        case "error":
          return "red";
        case "success":
          return "green";
        case "warning":
          return "orange";
        case "info":
          return "indigo";
        default:
          return "indigo";
      }
    }
  }
};
</script>

<style scoped>
.alert-style {
    margin-top: 10pt;
}
</style>