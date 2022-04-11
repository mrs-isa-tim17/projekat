
const clientHeader = {template: '<client-header></client-header>'}
const clientHome = { template: '<client-home></client-home>' }
const clientProfile = { template: '<client-profile-change></client-profile-change>' }
const standardDiasabledInputField = {template: '<disabled-input-field></diasabled-input-field>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/client/home', component: clientHome},
        { path: '/client/profile', component : clientProfile}
	  ]
});

var app = new Vue({
	router,
	el: '#root',
	components: {
		clientHeader : clientHeader,
		clientHome : clientHome,
		clientProfile : clientProfile,
		standardDiasabledInputField: standardDiasabledInputField
	}
});

