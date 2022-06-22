<template>
  <div v-if="reviews.length > 0">
    <div v-for="(review, index) of reviews" :key="index">
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
    this.getListOfReviews();
  },
  created() {
  },
  methods: {
    getListOfReviews(){

      if (this.offerType === "cottage" && this.id !== "" ){
        cottageServce.getCottageReviews(this.id, this.fromEl, this.numToDisplay)
            .then((response) => {
              let listLengthObj = response.data[0];
              this.listLength = listLengthObj.listSize;
              if (this.listLength > 0)
                this.reviews = response.data.slice(1);
              else
                this.reviews = [];
              if (this.listLength === 0){
                document.getElementById("noReviews").style.visibility = "visible"
              }
              this.$emit("remountReviews");
            })
      }else if (this.offerType === "adventure" && this.id !== "" ){
        adventureServce.getAdventureReviews(this.id, this.fromEl, this.numToDisplay)
            .then((response) => {
              let listLengthObj = response.data[0];
              this.listLength = listLengthObj.listSize;
              if (this.listLength > 0)
                this.reviews = response.data.slice(1);
              else
                this.reviews = [];
              if (this.listLength === 0){
                document.getElementById("noReviews").style.visibility = "visible"
              }
              this.$emit("remountReviews");
            })
      }else if (this.offerType === "ship" && this.id !== "" ) {
        shipServce.getShipReviews(this.id, this.fromEl, this.numToDisplay)
            .then((response) => {
              let listLengthObj = response.data[0];
              this.listLength = listLengthObj.listSize;
              if (this.listLength > 0)
                this.reviews = response.data.slice(1);
              else
                this.reviews = [];
              if (this.listLength === 0) {
                document.getElementById("noReviews").style.visibility = "visible"
              }
              this.$emit("remountReviews");
            })
      }
    },
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
          this.resultingReview.push(r);
        }
        index++;
      }
      return this.resultingReview;
    },
    forceRemounting(){
      this.myKey += 1;
      this.paginationKey++;

    },
    fromUntilElement(from){
      this.fromEl = from;
      this.getListOfReviews();
//      this.forceRemounting();
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
