import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    //mode: 'hash',
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
    ]
});

export default router