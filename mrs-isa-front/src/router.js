import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    //mode: 'hash',
    routes: [
        {path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        {path: '/client/profile', component: () => import("./components/clientProfileChange.vue")},
        {path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},

        {path: '/instructor/adventures', component: () => import("./components/allInstructorsAdventures")},

        {
            path: '/adventures/detail/:type',
            name: 'adventureForFishingInstructor',
            component: () => import("./components/adventureForFishingInstructor"),
        },
        { path: '/instructor/home', component: ()=> import("./components/instructorHomePage")},
        { path: '/adventure/add', component: ()=> import("./components/addAdventure")},
        { path: '/adventure/update', component: ()=> import("./components/updateAdventure")}
    ]
});

export default router