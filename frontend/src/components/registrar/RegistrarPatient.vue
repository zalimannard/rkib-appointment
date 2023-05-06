<template>
  <div class="table-container">
    <v-dialog v-model="isDialogForAddingPersonActive" max-width="600">
      <v-card
        rounded="lg"
        width="700">
        <v-form
          v-model="isFormOfAddingPatientCorrect"
        >
          <v-card-title class="text-center dialog-title">
            Создание пациента
          </v-card-title>

          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" md="4" sm="6">
                  <v-text-field
                    v-model="lastNameForNewPatient"
                    :rules="[rules.required]"
                    variant="outlined"
                  >
                    <template #label>
                      <div>
                        Фамилия <span style="color: red">*</span>
                      </div>
                    </template>
                  </v-text-field>
                </v-col>

                <v-col cols="12" md="4" sm="6">
                  <v-text-field
                    v-model="firstNameForNewPatient"
                    :rules="[rules.required]"
                    variant="outlined"
                  >
                    <template #label>
                      <div>
                        Имя <span style="color: red">*</span>
                      </div>
                    </template>
                  </v-text-field>
                </v-col>

                <v-col cols="12" md="4" sm="6">
                  <v-text-field
                    v-model="patronymicForNewPatient"
                    label="Отчество"
                    variant="outlined"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="4" sm="6">
                  <v-text-field
                    v-model="birthdateForNewPatient"
                    :rules="[rules.birthdateRules]"
                    label="Дата рождения"
                    variant="outlined"
                    placeholder="ДД.ММ.ГГГГ"
                    @keydown="handleBackspace"
                    @input="birthdateForNewPatient = birthdateMask(birthdateForNewPatient)"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4" sm="6">
                  <v-text-field
                    v-model="phoneNumberForNewPatient"
                    :rules="[rules.required]"
                    variant="outlined"
                    @input="phoneNumberForNewPatient = phoneMask(phoneNumberForNewPatient)"
                  >
                    <template #label>
                      <div>
                        Телефон <span style="color: red">*</span>
                      </div>
                    </template>
                  </v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="12" sm="12">
                  <v-text-field
                    v-model="addressForNewPatient"
                    label="Адрес"
                    variant="outlined"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="12" sm="12">
                  <v-text-field
                    v-model="occupationForNewPatient"
                    label="Занятость"
                    variant="outlined"
                  ></v-text-field>
                </v-col>
              </v-row>

              <div>
                <span style="color: red">*</span> - обязательные поля
              </div>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              :disabled="!isFormOfAddingPatientCorrect"
              color="green"
              type="submit"
              variant="elevated"
              width="120"
              @click="confirmAddPerson">
              Добавить
            </v-btn>
            <v-btn
              color="red"
              variant="elevated"
              width="120"
              @click="isDialogForAddingPersonActive = false">
              Отмена
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-table class="sticky-header" fixed-footer>
      <thead>
      <tr>
        <th class="text-left text-column" scope="col">
          <v-text-field
            v-model="lastNameFilter"
            class="table-header"
            density="comfortable"
            label="Фамилия"
            variant="outlined"
            @update:modelValue="editFilter">
          </v-text-field>
        </th>
        <th class="text-left text-column" scope="col">
          <v-text-field
            v-model="firstNameFilter"
            class="table-header"
            density="comfortable"
            label="Имя"
            variant="outlined"
            @update:modelValue="editFilter">
          </v-text-field>
        </th>
        <th class="text-left text-column" scope="col">
          <v-text-field
            v-model="patronymicFilter"
            class="table-header"
            density="comfortable"
            label="Отчество"
            variant="outlined"
            @update:modelValue="editFilter">
          </v-text-field>
        </th>
        <th class="text-left birthdate-column" scope="col">
          <v-text-field
            v-model="birthdateFilter"
            class="table-header"
            density="comfortable"
            label="Дата рождения"
            variant="outlined"
            @update:modelValue="editFilter">
          </v-text-field>
        </th>
        <th class="text-left phone-number-column" scope="col">
          <v-text-field
            v-model="phoneNumberFilter"
            class="table-header"
            density="comfortable"
            label="Телефон"
            variant="outlined"
            @update:modelValue="editFilter">
          </v-text-field>
        </th>
        <th class="align info-column" scope="col">
          <v-btn
            color="green"
            icon="mdi-plus"
            size="x-small"
            variant="elevated"
            @click="addPerson">
          </v-btn>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="(item, index) in filteredPatients"
        :key="item"
        :class="index % 2 === 0 ? 'light-row' : 'dark-row'"
      >
        <td>
          {{ item.lastName }}
        </td>
        <td>
          {{ item.firstName }}
        </td>
        <td>
          {{ item.patronymic }}
        </td>
        <td>
          {{ item.birthdate }}
        </td>
        <td>
          {{ item.phoneNumber }}
        </td>
        <td>
          <v-btn
            color="indigo"
            icon="mdi-account"
            size="x-small"
            variant="elevated">
          </v-btn>
        </td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
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
      phoneNumberFilter: "",

      patients: [],
      filteredPatients: [],

      isDialogForAddingPersonActive: false,
      isFormOfAddingPatientCorrect: false,


      rules: {
        required: value => {
          return !!value || "Поле не должно быть пустым.";
        },
        birthdateRules: value => {
          if (!value) return "Поле не должно быть пустым.";
          const pattern = /^(\d{2})\.(\d{2})\.(\d{4})$/;
          if (!pattern.test(value)) return "Неверный формат даты (дд.мм.гггг).";
          const [, day, month, year] = value.match(pattern);
          const date = new Date(year, month - 1, day);
          if (
            date.getFullYear() !== parseInt(year) ||
            date.getMonth() !== parseInt(month) - 1 ||
            date.getDate() !== parseInt(day)
          ) {
            return "Некорректная дата.";
          }
          return true;
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
        return {
          lastName: patient.person.lastName,
          firstName: patient.person.firstName,
          patronymic: patient.person.patronymic,
          birthdate: patient.birthdate,
          phoneNumber: patient.phoneNumber
        };
      });
      this.filteredPatients = this.patients;
    } catch (error) {
      console.error("Ошибка при получении данных:", error);
    }
  },
  methods: {
    async editFilter() {
      this.filteredPatients = [];
      this.patients.forEach(value => {
        let isFit = true;

        if (((value.lastName == null)) && (this.lastNameFilter !== "")) {
          isFit = false;
        } else if ((value.lastName !== null) && (value.lastName.toLowerCase().indexOf(this.lastNameFilter.toLowerCase()) === -1)) {
          isFit = false;
        }

        if (((value.firstName == null)) && (this.firstNameFilter !== "")) {
          isFit = false;
        } else if ((value.firstName !== null) && (value.firstName.toLowerCase().indexOf(this.firstNameFilter.toLowerCase()) === -1)) {
          isFit = false;
        }

        if (((value.patronymic == null)) && (this.patronymicFilter !== "")) {
          isFit = false;
        } else if ((value.patronymic !== null) && (value.patronymic.toLowerCase().indexOf(this.patronymicFilter.toLowerCase()) === -1)) {
          isFit = false;
        }

        if (((value.birthdate == null)) && (this.birthdateFilter !== "")) {
          isFit = false;
        } else if ((value.birthdate !== null) && (value.birthdate.toLowerCase().indexOf(this.birthdateFilter.toLowerCase()) === -1)) {
          isFit = false;
        }

        if (((value.phoneNumber == null)) && (this.phoneNumberFilter !== "")) {
          isFit = false;
        } else if ((value.phoneNumber !== null) && (value.phoneNumber.toLowerCase().indexOf(this.phoneNumberFilter.toLowerCase()) === -1)) {
          isFit = false;
        }

        if (isFit) {
          this.filteredPatients.push(value);
        }
      });
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
    },
    confirmAddPerson() {
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

      // Добавляем точку после второй и пятой цифры, если длина значения равна 2 или 5
      if (numValue.length === 0 && (formattedValue.length === 2 || formattedValue.length === 5)) {
        formattedValue += ".";
      }
      return formattedValue;
    },
    handleBackspace(event) {
      if (event.key === "Backspace" && this.birthdateForNewPatient.slice(-1) === ".") {
        event.preventDefault();
        this.birthdateForNewPatient = this.birthdateForNewPatient.slice(0, -2);
      }
    },
    phoneMask(value) {
      if (!value) return "+7";
      const numValue = value.replace(/\D+/g, "").split("").filter((_, i) => i > 0);
      const mask = ["(", "#", "#", "#", ")", "#", "#", "#", "-", "#", "#", "-", "#", "#"];

      let formattedValue = "+7";
      let index = 0;
      for (const char of mask) {
        if (!numValue.length) break;

        if (char === "#") {
          formattedValue += numValue.shift();
          index++;
        } else {
          formattedValue += char;
          index++;
        }
      }
      return formattedValue;
    }
  }
};
</script>

<style scoped>
.table-container {
    width: 60%;
    margin: 5vh auto 0;
    border: 1px solid #ccc;
    border-radius: 5pt;
}

.table-header {
    margin-top: 2vh;
}

.dialog-title {
    margin-top: 2vh;
    font-size: 20pt;
}

.text-column {
    width: 20%;
}

.birthdate-column {
    width: 17%;
}

.phone-number-column {
    width: 17%;
}

.info-column {
    width: 6%;
}

.sticky-header thead {
    position: sticky;
    top: 0;
    background-color: white;
    z-index: 100;
}

.light-row {
    background-color: #FAFAFA;
}

.dark-row {
    background-color: #EEEEEE;
}
</style>