<template>
  <div v-if="reviews.length > 0">
    <div v-for="(review, index) of filterReviews()" :key="index">
      <client-comment :key="myKey" :review="review"></client-comment>
    </div>

    <pagination-component :key="paginationKey" :numberOfElementsToDisplay="numToDisplay" :fromElement="fromEl" :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>
  </div>
  <div v-else>
    <p id="noReviews" style="visibility: hidden">Nema još recenzije na entitet</p>
  </div>
</template>

<script>
import cottageServce from "@/servieces/CottageServce";
import PaginationComponent from "@/components/paginationComponent";
import ClientComment from "@/components/main_site/offer_profile/comment";
import adventureServce from "@/servieces/AdventureServce";
import shipServce from "@/servieces/ShipServce";

export default {
  name: "experienceReviewView",
  components: {ClientComment, PaginationComponent},
  props: ["id", "offerType"],
  mounted() {

    console.log(this.id);
    if (this.offerType === "cottage" && this.id !== "" ){
      cottageServce.getCottageReviews(this.id)
          .then((response) => {
            this.reviews = response.data;
            this.listLength = this.reviews.length;
            this.paginationKey++;
            if (this.listLength === 0){
              document.getElementById("noReviews").style.visibility = "visible"
            }
          })
    }else if (this.offerType === "adventure" && this.id !== "" ){
      adventureServce.getAdventureReviews(this.id)
          .then((response) => {
            this.reviews = response.data;
            this.listLength = this.reviews.length;
            this.paginationKey++;
            if (this.listLength === 0){
              document.getElementById("noReviews").style.visibility = "visible"
            }
          })
    }else if (this.offerType === "ship" && this.id !== "" ) {
      shipServce.getShipReviews(this.id)
          .then((response) => {
            this.reviews = response.data;
            this.listLength = this.reviews.length;
            this.paginationKey++;
            if (this.listLength === 0) {
              document.getElementById("noReviews").style.visibility = "visible"
            }
          })
    }
  },
  created() {
  },
  methods: {

    checkIfNeedsToBeDisplayed(index){
      let untilElement = parseInt(this.numToDisplay) + parseInt(this.fromEl);
      if (index >= this.fromEl && index < untilElement){
        return true;
      }
      return false;
    },
    filterReviews(){
      this.resultingReview = [];
      let index = 0;
      for (let r of this.reviews){
        if (this.checkIfNeedsToBeDisplayed(index)) {
          console.log(r.clientName);
          this.resultingReview.push(r);
        }
        index++;
      }
      console.log(this.resultingReview);
      return this.resultingReview;
    },
    forceRemounting(){
      this.myKey += 1;
      this.paginationKey++;

    },
    fromUntilElement(from){
      console.log("paginate called");
      this.fromEl = from;
      this.$emit("remountReviews");
      this.forceRemounting();
    }
  },
  data(){
    return {
      reviews: [
      ],
      numToDisplay: 2,
      myKey: 0,
      fromEl: 0,
      paginationKey: 0,
      listLength: 0
    }
  }
}
</script>

<style scoped>

</style>
