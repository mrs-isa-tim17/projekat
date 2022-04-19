import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    //mode: 'hash',
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        { path: '/client/profile', component: () => import("./components/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},
        { path: '/owner/profile', component: () => import("./components/editProfileOwner")},
        { path: '/cottage/home', component: () => import("./components/cottageOwnerHomePage")},
        { path: '/owner/managment', component: () => import("./components/managmentOwner")}
    ]
});

export default router