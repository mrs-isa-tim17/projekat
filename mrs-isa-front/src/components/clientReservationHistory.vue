<template>
  <div style="overflow-scrolling: auto">
    <clientHeader></clientHeader>

    <div class="row">
      <div class="col-8">
      </div>
      <div class="col-3 justify-content-right" >
        <select id="sortBy" @change="sortList" class="form-select" aria-label="Default select">
          <option selected>Sortiraj po</option>
          <option value="1">Datum</option>
          <option value="2">Trajanja</option>
          <option value="3">Naziv</option>
          <option value="4">Cena</option>
        </select>
      </div>
    </div>


    <div>
      <clientReservationHistoryList @reviewed="itemReviewed" :key="myKey" :numToDisplay="numberOfElementsForDisplay" :from="fromElement" :cottagesHistory="cottageReservationHistory"> </clientReservationHistoryList>
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

  </div>
</template>

<script>
import clientReservationHistoryList from "@/components/clientReservationHistoryList";
import clientHeader from "@/components/clientHeader";
import clientServce from "@/servieces/ClientServce";
export default {
  name: "clientReservationHistory",
  components:{
    clientReservationHistoryList,
    clientHeader
  },
  props: ['option_link', 'image_path', 'text'],
  created: function() {
    this.fromElement = 0;
    this.type = this.$route.params.type;
    console.log(this.type);
    if (this.type == "ship")
      clientServce.getPastShipReservations(JSON.parse(localStorage.user).id)
          .then(response =>{
            this.cottageReservationHistory = response.data;

          })
    else if (this.type == "cottage")
      clientServce.getPastCottageReservations(JSON.parse(localStorage.user).id)
          .then((response) => {
            this.cottageReservationHistory = response.data;
            }
      );

  },
  methods : {
    itemReviewed(itemID){
      console.log(itemID);
      for (let offer of this.cottageReservationHistory){
        console.log(offer);
        if (offer.id == itemID){
          offer.reviewed = true;
        }
      }
    },
    forceRemounting(){
      this.myKey -= 1;
    },
    sortList(){
      //axios
      this.fromElement = 0;
      this.forceRemounting();
      console.log(document.getElementById("sortBy").value);
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 3){
        clientServce.getSortedPastCottageReservationsByName(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 1){
        clientServce.getSortedPastCottageReservationsByDate(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 2){
        clientServce.getSortedPastCottageReservationsByDuration(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4){
        clientServce.getSortedPastCottageReservationsByPrice(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }
    },
    makeMiddleNumber(){
      let numPages = this.cottageReservationHistory.length / this.numberOfElementsForDisplay;
      return Math.ceil(numPages / 2);
    },
    makeLastNumber(){
      let numPages = this.cottageReservationHistory.length / this.numberOfElementsForDisplay;
      return Math.round(numPages);
    },
    makeFirstLink(){
      this.fromElement = 0;
      this.forceRemounting();
    },
    makeMiddleLink(){
      this.fromElement = (this.makeMiddleNumber()-1) * this.numberOfElementsForDisplay;
      this.forceRemounting();
    },
    makeLastLink(){
      this.fromElement = (this.makeLastNumber()-1) * this.numberOfElementsForDisplay;
      this.forceRemounting();
    },
    makePreviousLink(){
      let previousFrom = parseInt(this.fromElement) - parseInt(this.numberOfElementsForDisplay);
      if (previousFrom < 0)
        return
      this.fromElement = previousFrom;
      this.forceRemounting();
    },
    makeNextLink(){
      let nextFrom = parseInt(this.fromElement) + parseInt(this.numberOfElementsForDisplay);
      if (nextFrom > parseInt(this.cottageReservationHistory.length))
        return
      this.fromElement = nextFrom;
      this.forceRemounting();
    },
    checkIfNeedsToBeDisplayed(index){
      let untilElement = parseInt(this.numberOfElementsForDisplay) + parseInt(this.fromElement);
      console.log(index);
      console.log(this.fromElement);
      console.log(untilElement);
      if (index >= this.fromElement && index < untilElement){
        return true;
      }
      return false;
    }
  },
  data() {
    return {
      myKey: 0,
      cottageReservationHistory: [],
      numberOfElementsForDisplay: 2
    }
  }
}
</script>

<style scoped>

</style>
