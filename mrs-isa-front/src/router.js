import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    //mode: 'hash',
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        { path: '/client/profile', component: () => import("./components/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},
    ]
});

export default router