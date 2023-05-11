<template>
  <create-patient-dialog
    v-model="isDialogForAddingPersonActive"
  />

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
                :handle-backspace="backspaceHandlers.handleBackspaceForDate"
                :mask="masks.dateMask"
                class="table-header"
                label="Дата рождения"
                placeholder="ДД.ММ.ГГГГ"
                @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <CustomTextField
                v-model="phoneNumberFilter"
                :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
                :mask="masks.phoneMask"
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
import { dateRule, phoneRule, requiredRule } from "@/rules";
import { dateMask, phoneMask } from "@/masks";
import { handleBackspaceForDate, handleBackspaceForPhoneNumber } from "@/backspaceHandlers";

export default {
  components: { CreatePatientDialog, CustomButton, CustomTextField },
  data() {
    return {
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
        requiredRule,
        dateRule,
        phoneRule
      },
      masks: {
        dateMask,
        phoneMask
      },
      backspaceHandlers: {
        handleBackspaceForDate,
        handleBackspaceForPhoneNumber
      },
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
          phoneNumber: this.masks.phoneMask(patient.phoneNumber)
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
    addPerson() {
      this.isDialogForAddingPersonActive = true;
    },
    clearFilter() {
      this.lastNameFilter = "";
      this.firstNameFilter = "";
      this.patronymicFilter = "";
      this.birthdateFilter = "";
      this.phoneNumberFilter = "+7(";
      this.editFilter();
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
