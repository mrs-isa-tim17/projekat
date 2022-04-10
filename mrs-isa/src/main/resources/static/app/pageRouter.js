
const clientHeader = {template: '<client-header></client-header>'}
const clientHome = { template: '<client-home></client-home>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/client/home', component: clientHome},
        { path: '/client/header', component : clientHeader}
	  ]
});

var app = new Vue({
	router,
	el: '#root',
	components: {
		clientHeader : clientHeader,
		clientHome : clientHome
	}
});

