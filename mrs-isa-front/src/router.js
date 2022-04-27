import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    //mode: 'hash',
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        { path: '/client/profile/:id', component: () => import("./components/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},
        { path: '/instructor/adventures', component: () => import("./components/allInstructorsAdventures")},
        { path: '/instructor/adventures/detail', component: () => import("./components/adventureForFishingInstructor")},
        { path: '/home', component: () => import("./components/homePage.vue")},
        { path: '/login', component: () => import("./components/login.vue")},
        { path:  '/register', component: () => import("./components/registerUser.vue")}

    ]
});

export default router
