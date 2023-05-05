<template>
  <div class="table-container">
    <v-table class="sticky-header" fixed-footer>
      <thead>
      <tr>
        <th class="text-left text-column">
          <v-text-field
            v-model="lastNameFilter"
            class="table-header"
            density="comfortable"
            label="Фамилия"
            variant="outlined"
            @update:modelValue="onEditFilter">
          </v-text-field>
        </th>
        <th class="text-left text-column">
          <v-text-field
            v-model="firstNameFilter"
            class="table-header"
            density="comfortable"
            label="Имя"
            variant="outlined"
            @update:modelValue="onEditFilter">
          </v-text-field>
        </th>
        <th class="text-left text-column">
          <v-text-field
            v-model="patronymicFilter"
            class="table-header"
            density="comfortable"
            label="Отчество"
            variant="outlined"
            @update:modelValue="onEditFilter">
          </v-text-field>
        </th>
        <th class="text-left birthdate-column">
          <v-text-field
            v-model="birthdateFilter"
            class="table-header"
            density="comfortable"
            label="Дата рождения"
            variant="outlined"
            @update:modelValue="onEditFilter">
          </v-text-field>
        </th>
        <th class="text-left phone-number-column">
          <v-text-field
            v-model="phoneNumberFilter"
            class="table-header"
            density="comfortable"
            label="Телефон"
            variant="outlined"
            @update:modelValue="onEditFilter">
          </v-text-field>
        </th>
        <th class="align info-column">
          <v-btn
            color="green"
            density="comfortable"
            icon="mdi-plus"
            variant="elevated"></v-btn>
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
      lastNameFilter: "",
      firstNameFilter: "",
      patronymicFilter: "",
      birthdateFilter: "",
      phoneNumberFilter: "",
      patients: [],
      filteredPatients: []
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
    async onEditFilter() {
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