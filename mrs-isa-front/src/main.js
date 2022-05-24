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


import SsrCarousel from 'vue-ssr-carousel'
import 'vue-ssr-carousel/index.css'

import 'sweetalert2/dist/sweetalert2.min.css';

import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

import { library } from '@fortawesome/fontawesome-svg-core';

/* import specific icons */
import { faUserSecret } from '@fortawesome/free-solid-svg-icons';

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

/* add icons to the library */
library.add(faUserSecret);

createApp(App).use(Datepicker).use(OpenLayersMap,VueStepWizard,swal,SsrCarousel,FontAwesomeIcon).use(router).mount('#app');


