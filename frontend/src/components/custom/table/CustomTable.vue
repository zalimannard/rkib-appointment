<template>
    <v-container class="main-container pl-0 pr-0 pt-6">
        <v-table class="table-container pa-0 ma-0" fixed-footer>
            <slot name="header"></slot>
            <slot name="body"></slot>
        </v-table>
    </v-container>
</template>

<script>
export default {
    data() {

    }
};
</script>

<style>
.main-container {
    width: 100%;
}

.table-container {
    width: 100%;
    border: 1px solid #ccc;
    border-radius: 4pt;
}

.header-cell {
    padding-top: 20px;
}

.light-row {
    background-color: #FFFFFF;
}

.dark-row {
    background-color: #ECEFF1;
}
</style>
