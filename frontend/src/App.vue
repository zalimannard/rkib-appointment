<template>
  <the-alert/>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import TheAlert from "@/components/custom/alert/TheAlert.vue";

export default defineComponent({
  name: "App",
  components: {TheAlert},

  data() {
    return {
      //
    };
  }
});
</script>
