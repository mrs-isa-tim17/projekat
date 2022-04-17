<template>
  <div class="container" >
    <div class="p-2" v-for="(item) in filterOffers()"  :key="item.name">
      <clientReservationHistoryElement :cottage="item" :key="myKey" @rerender="forceRerendering(item)" @reviewed="itemReviewed"> </clientReservationHistoryElement>
    </div>
  </div>
</template>

<script>
import clientReservationHistoryElement from "@/components/clientReservationHistoryElement";
export default {
  props: ['from', 'cottagesHistory', 'numToDisplay'],
  name: "clientReservationHistoryList",
  components: {
    clientReservationHistoryElement
  },
  mounted() {


  },
  methods : {
    checkIfNeedsToBeDisplayed(index){
      let untilElement = parseInt(this.numToDisplay) + parseInt(this.from);
      if (index >= this.from && index < untilElement){
        return true;
      }
      return false;
    },
    filterOffers(){
      this.resultingOffers = [];
      let index = 0;
      for (let offer of this.cottagesHistory){
        if (this.checkIfNeedsToBeDisplayed(index)) {
          this.resultingOffers.push(offer);
        }
        index++;
      }
      console.log(this.resultingOffers);
      return this.resultingOffers;
    },
    forceRerendering(){
      this.myKey += 1;

    },
    itemReviewed(itemID){
      this.$emit('reviewed', itemID);
      this.myKey += 1;
      ///console.log(this.item);
      //this.item.reviewed = true;
    }
  },
  data() {
    return {
      myKey: 1
    }
  }
}
</script>

<style scoped>

</style>