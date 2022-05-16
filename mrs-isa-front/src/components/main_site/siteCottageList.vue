<template>


  <div>
    <div class="p-2" v-for="(item) in filterOffers()"  :key="item.name">
      <site-cottage-element :cottage="item"></site-cottage-element>
    </div>
  </div>


  <pagination-component :key="paginationKey" :numberOfElementsToDisplay="numberOfElementsForDisplay" :fromElement="fromElement"
                        :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>


</template>

<script>
import SiteCottageElement from "@/components/main_site/siteCottageElement";
import PaginationComponent from "@/components/paginationComponent";

export default {
  name: "site-cottage-list",
  components: {PaginationComponent, SiteCottageElement},
  props: ["cottages", "listLength"],
  mounted() {

  },
  created:
    function () {
      this.forceRemounting();
    },
  methods: {
    fromUntilElement(from){
      this.fromElement = from;
      this.forceRemounting();
    },
    forceRemounting(){
      this.paginationKey += 1;
    },
    filterOffers(){
      this.resultingOffers = [];
      let index = 0;
      for (let offer of this.cottages){
        if (this.checkIfNeedsToBeDisplayed(index)) {
          this.resultingOffers.push(offer);
        }
        index++;
      }
      return this.resultingOffers;
    },
    checkIfNeedsToBeDisplayed(index) {
      let untilElement = parseInt(this.numberOfElementsForDisplay) + parseInt(this.fromElement);
      if (index >= this.fromElement && index < untilElement) {
        return true;
      }
      return false;
    }
  },

  data() {
    return {
      numberOfElementsForDisplay : 4,
      fromElement: 0,
      paginationKey: 0
    }
  }
}
</script>

<style scoped>

</style>
