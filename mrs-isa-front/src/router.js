import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        { path: '/client/profile', component: () => import("./components/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},

        { path: '/instructor/adventures', component: () => import("./components/allInstructorsAdventures")},
        { path: '/instructor/adventures/detail', component: () => import("./components/adventureForFishingInstructor")},
        { path: '/book/site/home', component: () => import("./components/homePage.vue")},
        { path: '/book/site/login', component: () => import("./components/login.vue")},
        { path:  '/register', component: () => import("./components/registerUser.vue")},

        { path: '/owner/profile', component: () => import("./components/editProfileOwner")},
        { path: '/cottage/home', component: () => import("./components/cottageOwnerHomePage")},
        { path: '/owner/managment', component: () => import("./components/managmentOwner")}
    ]
});


router.beforeEach((to, from, next) => {
    const publicPages = ['/book/site/login', '/book/site/register', '/book/site/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/book/site/home');
    } else {
        next();
    }
});

export default router
