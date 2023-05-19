<template>
  <custom-table>
    <template v-slot:header>
      <thead>
      <tr>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.lastName"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Фамилия"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.firstName"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Имя"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.patronymic"
              capitalize-first-letter
              class="header-cell"
              density="comfortable"
              label="Отчество"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.birthdate"
              :handle-backspace="backspaceHandlers.handleBackspaceForDate"
              :mask="masks.dateMask"
              class="header-cell"
              density="comfortable"
              label="Дата рождения"
              placeholder="ДД.ММ.ГГГГ"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.phoneNumber"
              :handle-backspace="backspaceHandlers.handleBackspaceForPhoneNumber"
              :mask="masks.phoneMask"
              class="header-cell"
              density="comfortable"
              label="Телефон"
              @input="updateSearch"
          />
        </th>
        <th class="text-left" scope="col">
          <masked-text-field
              v-model="person.username"
              class="header-cell"
              density="comfortable"
              label="Логин"
              @input="updateSearch"
          />
        </th>
        <th class="text-left role-field" scope="col">
          <role-select
              :roles="[person.roles]"
              :update-search-input="updateSearch"
              class="header-cell"
              density="comfortable"
              include-none
              include-patient
              @update:roles="person.roles = $event"
          />
        </th>
      </tr>
      </thead>
    </template>
    <template v-slot:body>
      <tbody>
      <tr
          v-for="(item, index) in filteredPeople"
          :key="item"
          :class="{ 'light-row': index % 2 === 0, 'dark-row': index % 2 !== 0 }"
          :style="{ cursor: handleClick ? 'pointer' : 'default' }"
          @click="handleClick(item)"
      >
        <td>{{ item.lastName }}</td>
        <td>{{ item.firstName }}</td>
        <td>{{ item.patronymic }}</td>
        <td>{{ item.birthdate }}</td>
        <td>{{ item.phoneNumber }}</td>
        <td>{{ item.username }}</td>
        <td>{{ calcRoles(item) }}</td>
      </tr>
      </tbody>
    </template>
  </custom-table>
</template>

<script>
import {dateRule, phoneRule, requiredRule} from "@/rules";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/custom/table/CustomTable.vue";
import RoleSelect from "@/components/custom/selectrole/RoleSelect.vue";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";
import {fromIsoToDefault} from "@/utils";
import {setAlertShow, setAlertText, setAlertType} from "@/components/custom/alert/AlertState";

export default {
  components: {
    RoleSelect,
    CustomTable,
    MaskedTextField
  },
  props: {
    searchInput: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      localSearchLastName: this.searchInput,
      person: {
        id: "",
        username: "",
        lastName: "",
        firstName: "",
        patronymic: "",
        birthdate: "",
        phoneNumber: "",
        roles: []
      },
      people: [],
      filteredPeople: [],

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
  watch: {
    searchInput: {
      immediate: true,
      handler(newVal) {
        this.localSearchLastName = newVal;
        this.onEditFilter();
      }
    }
  },
  created() {
    this.person = this.createDefaultPerson();
    this.requestPeople();
  },
  methods: {
    createDefaultPerson() {
      return {
        id: "",
        username: "",
        lastName: "",
        firstName: "",
        patronymic: "",
        birthdate: "",
        phoneNumber: "+7(",
        roles: []
      };
    },
    calcRoles(item) {
      return item.roles
          ? item.roles.map(role => role.value).join(", ")
          : '';
    },
    handleClick(item) {
      this.$emit('rowClick', item);
    },
    updateSearch() {
      this.$emit("updateSearchInput", this.localSearchLastName);
      this.onEditFilter();
    },
    async requestPeople() {
      try {
        let basicAuth = localStorage.getItem("auth");
        const response = await this.fetchPeople(basicAuth);
        this.people = this.processPeople(response.data);
        await this.onEditFilter();
      } catch (error) {
        setAlertText("Не удалось получить данные");
        setAlertType("error");
        setAlertShow(true);
      }
    },

    fetchPeople(basicAuth) {
      return axios.get(import.meta.env.VITE_API_URL + "/api/v1/people", {
        headers: {"Authorization": "Basic " + basicAuth}
      });
    },

    processPeople(people) {
      return people.map(person => this.processPerson(person));
    },

    processPerson(person) {
      let birthdate;
      if ((person.patient !== undefined) && (person.patient.birthdate)) {
        birthdate = fromIsoToDefault(person.patient.birthdate);
      }

      let roles = [];
      if (person.patient !== undefined) {
        roles.push({value: "PATIENT"});
      }
      if (person.employee !== undefined) {
        for (let role of person.employee.roles) {
          roles.push({value: role});
        }
      }

      return {
        id: person.id,
        username: person.username,
        lastName: person.lastName,
        firstName: person.firstName,
        patronymic: person.patronymic,
        birthdate: birthdate,
        phoneNumber: person.patient !== undefined
            ? this.masks.phoneMask(person.patient.phoneNumber)
            : null,
        roles: roles
      };
    },

    onEditFilter() {
      this.filteredPeople = this.filterPeople(this.people);
    },

    filterPeople(people) {
      return people.filter(person => this.filterPerson(person));
    },

    filterPerson(person) {
      const checkFilter = (fieldValue, filterValue) => {
        if (filterValue === "") return true;
        if (!fieldValue && filterValue !== "") return false;
        if (fieldValue && typeof fieldValue === "string" && filterValue && typeof filterValue === "string") {
          return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
        }
        return false;
      };

      return (
          checkFilter(person.lastName, this.person.lastName) &&
          checkFilter(person.firstName, this.person.firstName) &&
          checkFilter(person.patronymic, this.person.patronymic) &&
          checkFilter(person.birthdate, this.person.birthdate) &&
          ((this.person.phoneNumber === this.createDefaultPerson().phoneNumber) || checkFilter(person.phoneNumber, this.person.phoneNumber)) &&
          checkFilter(person.username, this.person.username) &&
          (this.person.roles.length === 0 || (this.person.roles.length === 1 && this.person.roles[0].value === null) ||
              person.roles.some(r => this.person.roles.map(role => role.value).includes(r.value)))
      );
    },

    resetFilters() {
      this.person = this.createDefaultPerson();
      this.onEditFilter();
    }
  }
};
</script>

<style scoped>
.role-field {
  width: 200px;
}
</style>
