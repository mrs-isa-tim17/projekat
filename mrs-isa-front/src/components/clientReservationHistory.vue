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

    <pagination-component :numberOfElementsToDisplay="numberOfElementsForDisplay" :fromElement="fromElement" :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>


  </div>
</template>

<script>
import clientReservationHistoryList from "@/components/clientReservationHistoryList";
import clientHeader from "@/components/clientHeader";
//import clientServce from "@/servieces/ClientServce";
import PaginationComponent from "@/components/paginationComponent";
import reservationServce from "@/servieces/ReservationServce";
export default {
  name: "clientReservationHistory",
  components:{
    PaginationComponent,
    clientReservationHistoryList,
    clientHeader
  },
  props: ['option_link', 'image_path', 'text'],
  created: function() {
    this.fromElement = 0;
    this.type = this.$route.params.type;
    console.log(this.type);
    if (this.type == "ship")
      reservationServce.getPastShipReservations(JSON.parse(localStorage.user).id)
          .then(response =>{
            this.cottageReservationHistory = response.data;
            this.listLength = this.cottageReservationHistory.length;
          })
    else if (this.type == "cottage")
      reservationServce.getPastCottageReservations(JSON.parse(localStorage.user).id)
          .then((response) => {
            this.cottageReservationHistory = response.data;
            this.listLength = this.cottageReservationHistory.length;
            }
      );
    else if (this.type == "adventure")
      reservationServce.getPastAdventureReservations(JSON.parse(localStorage.user).id)
          .then((response) => {
                this.cottageReservationHistory = response.data;
                this.listLength = this.cottageReservationHistory.length;
              }
          );

  },
  methods : {
    fromUntilElement(from){
      this.fromElement = from;
      this.forceRemounting();
    },
    itemReviewed(itemID){
      console.log(itemID);
      for (let offer of this.cottageReservationHistory){
        console.log(offer);
        if (offer.reservationId == itemID){
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
      if (sortBy == 3 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByName(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 1 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByDate(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 2 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByDuration(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByPrice(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 3 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByName(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 1 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByDate(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 2 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByDuration(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByPrice(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 3 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByName(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 1 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByDate(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 2 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByDuration(JSON.parse(localStorage.user).id)
            .then(response =>{
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4 && this.type == "adventure") {
        reservationServce.getSortedPastAdventureReservationsByPrice(JSON.parse(localStorage.user).id)
            .then(response => {
              this.cottageReservationHistory = response.data;
              this.forceRemounting();
            })
      }
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
      fromElement: 0,
      cottageReservationHistory: [],
      numberOfElementsForDisplay: 3
    }
  }
}
</script>

<style scoped>

</style>
