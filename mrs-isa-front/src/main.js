import App from './App.vue';
import {createApp} from "vue";

import router from "./router";
import VueStepWizard from 'vue-step-wizard'
import 'vue-step-wizard/dist/vue-step-wizard.css'

import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'jquery/src/jquery';
import 'bootstrap/dist/js/bootstrap.min.js';
import './assets/basicCSS.css'
import "vue-form-wizard/dist/vue-form-wizard.min.css";
import swal from 'sweetalert2';

import 'sweetalert2/dist/sweetalert2.min.css';

import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';


createApp(App).use(Datepicker).use(OpenLayersMap,VueStepWizard,swal).use(router).mount('#app');


