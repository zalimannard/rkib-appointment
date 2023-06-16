<template>
  <change-password-dialog
      v-model="showDialog"
      :close-dialog="closeDialog"
  >
  </change-password-dialog>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import ChangePasswordDialog from "@/components/dialog/ChangePasswordDialog.vue";

export default defineComponent({
  components: {
    ChangePasswordDialog
  },
  setup() {
    const showDialog = ref(true);

    function closeDialog() {

    }

    return {
      showDialog,
      closeDialog,
    };
  },
});
</script>

<style scoped>

</style>
