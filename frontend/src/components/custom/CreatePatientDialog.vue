<template>
  <v-dialog
    v-model="internalValue"
    max-width="700px"
  >
    <v-card>
      <v-card-title>
        СОЗДАНИЕ ПАЦИЕНТА
      </v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-col class="pl-0 pr-0 pt-0">
            <v-row>
              <v-col class="pt-5 pb-0" cols="4">
                <masked-text-field
                  v-model="person.lastName"
                  :rules="rules.requiredRule"
                  capitalize-first-letter
                  label="Фамилия"
                  required-asterisk
                />
              </v-col>
              <v-col class="pt-5 pb-0" cols="4">
                <masked-text-field
                  v-model="person.firstName"
                  :rules="rules.requiredRule"
                  capitalize-first-letter
                  label="Имя"
                  required-asterisk
                />
              </v-col>
              <v-col class="pt-5 pb-0" cols="4">
                <masked-text-field
                  v-model="person.patronymic"
                  capitalize-first-letter
                  label="Отчество"
                />
              </v-col>
            </v-row>

            <v-row>
              <v-col class="pt-0 pb-0" cols="4">
                <masked-text-field
                  v-model="patient.birthdate"
                  :mask="masks.dateMask"
                  :rules="rules.dateRule"
                  label="Дата рождения"
                  placeholder="ДД.ММ.ГГГГ"
                />
              </v-col>
              <v-col class="pt-0 pb-0" cols="4">
                <masked-text-field
                  v-model="patient.phoneNumber"
                  :mask="masks.phoneMask"
                  :rules="rules.phoneRule"
                  label="Телефон"
                  required-asterisk
                />
              </v-col>
            </v-row>

            <v-row>
              <v-col class="pt-0 pb-0" cols="12">
                <masked-text-field
                  v-model="patient.address"
                  label="Адрес"
                />
              </v-col>
            </v-row>

            <v-row>
              <v-col class="pt-0 pb-0" cols="12">
                <masked-text-field
                  v-model="patient.occupation"
                  capitalize-first-letter
                  label="Занятость"
                />
              </v-col>
            </v-row>

            <v-row>
              <v-col class="pt-0">
                <custom-button
                  block
                  size="default"
                  type="confirm"
                  @custom-click="submit"
                >
                  Создать
                </custom-button>
              </v-col>
              <v-col class="pt-0">
                <custom-button
                  block
                  size="default"
                  type="cancel"
                  @click="internalValue = false"
                >
                  Отмена
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
import axios from "axios";
import CustomButton from "@/components/custom/button/CustomButton.vue";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";

import { dateRule, phoneRule, requiredRule } from "@/rules.ts";
import { dateMask, phoneMask } from "@/masks.ts";

export default {
  components: { MaskedTextField, CustomButton },
  props: {
    is: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      internalValue: this.value,
      valid: true,

      rules: {
        requiredRule,
        dateRule,
        phoneRule
      },
      masks: {
        dateMask,
        phoneMask
      },

      person: {
        lastName: "",
        firstName: "",
        patronymic: ""
      },
      patient: {
        birthdate: "",
        phoneNumber: "",
        address: "",
        occupation: ""
      }
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
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        let basicAuth = localStorage.getItem("auth");
        axios({
          method: "post",
          url: import.meta.env.VITE_API_URL + "/api/v1/people",
          headers: { "Authorization": "Basic " + basicAuth },
          data: {
            // TODO: Потом убрать username и password отсюда вообще
            username: (Math.random() + 1).toString(36).substring(7),
            password: "password",
            lastName: this.person.lastName,
            firstName: this.person.firstName,
            patronymic: this.person.patronymic
          }
        }).then((response) => {
          let personId = response.data.id;
          let phoneNumber = this.patient.phoneNumber
            .replaceAll("(", "")
            .replaceAll(")", "")
            .replaceAll("-", "");
          let birthdate = null;
          if (this.patient.birthdate) {
            let dateParts = this.patient.birthdate.split(".");
            if (dateParts.length === 3) {
              birthdate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
            }
          }
          console.log(personId);
          console.log(phoneNumber);
          console.log(birthdate);
          axios({
            method: "post",
            url: import.meta.env.VITE_API_URL + "/api/v1/patients",
            headers: { "Authorization": "Basic " + basicAuth },
            data: {
              personId: personId,
              phoneNumber: phoneNumber,
              birthdate: birthdate,
              address: this.patient.address,
              occupation: this.patient.occupation
            }
          }).catch((error) => {
            console.error("Ошибка при добавлении пациента:", error);
          });
        }).catch((error) => {
          console.error("Ошибка при добавлении человека:", error);
        });
      }
    }
  }
};
</script>

<style>
.v-card-title {
    padding-top: 1vh;
    padding-bottom: 1vh;
    background-color: #3F51B5;
    font-size: 14pt;
    color: #FFFFFF;
    text-align: center;
}
</style>
