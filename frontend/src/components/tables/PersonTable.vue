<template>
    <custom-table>
        <template v-slot:header>
            <thead>
            <tr>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.lastName"
                            capitalize-first-letter
                            class="header-cell"
                            density="comfortable"
                            label="Фамилия"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.firstName"
                            capitalize-first-letter
                            class="header-cell"
                            density="comfortable"
                            label="Имя"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.patronymic"
                            capitalize-first-letter
                            class="header-cell"
                            density="comfortable"
                            label="Отчество"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.birthdate"
                            capitalize-first-letter
                            class="header-cell"
                            density="comfortable"
                            label="Дата рождения"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.phoneNumber"
                            capitalize-first-letter
                            class="header-cell"
                            density="comfortable"
                            label="Телефон"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell" scope="col">
                    <masked-text-field
                            v-model="person.username"
                            class="header-cell"
                            density="comfortable"
                            label="Логин"
                            @input="updateSearchInput"
                    />
                </th>
                <th class="text-left header-cell role-field" scope="col">
                    <role-select
                            v-model:role="person.role"
                            :include-patient="true"
                            class="header-cell"
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
                    :class="index % 2 === 0 ? 'light-row' : 'dark-row'"
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
import {requiredRule} from "@/rules";
import MaskedTextField from "@/components/custom/textfield/MaskedTextField.vue";
import axios from "axios";
import CustomTable from "@/components/custom/table/CustomTable.vue";
import RoleSelect from "@/components/custom/selectrole/RoleSelect.vue";

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
            defaultPerson: {
                id: "",
                username: "",
                lastName: "",
                firstName: "",
                patronymic: "",
                birthdate: "",
                phoneNumber: "+7(",
                role: ""
            },
            person: {
                id: "",
                username: "",
                lastName: "",
                firstName: "",
                patronymic: "",
                birthdate: "",
                phoneNumber: "",
                role: ""
            },
            people: [],
            filteredPeople: [],

            rules: {
                requiredRule
            }
        };
    },
    watch: {
        searchInput: {
            immediate: true,
            handler(newVal) {
                this.localSearchLastName = newVal;
                this.editFilter();
            }
        }
    },
    async created() {
        await this.requestPeople();
    },
    methods: {
        calcRoles(item) {
            // console.log(item)
            let roles = [];
            if (item.phoneNumber != null) {
                roles.push("PATIENT");
            }
            if (item.roles) {
                for (let role of item.roles) {
                    roles.push(role);
                }
            }
            return roles.join(", ");
        },
        updateSearchInput() {
            this.$emit("updateSearchInput", this.localSearchLastName);
            this.editFilter();
        },
        async requestPeople() {
            try {
                let basicAuth = localStorage.getItem("auth");
                const response = await axios.get(import.meta.env.VITE_API_URL + "/api/v1/people", {
                    headers: {"Authorization": "Basic " + basicAuth}
                });
                this.people = response.data.map(person => {
                    console.log(person)
                    return {
                        id: person.id,
                        username: person.username,
                        lastName: person.lastName,
                        firstName: person.firstName,
                        patronymic: person.patronymic,
                        birthdate: person.patient !== undefined ? person.patient.birthdate : null,
                        phoneNumber: person.patient !== undefined ? person.patient.phoneNumber : null,
                        roles: person.employee !== undefined ? person.employee.roles : null,
                    };
                });
                await this.editFilter();
            } catch (error) {
                console.error("Ошибка при получении данных:", error);
            }
        },

        async editFilter() {
            const checkFilter = (fieldValue, filterValue) => {
                if (filterValue === "") return true;
                if (!fieldValue && filterValue !== "") return false;
                if (fieldValue && typeof fieldValue === "string" && filterValue && typeof filterValue === "string") {
                    return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
                }
                return false;
            };

            this.filteredPeople = this.people.filter(person => {
                return (
                    checkFilter(person.username, this.person.username) &&
                    checkFilter(person.lastName, this.person.lastName) &&
                    checkFilter(person.firstName, this.person.firstName) &&
                    checkFilter(person.patronymic, this.person.patronymic)
                );
            });
        },
        resetFilters() {
            this.person = this.defaultPerson;
            this.editFilter();
        }
    }
};
</script>

<style scoped>
.role-field {
    width: 200px;
}
</style>
