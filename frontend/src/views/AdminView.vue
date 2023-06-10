<template>
  <v-app>
    <Navbar/>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Navbar from "@/views/admin/AdminNavbar.vue";
import {defineComponent} from "vue";

export default defineComponent({
  components: {
    Navbar
  }
});
</script>