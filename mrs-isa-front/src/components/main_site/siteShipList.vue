<template>


  <div>
    <div class="p-2" v-for="(item) in filterOffers()"  :key="item.name">
      <site-ship-element :cottage="item"></site-ship-element>
    </div>
  </div>

  <nav class="d-flex justify-content-center" aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item">
        <button class="page-link" @click="makePreviousLink" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </button>
      </li>

      <li class="page-item"><button class="page-link" @click="makeFirstLink">1</button></li>
      <li class="page-item"><button class="page-link" disabled>...</button></li>
      <li class="page-item"><button class="page-link" @click="makeMiddleLink">{{makeMiddleNumber()}}</button></li>
      <li class="page-item"><button class="page-link" disabled>...</button></li>
      <li class="page-item"><button class="page-link" @click="makeLastLink">{{makeLastNumber()}}</button></li>

      <li class="page-item">
        <button class="page-link" @click="makeNextLink" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </button>
      </li>
    </ul>
  </nav>

</template>

<script>
import shipServce from "@/servieces/ShipServce";
import SiteShipElement from "@/components/main_site/siteShipElement";

export default {
  name: "site-cottage-list",
  components: {SiteShipElement},
  mounted() {
    shipServce.getShips().then(
        (response) => {
          this.cottages = response.data;
        }
    )
  },
  created:
      function () {
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
    makeMiddleNumber() {
      let numPages = this.cottages.length / this.numberOfElementsForDisplay;
      return Math.ceil(numPages / 2);
    },
    makeLastNumber() {
      let numPages = this.cottages.length / this.numberOfElementsForDisplay;
      return Math.ceil(numPages);
    },
    makeFirstLink() {
      this.fromElement = 0;
      this.forceRemounting();
    },
    makeMiddleLink() {
      this.fromElement = (this.makeMiddleNumber() - 1) * this.numberOfElementsForDisplay;
      this.forceRemounting();
    },
    makeLastLink() {
      this.fromElement = (this.makeLastNumber() - 1) * this.numberOfElementsForDisplay;
      this.forceRemounting();
    },
    makePreviousLink() {
      let previousFrom = parseInt(this.fromElement) - parseInt(this.numberOfElementsForDisplay);
      if (previousFrom < 0)
        return
      this.fromElement = previousFrom;
      this.forceRemounting();
    },
    makeNextLink() {
      let nextFrom = parseInt(this.fromElement) + parseInt(this.numberOfElementsForDisplay);
      if (nextFrom > parseInt(this.cottageReservationHistory.length))
        return
      this.fromElement = nextFrom;
      this.forceRemounting();
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
