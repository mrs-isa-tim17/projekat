import App from './App.vue';
import {createApp} from "vue";

import router from "./router";

import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'

import 'bootstrap/dist/css/bootstrap.min.css';
import 'jquery/src/jquery';
import 'bootstrap/dist/js/bootstrap.min.js';
import './assets/basicCSS.css'


createApp(App).use(OpenLayersMap).use(router).mount('#app');
