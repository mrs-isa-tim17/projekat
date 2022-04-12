const instructorHeader = {template: '<instructor-header></instructor-header>'}
const imagesCarousel = {template : '<img-option></img-option>'}
const simpleAdventure = {template: '<simple-adventure></simple-adventure>'}
const allInstructionsAdventures = {template : '<adventure-all></adventure-all>'}
const adventureForFishingInstructor = {template : '<adventure-instructor></adventure-instructor>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{path: '/instructor/adventures', component: allInstructionsAdventures},
		{path: '/instuctor/one/adventure', component: adventureForFishingInstructor}
	  ]
});

var app = new Vue({
	router,
	el: '#root',
	components: {
		instructorHeader : instructorHeader,
		imagesCarousel : imagesCarousel,
		simpleAdventure : simpleAdventure,
		adventurePageFishingInstructor : adventureForFishingInstructor,
		allInstructionsAdventures : allInstructionsAdventures
	}
});