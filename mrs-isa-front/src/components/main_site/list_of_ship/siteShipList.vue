<template>


  <div>
    <div class="p-2" v-for="(item) in cottages"  :key="item.name">
      <site-ship-element :cottage="item"></site-ship-element>
    </div>
  </div>

  <pagination-component :key="paginationKey" :numberOfElementsToDisplay="numberOfElementsForDisplay"
                        :fromElement="fromElement"
                        :numberOfElements="listLength" @pagination="fromUntilElement"
                        class="d-flex justify-content-center"></pagination-component>


</template>

<script>
import SiteShipElement from "@/components/main_site/list_of_ship/siteShipElement";
import PaginationComponent from "@/components/paginationComponent";

export default {
  name: "site-cottage-list",
  components: {PaginationComponent, SiteShipElement},
  props: ["cottages", "numberOfElementsForDisplay", "listLength", "fromElement"],
  mounted() {
  },
  created:
      function () {
        this.forceRemounting();


      },
  methods: {
    fromUntilElement(from){
      this.$emit("get-list-from", from);
    },
    forceRemounting(){
      this.paginationKey += 1;
    },
    filterOffers(){
      this.resultingOffers = [];
      this.paginationKey += 1;
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
      paginationKey: 0
    }
  }
}
</script>

<style scoped>

</style>
