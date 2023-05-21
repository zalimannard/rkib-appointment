import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import {loadFonts} from "./plugins/webfontloader";
import alertState from "@/components/alert/AlertState";

loadFonts();

createApp(App)
    .use(router)
    .use(vuetify)
    .provide("alertState", alertState)
    .mount("#app");
