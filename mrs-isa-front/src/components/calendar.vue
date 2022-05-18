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
    console.log(this.myEvents);
  },

  data() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        dateClick: this.handleDateClick,
        events: [
        //  {title: '', start: '2022-04-01', end: '2022-04-30', color: '#4FC358'},
          //{title: 'avantura', start:'2022-04-19', end:'2022-04-22', color:'#2593F5'},
          //{title: '', start: '2022-05-12', end: '2022-05-19', color:'#F86C53'}
        ],
        period:{
          start:"",
          end:"",
          color: "",
          title: ""
        }
      },
    }
  },
  methods: {
    handleDateClick: function(arg) {
      alert('date click! ' + arg.dateStr)
    }
  }
}
</script>
<template>
  <FullCalendar :options="calendarOptions" />
</template>