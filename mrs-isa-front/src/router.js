import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes:[
        { path: '/client/home', component: () => import("./components/clientHomePage.vue")},
        { path: '/client/profile', component: () => import("./components/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/clientReservationHistory.vue")},
        { path: '/client/verify/:code', component: () => import("./components/clientVerified.vue") },
        { path: '/client/site/home', component: () => import("./components/homePageClient.vue")},

        { path: '/instructor/adventures', component: () => import("./components/allInstructorsAdventures")},
        { path: '/instructor/adventures/detail', component: () => import("./components/adventureForFishingInstructor")},
        { path: '/book/site/home', component: () => import("./components/homePage.vue")},
        { path: '/book/site/login', component: () => import("./components/login.vue")},
        { path:  '/book/site/register', component: () => import("./components/registerUser.vue")},

        { path: '/owner/profile', component: () => import("./components/editProfileOwner")},
        { path: '/cottage/home', component: () => import("./components/cottageOwnerHomePage")},
        { path: '/owner/managment', component: () => import("./components/managmentOwner")}
    ]
});


router.beforeEach((to, from, next) => {
    const publicPages = ['/book/site/login', '/book/site/register', '/book/site/home',
        '/client/verify/HQqzhsKyQkxPgrNTiTIQkUeAcj43RwahJoEbEDsnIOL9yS6IfyS10SHtDTZekMBJ'];
    let authRequired = !publicPages.includes(to.path);
    if (to.path.includes('/client/verify/')){
        authRequired = false;
    }
    const loggedIn = localStorage.getItem('user');
    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/book/site/login');
    } else {
        next();
    }
});

export default router
