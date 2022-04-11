
const clientHeader = {template: '<client-header></client-header>'}
const clientHome = { template: '<client-home></client-home>' }
const clientProfile = { template: '<client-profile-change></client-profile-change>' }
const standardDiasabledInputField = {template: '<disabled-input-field></diasabled-input-field>'}
const cottageHistoryReservation = {template: '<cottage-history-reservation></cottage-history-reservation>'}
const historyReservationElement = {template: '<history-element></history-element>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/client/home', component: clientHome},
        { path: '/client/profile', component : clientProfile},
		{ path: '/client/history/cottage', component: cottageHistoryReservation}
	  ]
});

var app = new Vue({
	router,
	el: '#root',
	components: {
		clientHeader : clientHeader,
		clientHome : clientHome,
		clientProfile : clientProfile,
		standardDiasabledInputField: standardDiasabledInputField,
		cottageHistoryReservation: cottageHistoryReservation
	}
});

