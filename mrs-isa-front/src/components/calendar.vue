<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'

export default {
  name: "calendar-mrs",
  props: ['availabilityPeriod', 'unavailabilityPeriod', 'myEvents'],
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  created() {
    for (let period of this.availabilityPeriod){
      period.color ='#4FC358';
      this.calendarOptions.events.push(period);
    }
    for (let period of this.unavailabilityPeriod){
      period.color= '#F86C53';
      this.calendarOptions.events.push(period);
    }
    for(let period of this.myEvents){
      period.color = '#2593F5';
      this.calendarOptions.events.push(period);
    }
  },

  data() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        dateClick: this.handleDateClick,
        events: [
     //     {title: "  ", start: '2022-04-01 15:00', end: '2022-04-05 16:00', color: '#4FC358'},
       //   {title: 'avantura', start:'2022-04-05 16:00', end:'2022-04-09 19:00', color:'#2593F5'},
          //{title: '', start: '2022-05-12', end: '2022-05-19', color:'#F86C53'}
        ],
        timeFormat: 'hh:mm',
        locale:'sr-ME',
        firstDay:1,
        buttonText:{today:"Danas"},
      },
      period:{
        start:"",
        end:"",
        color: "",
        title: ""
      }
    }
  },
}
</script>
<template>
  <FullCalendar :options="this.calendarOptions" />
</template>