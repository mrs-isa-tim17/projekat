<template>


  <div>
    <div class="p-2" v-for="(item) in filterOffers()"  :key="item.name">
      <site-cottage-element :cottage="item"></site-cottage-element>
    </div>
  </div>


  <pagination-component :numberOfElementsToDisplay="numberOfElementsForDisplay" :fromElement="fromElement"
                        :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>


</template>

<script>
import cottageServce from "@/servieces/CottageServce";
import SiteCottageElement from "@/components/main_site/siteCottageElement";
import PaginationComponent from "@/components/paginationComponent";

export default {
  name: "site-cottage-list",
  components: {PaginationComponent, SiteCottageElement},
  mounted() {
  },
  created:
    function () {
      cottageServce.getCottages().then(
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
      numberOfElementsForDisplay : 4,
      fromElement: 0,
      basicHeaderKey: 0,
      clientHeaderKey: 0
    }
  }
}
</script>

<style scoped>

</style>
