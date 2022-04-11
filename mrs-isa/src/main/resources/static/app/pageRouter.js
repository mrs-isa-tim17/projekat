
const clientHeader = {template: '<client-header></client-header>'}
const clientHome = { template: '<client-home></client-home>' }
const clientProfile = { template: '<client-profile-change></client-profile-change>' }
const standardInputField = {template: '<input-field></input-field>'}
const standardDiasabledInputField = {template: '<disabled-input-field></diasabled-input-field>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/client/home', component: clientHome},
        { path: '/client/profile', component : clientProfile},
        //{ path: '/client/header', component : clientHeader}
	  ]
});

var app = new Vue({
	router,
	el: '#root',
	components: {
		clientHeader : clientHeader,
		clientHome : clientHome,
		clientProfile : clientProfile,
		standardInputField: standardInputField
	}
});

