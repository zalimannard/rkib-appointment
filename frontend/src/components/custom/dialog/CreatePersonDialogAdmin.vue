<template>
  <base-dialog
      v-model="internalValue"
      :close-dialog="closeDialog"
      :ok-dialog="createProcedure"
      ok-button-text="Создать"
      title="Создание нового человека"
  >
    <v-row>
      <v-col>
        <masked-text-field
            v-model="person.lastName"
            :rules="rules.requiredRule"
            capitalize-first-letter
            label="Фамилия"
            required-asterisk
        />
      </v-col>
      <v-col>
        <masked-text-field
            v-model="person.firstName"
            :rules="rules.requiredRule"
            capitalize-first-letter
            label="Имя"
            required-asterisk
        />
      </v-col>
      <v-col>
        <masked-text-field
            v-model="person.patronymic"
            :rules="rules.requiredRule"
            capitalize-first-letter
            label="Отчество"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col>
      </v-col>
    </v-row>
  </base-dialog>
</template>

<script>
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import {requiredRule} from "@/rules";
import BaseDialog from "@/components/custom/dialog/BaseDialog.vue";
import axios from "axios";
import RoleSelect from "@/components/custom/selectrole/RoleSelect.vue";

export default {
  components: {RoleSelect, BaseDialog, MaskedTextField},
  props: {
    value: Boolean,
    searchPerson: {
      lastName: "",
      firstName: "",
      patronymic: "",
      roles: []
    },
    onCreateEntity: {
      type: Function,
      required: true
    },
    closeDialog: {
      type: Function,
      required: true
    }
  },
  data() {
    return {
      person: {
        lastName: this.searchPerson.lastName,
        firstName: this.searchPerson.firstName,
        patronymic: this.searchPerson.patronymic,
        roles: this.searchPerson.roles
      },
      rules: {
        requiredRule
      }
    };
  },
  methods: {
    createProcedure() {
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/people",
        headers: {"Authorization": "Basic " + basicAuth},
        data: {
          // TODO: Потом убрать username и password отсюда вообще
          username: (Math.random() + 1).toString(36).substring(7),
          password: "password",
          lastName: this.person.lastName,
          firstName: this.person.firstName,
          patronymic: this.person.patronymic
        }
      }).then((response) => {
        console.error("Создан человек:", response);
        this.onCreateEntity();
      }).catch((error) => {
        console.error("Ошибка при добавлении человека:", error);
      });
      this.$emit("updateSearchInput", this.person.inputName);
    }
  },
  computed: {
    internalValue: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit("input", val);
        if (!val) {
          this.$emit("updateSearchInput", this.person.inputName);
        }
      }
    }
  },
  watch: {
    searchInput(newVal) {
      this.person.inputName = newVal;
    }
  }
};
</script>
