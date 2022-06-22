<template>
  <div v-for="(quick, index) of quickReservations" :key="index">
    <div class="card my-1">
      <div class="card-body">
        <quick-sale-element :saleAppointment="quick" @remount="forceRemount"></quick-sale-element>
      </div>
    </div>
  </div>
</template>

<script>
import QuickSaleElement from "@/components/client/quickSaleElement";
export default {
  name: "quickReservationList",
  components: {QuickSaleElement},
  props: ["quickReservations"],
  created() {
  },
  methods: {
    forceRemount(){
      this.$emit('remount');
    }
  }
}
</script>

<style scoped>

</style>
