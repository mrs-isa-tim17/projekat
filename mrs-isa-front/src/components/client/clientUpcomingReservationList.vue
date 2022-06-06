<template>
  <div class="container" >
    <div class="p-2" v-for="(item) in offers"  :key="item.name">
      <client-upcoming-reservation-element :offer="item" @rerender="emitRerender"> </client-upcoming-reservation-element>
    </div>
  </div>
</template>

<script>
import ClientUpcomingReservationElement from "@/components/client/clientUpcomingReservationElement";
export default {
  name: "clientUpcomingReservationList",
  components: {ClientUpcomingReservationElement},
  props: ["offers"],
  methods: {
    emitRerender(){
      this.$emit('rerender');
    }
  }
}
</script>

<style scoped>

</style>
