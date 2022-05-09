<template>


  <div>
    <div class="p-2" v-for="(item) in filterOffers()"  :key="item.name">
      <site-ship-element :cottage="item"></site-ship-element>
    </div>
  </div>

  <pagination-component :numberOfElementsToDisplay="numberOfElementsForDisplay" :fromElement="fromElement"
                        :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>


</template>

<script>
import shipServce from "@/servieces/ShipServce";
import SiteShipElement from "@/components/main_site/siteShipElement";
import PaginationComponent from "@/components/paginationComponent";

export default {
  name: "site-cottage-list",
  components: {PaginationComponent, SiteShipElement},
  mounted() {
  },
  created:
      function () {
        shipServce.getShips().then(
            (response) => {
              this.cottages = response.data;
              this.listLength = this.cottages.length;
            }
        )
        try{

          if (JSON.parse(localStorage.user) == null) {
            this.verifiedClient = false;
          } else {
            this.verifiedClient = true;
          }
        }catch (error){
          this.verifiedClient = false;

        }
        this.forceRemounting();


      },
  methods: {
    fromUntilElement(from){
      this.fromElement = from;
      this.forceRemounting();
    },
    forceRemounting(){
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
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
      verifiedClient: false,
      cottages : [],
      numberOfElementsForDisplay : 3,
      fromElement: 0,
      basicHeaderKey: 0,
      clientHeaderKey: 0
    }
  }
}
</script>

<style scoped>

</style>
