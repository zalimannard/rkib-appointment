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
                                        v-model="personLocal.lastName"
                                        :rules="rules.requiredRule"
                                        capitalize-first-letter
                                        label="Фамилия"
                                        required-asterisk
                                />
                            </v-col>
                            <v-col class="pt-5 pb-0" cols="4">
                                <masked-text-field
                                        v-model="personLocal.firstName"
                                        :rules="rules.requiredRule"
                                        capitalize-first-letter
                                        label="Имя"
                                        required-asterisk
                                />
                            </v-col>
                            <v-col class="pt-5 pb-0" cols="4">
                                <masked-text-field
                                        v-model="personLocal.patronymic"
                                        capitalize-first-letter
                                        label="Отчество"
                                />
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col class="pt-0 pb-0" cols="4">
                                <masked-text-field
                                        v-model="patientLocal.birthdate"
                                        :handleBackspace="backspaceHandlers.handleBackspaceForDate"
                                        :mask="masks.dateMask"
                                        :rules="rules.dateRule"
                                        label="Дата рождения"
                                        placeholder="ДД.ММ.ГГГГ"
                                />
                            </v-col>
                            <v-col class="pt-0 pb-0" cols="4">
                                <masked-text-field
                                        v-model="patientLocal.phoneNumber"
                                        :handleBackspace="backspaceHandlers.handleBackspaceForPhoneNumber"
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
                                        v-model="patientLocal.address"
                                        label="Адрес"
                                />
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col class="pt-0 pb-0" cols="12">
                                <masked-text-field
                                        v-model="patientLocal.occupation"
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

import {dateRule, phoneRule, requiredRule} from "@/rules";
import {dateMask, phoneMask} from "@/masks";
import {handleBackspaceForDate, handleBackspaceForPhoneNumber} from "@/backspaceHandlers";

export default {
    components: {MaskedTextField, CustomButton},
    props: {
        value: Boolean,
        person: Object,
        patient: Object
    },
    data() {
        return {
            internalValue: this.value,
            valid: true,

            personLocal: this.person ? {...this.person} : {lastName: "", firstName: "", patronymic: ""},
            patientLocal: this.patient ? {...this.patient} : {
                birthdate: "",
                phoneNumber: "",
                address: "",
                occupation: ""
            },

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
    watch: {
        person: {
            handler(newVal) {
                this.personLocal = {...newVal};
            },
            deep: true
        },
        patient: {
            handler(newVal) {
                this.patientLocal = {...newVal};
            },
            deep: true
        },
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
                    headers: {"Authorization": "Basic " + basicAuth},
                    data: {
                        // TODO: Потом убрать username и password отсюда вообще
                        username: (Math.random() + 1).toString(36).substring(7),
                        password: "password",
                        lastName: this.personLocal.lastName,
                        firstName: this.personLocal.firstName,
                        patronymic: this.personLocal.patronymic
                    }
                }).then((response) => {
                    let personId = response.data.id;
                    let phoneNumber = this.patientLocal.phoneNumber
                        .replaceAll("(", "")
                        .replaceAll(")", "")
                        .replaceAll("-", "");
                    let birthdate = null;
                    if (this.patientLocal.birthdate) {
                        let dateParts = this.patientLocal.birthdate.split(".");
                        if (dateParts.length === 3) {
                            birthdate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
                        }
                    }
                    axios({
                        method: "post",
                        url: import.meta.env.VITE_API_URL + "/api/v1/patients",
                        headers: {"Authorization": "Basic " + basicAuth},
                        data: {
                            personId: personId,
                            phoneNumber: phoneNumber,
                            birthdate: birthdate,
                            address: this.patientLocal.address,
                            occupation: this.patientLocal.occupation
                        }
                    }).then(() => {
                        this.$emit("input", {
                            person: {
                                lastName: this.personLocal.lastName,
                                firstName: this.personLocal.firstName,
                                patronymic: this.personLocal.patronymic
                            },
                            patient: {
                                birthdate: this.patientLocal.birthdate,
                                phoneNumber: this.patientLocal.phoneNumber,
                                address: this.patientLocal.address,
                                occupation: this.patientLocal.occupation
                            }
                        });
                        this.$emit("update:onNewPatient", {
                            person: {
                                lastName: this.personLocal.lastName,
                                firstName: this.personLocal.firstName,
                                patronymic: this.personLocal.patronymic
                            },
                            patient: {
                                birthdate: this.patientLocal.birthdate,
                                phoneNumber: this.patientLocal.phoneNumber,
                                address: this.patientLocal.address,
                                occupation: this.patientLocal.occupation
                            }
                        });
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

<style scoped>
.v-card-title {
    padding-top: 1vh;
    padding-bottom: 1vh;
    background-color: #3F51B5;
    font-size: 14pt;
    color: #FFFFFF;
    text-align: center;
}
</style>
