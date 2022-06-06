<template>
  <div class="container" >
    <div class="p-2" v-for="(item) in offers"  :key="item.name">
      <client-upcoming-reservation-element :offer="item"> </client-upcoming-reservation-element>
    </div>
  </div>
</template>

<script>
import ClientUpcomingReservationElement from "@/components/client/clientUpcomingReservationElement";
export default {
  name: "clientUpcomingReservationList",
  components: {ClientUpcomingReservationElement},
  props: ["offers"]
}
</script>

<style scoped>

</style>
