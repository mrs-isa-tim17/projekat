const ownerHome = { template: '<owner-home-page></owner-home-page>' }
const ownerEditProfile = {template: '<owner-edit-profile></owner-edit-profile>'}
const ownerManagment = {template:'<owner-managment></owner-managment>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/owner', component: ownerHome},
	    {path: '/owner/editprofile',component:ownerEditProfile},
	    {path:'/owner/managment',component:ownerManagment}
	  ]
});

var app = new Vue({
	router,
	el: '#fun',
	components: {
		ownerHome:ownerHome,
		ownerEditProfile:ownerEditProfile,
		ownerManagment:ownerManagment
	}
});