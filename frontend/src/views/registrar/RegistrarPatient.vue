<template>
  <create-patient-dialog
      v-model="isDialogForAddingPersonActive"
      :patient="patient"
      :person="person"
      @update:onNewPatient="requestPatients"
  />

  <v-container class="main-container" fluid>
    <v-row>
      <v-col>
        <v-table class="sticky-header table-container" fixed-footer>
          <thead>
          <tr>
            <th class="text-left table-column" scope="col">
              <masked-text-field
                  v-model="person.lastName"
                  capitalize-first-letter
                  class="table-header"
                  label="Фамилия"
                  @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <masked-text-field
                  v-model="person.firstName"
                  capitalize-first-letter
                  class="table-header"
                  label="Имя"
                  @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <masked-text-field
                  v-model="person.patronymic"
                  capitalize-first-letter
                  class="table-header"
                  label="Отчество"
                  @input="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <masked-text-field
                  v-model="patient.birthdate"
                  :handle-backspace="backspaceHandlers.handleBackspaceForDate"
                  :mask="masks.dateMask"
                  class="table-header"
                  label="Дата рождения"
                  placeholder="ДД.ММ.ГГГГ"
                  @mask-valid="editFilter"
              />
            </th>
            <th class="text-left table-column" scope="col">
              <masked-text-field
                  v-model="patient.phoneNumber"
                  :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
                  :mask="masks.phoneMask"
                  class="table-header"
                  label="Телефон"
                  @mask-valid="editFilter"
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
import MaskedTextField from "@/components/textfield/MaskedTextField.vue";
import CustomButton from "@/components/button/CustomButton.vue";
import CreatePatientDialog from "@/components/dialog/CreatePatientDialog.vue";
import {dateRule, phoneRule, requiredRule} from "@/rules";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";

export default {
  components: {CreatePatientDialog, CustomButton, MaskedTextField},
  data() {
    return {
      person: {
        lastName: "",
        firstName: "",
        patronymic: ""
      },
      patient: {
        birthdate: "",
        phoneNumber: "+7(",
        address: "",
        occupation: ""
      },

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
      }
    };
  },
  async created() {
    await this.requestPatients();
  },
  methods: {
    async requestPatients() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/patients", {
          headers: {"Authorization": "Basic " + basicAuth}
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
        await this.editFilter(); // вызовите здесь
      } catch (error) {
        console.error("Ошибка при получении данных:", error);
      }
    },
    async onNewPatient(newData) {
      if (newData.person) {
        this.person = Object.assign({}, this.person, newData.person);
      }
      if (newData.patient) {
        this.patient = Object.assign({}, this.patient, newData.patient);
      }
      await this.requestPatients();
    },
    async editFilter() {
      const checkFilter = (fieldValue, filterValue) => {
        if (!fieldValue && filterValue !== "") return false;
        return !(fieldValue && fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) === -1);
      };

      this.filteredPatients = this.patients.filter(patient => {
        return (
            checkFilter(patient.lastName, this.person.lastName) &&
            checkFilter(patient.firstName, this.person.firstName) &&
            checkFilter(patient.patronymic, this.person.patronymic) &&
            checkFilter(patient.birthdate, this.patient.birthdate) &&
            checkFilter(patient.phoneNumber, this.patient.phoneNumber)
        );
      });
    },
    addPerson() {
      this.isDialogForAddingPersonActive = true;
    },
    clearFilter() {
      this.person = {
        type: "",
        firstName: "",
        patronymic: ""
      };
      this.patient = {
        birthdate: "",
        phoneNumber: "+7(",
        address: "",
        occupation: ""
      };
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
