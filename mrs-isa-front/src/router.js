import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),

    routes:[
        { path: '/client/home', component: () => import("./components/client/clientHomePage.vue")},
        { path: '/client/profile', component: () => import("./components/client/clientProfileChange.vue")},
        { path: '/client/history/:type', component: () => import("./components/client/clientReservationHistory.vue")},
        { path: '/client/verify/:code', component: () => import("./components/client/clientVerified.vue") },
        { path: '/client/site/home', component: () => import("./components/main_site/homePageClient.vue")},

        { path: '/instructor/adventures', component: () => import("./components/fishing_instructor/allInstructorsAdventures")},

        {path: '/adventures/detail/:type', name: 'adventureForFishingInstructor', component: () => import("./components/fishing_instructor/adventureForFishingInstructor"),},
        { path: '/instructor/home', component: ()=> import("./components/fishing_instructor/instructorHomePage")},
        { path: '/adventure/add', component: ()=> import("./components/fishing_instructor/addAdventure")},
        { path: '/adventure/update/:type/:pre', component: ()=> import("./components/fishing_instructor/updateAdventure.vue")},
        { path: '/adventure/reservations/:type', component: ()=> import("./components/fishing_instructor/reservationsForOwner")},

        { path: '/book/site/home', component: () => import("./components/main_site/homePage.vue")},
        { path: '/book/site/login', component: () => import("./components/login.vue")},
        { path:  '/book/site/register', component: () => import("./components/registerUser.vue")},
        { path:  '/book/cottage/site', component: () => import("./components/main_site/siteCottageListView.vue")},
        { path:  '/book/ship/site', component: () => import("./components/main_site/siteShipListView.vue")},
        { path:  '/book/adventure/site', component: () => import("./components/main_site/siteAdventureListView.vue")},

        { path: '/cottageOwner/profile', component: () => import("./components/cottage_owner/editProfileCottageOwner")},
        {path:'/cottage/reservations/future/:id',component:() => import("./components/cottage_owner/cottageOwnerFutureReservationsList")},
        {path:'/cottage/reservations/past/:id',component:()=>import("./components/cottage_owner/cottageOwnerPastReservationsList")},
        {path:'/ship/reservations/future/:id',component:() => import("./components/ship_owner/shipOwnerFutureReservationsList")},
        {path:'/ship/reservations/past/:id',component:()=>import("./components/ship_owner/shipOwnerPastReservationsList")},
        { path: '/shipOwner/profile', component: () => import("./components/ship_owner/editProfileShipOwner")},
        { path: '/cottageOwner/home', component: () => import("./components/cottage_owner/homePageCottageOwner")},
        { path: '/shipOwner/home', component: () => import("./components/ship_owner/homePageShipOwner")},
        { path: '/owner/cottages', component: () => import("./components/cottage_owner/cottageList")},
        { path: '/owner/ships', component: () => import("./components/ship_owner/shipList")},
        {path:'/cottage/add',component:() => import("./components/cottage_owner/addCottage.vue")},
        {path:'/cottage/detail/:id',component:()=>import("./components/cottage_owner/cottageDetail")},
        {path:'/ship/add',component:() => import("./components/ship_owner/addShip.vue")},
        { path: '/cottage/update/:id',component:()=>import("./components/cottage_owner/updateCottage")},
        { path: '/ship/update/:id',component:()=>import("./components/ship_owner/updateShip")},


        {path:'/admin/home', component:()=>import("./components/admin/adminHomePage")},
        {path:'/admin/registration/request', component:()=> import("./components/admin/requestForRegistration.vue")},
        {path: '/admin/complaints', component:()=> import("./components/admin/complaints")},
        {path: '/admin/delete/requests', component:()=> import("./components/admin/deletingRequest")}
    ]
});


router.beforeEach((to, from, next) => {
    const publicPages = ['/book/site/login', '/book/site/register', '/book/site/home', '/book/cottage/site',
        '/book/ship/site', '/book/adventure/site'];
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
