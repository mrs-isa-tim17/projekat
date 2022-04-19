import App from './App.vue';
import {createApp} from "vue";

import router from "./router";

import 'bootstrap/dist/css/bootstrap.min.css';
import 'jquery/src/jquery';
import 'bootstrap/dist/js/bootstrap.min.js';
import './assets/basicCSS.css'

createApp(App).use(router).mount('#app');
