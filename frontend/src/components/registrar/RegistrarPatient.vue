<template>
  <create-patient-dialog
    v-model="isDialogForAddingPersonActive"
  />

  <!--  <v-dialog v-model="isDialogForAddingPersonActive" max-width="720">-->
  <!--    <v-card-->
  <!--      rounded="lg">-->
  <!--      <v-form v-model="isFormOfAddingPatientCorrect">-->
  <!--        <v-card-title class="text-center dialog-title">-->
  <!--          СОЗДАНИЕ ПАЦИЕНТА-->
  <!--        </v-card-title>-->

  <!--        <v-card-text>-->
  <!--          <v-container class="pb-0">-->
  <!--            <v-row>-->
  <!--              <v-col class="pt-0 pb-0" cols="4">-->
  <!--                <CustomTextField-->
  <!--                  v-model="lastNameForNewPatient"-->
  <!--                  :rules="[rules.required]"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Фамилия"-->
  <!--                  requiredMark-->
  <!--                  validate-on-blur-->
  <!--                />-->
  <!--              </v-col>-->

  <!--              <v-col class="pt-0 pb-0" cols="4">-->
  <!--                <CustomTextField-->
  <!--                  v-model="firstNameForNewPatient"-->
  <!--                  :rules="[rules.required]"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Имя"-->
  <!--                  requiredMark-->
  <!--                  validate-on-blur-->
  <!--                />-->
  <!--              </v-col>-->

  <!--              <v-col class="pt-0 pb-0" cols="4">-->
  <!--                <CustomTextField-->
  <!--                  v-model="patronymicForNewPatient"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Отчество"-->
  <!--                  validate-on-blur-->
  <!--                />-->
  <!--              </v-col>-->
  <!--            </v-row>-->

  <!--            <v-row>-->
  <!--              <v-col class="pt-0 pb-0" cols="4">-->
  <!--                <CustomTextField-->
  <!--                  v-model="birthdateForNewPatient"-->
  <!--                  :handle-backspace="handleBackspaceForDate"-->
  <!--                  :mask="birthdateMask"-->
  <!--                  :rules="[rules.birthdateRules]"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Дата рождения"-->
  <!--                  placeholder="ДД.ММ.ГГГГ"-->
  <!--                  validate-on-blur-->
  <!--                />-->
  <!--              </v-col>-->

  <!--              <v-col class="pt-0 pb-0" cols="4">-->
  <!--                <CustomTextField-->
  <!--                  v-model="phoneNumberForNewPatient"-->
  <!--                  :handle-backspace="handleBackspaceForPhoneNumber"-->
  <!--                  :mask="phoneMask"-->
  <!--                  :rules="[rules.phoneRules]"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Телефон"-->
  <!--                  required-mark-->
  <!--                  validate-on-blur-->
  <!--                />-->
  <!--              </v-col>-->
  <!--            </v-row>-->

  <!--            <v-row>-->
  <!--              <v-col class="pt-0 pb-0" cols="12">-->
  <!--                <CustomTextField-->
  <!--                  v-model="addressForNewPatient"-->
  <!--                  label="Адрес"-->
  <!--                />-->
  <!--              </v-col>-->
  <!--            </v-row>-->

  <!--            <v-row>-->
  <!--              <v-col class="pt-0 pb-0" cols="12">-->
  <!--                <CustomTextField-->
  <!--                  v-model="occupationForNewPatient"-->
  <!--                  capitalize-first-letter-->
  <!--                  label="Занятость"-->
  <!--                />-->
  <!--              </v-col>-->
  <!--            </v-row>-->
  <!--          </v-container>-->
  <!--        </v-card-text>-->

  <!--        <v-card-actions>-->
  <!--          <v-spacer></v-spacer>-->
  <!--          <custom-button-->
  <!--            :disabled="!isFormOfAddingPatientCorrect"-->
  <!--            size="comfortable"-->
  <!--            type="confirm"-->
  <!--            width="120"-->
  <!--            @click="confirmAddPerson"-->
  <!--          >-->
  <!--            Добавить-->
  <!--          </custom-button>-->
  <!--          <custom-button-->
  <!--            size="comfortable"-->
  <!--            type="cancel"-->
  <!--            width="120"-->
  <!--            @click="isDialogForAddingPersonActive = false"-->
  <!--          >-->
  <!--            Отмена-->
  <!--          </custom-button>-->
  <!--        </v-card-actions>-->
  <!--      </v-form>-->
  <!--    </v-card>-->
  <!--  </v-dialog>-->

  <v-container class="main-container" fluid>
    <v-row>
      <v-col>
        <v-table class="sticky-header table-container" fixed-footer>
          <thead>
          <tr>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="lastNameFilter"
                capitalize-first-letter
                class="table-header"
                label="Фамилия"
                @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="firstNameFilter"
                capitalize-first-letter
                class="table-header"
                label="Имя"
                @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="patronymicFilter"
                capitalize-first-letter
                class="table-header"
                label="Отчество"
                @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="birthdateFilter"
                :handle-backspace="handleBackspaceForDate"
                :mask="birthdateMask"
                class="table-header"
                label="Дата рождения"
                placeholder="ДД.ММ.ГГГГ"
                @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="phoneNumberFilter"
                :handle-backspace="handleBackspaceForPhoneNumber"
                :mask="phoneMask"
                class="table-header"
                label="Телефон"
                @input="editFilter"
              />
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
            v-for="(item, index) in filteredPatients"
            :key="item"
            :class="index % 2 === 0 ? 'light-row' : 'dark-row'"
          >
            <td>{{ item.lastName }}</td>
            <td>{{ item.firstName }}</td>
            <td>{{ item.patronymic }}</td>
            <td>{{ item.birthdate }}</td>
            <td>{{ item.phoneNumber }}</td>
          </tr>
          </tbody>
        </v-table>
      </v-col>
      <v-col class="d-flex align-start flex-column" cols="auto">
        <custom-button
          class="mb-4"
          size="compact"
          type="confirm"
          width="200"
          @click="addPerson"
        >
          Создать пациента
        </custom-button>
        <custom-button
          size="compact"
          type="regular"
          width="200"
          @click="clearFilter"
        >
          Убрать фильтры
        </custom-button>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import CustomTextField from "@/components/custom/textfield/MaskedTextField.vue";
import CustomButton from "@/components/custom/button/CustomButton.vue";
import CreatePatientDialog from "@/components/custom/CreatePatientDialog.vue";

export default {
  components: { CreatePatientDialog, CustomButton, CustomTextField },
  data() {
    return {
      lastNameForNewPatient: "",
      firstNameForNewPatient: "",
      patronymicForNewPatient: "",
      birthdateForNewPatient: "",
      phoneNumberForNewPatient: "",
      addressForNewPatient: "",
      occupationForNewPatient: "",

      lastNameFilter: "",
      firstNameFilter: "",
      patronymicFilter: "",
      birthdateFilter: "",
      phoneNumberFilter: "+7(",

      patients: [],
      filteredPatients: [],

      isDialogForAddingPersonActive: false,
      isFormOfAddingPatientCorrect: false,

      rules: {
        required: value => {
          return !!value || "Не должно быть пустым";
        }
      }
    };
  },
  async created() {
    try {
      let basicAuth = localStorage.getItem("auth");
      const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/patients", {
        headers: { "Authorization": "Basic " + basicAuth }
      });
      this.patients = response.data.map(patient => {
        let dateParts = [];
        if (patient.birthdate) {
          dateParts = patient.birthdate.split("-");
        }
        return {
          lastName: patient.person.lastName,
          firstName: patient.person.firstName,
          patronymic: patient.person.patronymic,
          birthdate: dateParts.length === 3 ? `${dateParts[2]}.${dateParts[1]}.${dateParts[0]}` : "",
          phoneNumber: this.phoneMask(patient.phoneNumber) // Здесь мы применяем phoneMask к полученному номеру телефона
        };
      });
      this.filteredPatients = this.patients;
    } catch (error) {
      console.error("Ошибка при получении данных:", error);
    }
  },
  methods: {
    async editFilter() {
      const checkFilter = (fieldValue, filterValue) => {
        if (!fieldValue && filterValue !== "") return false;
        return !(fieldValue && fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) === -1);
      };

      this.filteredPatients = this.patients.filter(patient => {
        return (
          checkFilter(patient.lastName, this.lastNameFilter) &&
          checkFilter(patient.firstName, this.firstNameFilter) &&
          checkFilter(patient.patronymic, this.patronymicFilter) &&
          checkFilter(patient.birthdate, this.birthdateFilter) &&
          checkFilter(patient.phoneNumber, this.phoneNumberFilter)
        );
      });
    },
    validateFormFields() {
      this.$refs.lastNameForNewPatient.validate();
      this.$refs.firstNameForNewPatient.validate();
      this.$refs.birthdateForNewPatient.validate();
      this.$refs.phoneNumberForNewPatient.validate();
    },
    addPerson() {
      this.isDialogForAddingPersonActive = true;
      this.lastNameForNewPatient = this.lastNameFilter;
      this.firstNameForNewPatient = this.firstNameFilter;
      this.patronymicForNewPatient = this.patronymicFilter;
      this.birthdateForNewPatient = this.birthdateFilter;
      this.phoneNumberForNewPatient = this.phoneNumberFilter;
      this.addressForNewPatient = "";
      this.occupationForNewPatient = "";
      this.$nextTick(() => {
        this.validateFormFields();
      });
    },
    clearFilter() {
      this.lastNameFilter = "";
      this.firstNameFilter = "";
      this.patronymicFilter = "";
      this.birthdateFilter = "";
      this.phoneNumberFilter = "";
      this.editFilter();
    },
    confirmAddPerson() {
      event.preventDefault();
      let basicAuth = localStorage.getItem("auth");
      axios({
        method: "post",
        url: import.meta.env.VITE_API_URL + "/api/v1/people",
        headers: { "Authorization": "Basic " + basicAuth },
        data: {
          // TODO: Потом убрать username и password отсюда вообще
          username: (Math.random() + 1).toString(36).substring(7),
          password: "password",
          lastName: this.lastNameForNewPatient,
          firstName: this.firstNameForNewPatient,
          patronymic: this.patronymicForNewPatient
        }
      }).then((response) => {
        let personId = response.data.id;
        let phoneNumber = this.phoneNumberForNewPatient
          .replaceAll("(", "")
          .replaceAll(")", "")
          .replaceAll("-", "");
        let dateParts = this.birthdateForNewPatient.split(".");
        let birthdate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
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
            address: this.addressForNewPatient,
            occupation: this.occupationForNewPatient
          }
        }).catch((error) => {
          console.error("Ошибка при добавлении пациента:", error);
        });
      }).catch((error) => {
        console.error("Ошибка при добавлении человека:", error);
      });
      this.isDialogForAddingPersonActive = false;
    },
    birthdateMask(value) {
      if (!value) return "";
      const numValue = value.replace(/\D+/g, "").split("");
      const mask = ["#", "#", ".", "#", "#", ".", "#", "#", "#", "#"];

      let formattedValue = "";
      let index = 0;
      for (const char of mask) {
        if (!numValue.length) break;

        if (char === "#") {
          formattedValue += numValue.shift();
          index++;
        } else if (index === 2 || index === 5) {
          formattedValue += char;
          index++;
        }
      }

      if (numValue.length === 0 && (formattedValue.length === 2 || formattedValue.length === 5)) {
        formattedValue += ".";
      }
      return formattedValue;
    },
    phoneMask(value) {
      if (!value) return "+7(";
      const numValue = value.replace(/\D+/g, "").split("").filter((_, i) => i > 0);
      const mask = ["#", "#", "#", ")", "#", "#", "#", "-", "#", "#", "-", "#", "#"];

      let formattedValue = "+7(";
      for (const char of mask) {
        if (!numValue.length) break;

        if (char === "#") {
          formattedValue += numValue.shift();
        } else {
          formattedValue += char;
        }
      }

      if (numValue.length === 0 && formattedValue.length === 6) {
        formattedValue += ")";
      }

      if (numValue.length === 0 && (formattedValue.length === 10 || formattedValue.length === 13)) {
        formattedValue += "-";
      }

      return formattedValue;
    },
    handleBackspaceForDate(event) {
      if (event.key === "Backspace" && event.target.value.slice(-1) === ".") {
        event.preventDefault();
        event.target.value = event.target.value.slice(0, -2);
      }
    },
    handleBackspaceForPhoneNumber(event) {
      if (event.key === "Backspace" && (event.target.value.slice(-1) === "-" || event.target.value.slice(-1) === ")")) {
        event.preventDefault();
        event.target.value = event.target.value.slice(0, -2);
      }
    }
  }
};
</script>

<style scoped>

.main-container {
    width: 1452px;
    margin: 5vh auto 0;
}

.table-container {
    border: 1px solid #ccc;
    border-radius: 5pt;
}

.table-header {
    margin-top: 2vh;
}

.dialog-title {
    padding-top: 1vh;
    padding-bottom: 1vh;
    margin-bottom: 4vh;
    background-color: #3F51B5;
    font-size: 14pt;
    color: #FFFFFF;
}

.table-column {
    width: 20%;
}

.sticky-header thead {
    position: sticky;
    top: 0;
    background-color: white;
    z-index: 100;
}

.light-row {
    background-color: #FFFFFF;
}

.dark-row {
    background-color: #E8EAF6;
}
</style>
