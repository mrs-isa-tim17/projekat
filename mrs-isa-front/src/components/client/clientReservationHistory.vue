<template>
  <div style="overflow-scrolling: auto">
    <clientHeader></clientHeader>

    <div class="row">
      <div class="col-8">
      </div>
      <div class="col-3 justify-content-right" >
        <select id="sortBy" @change="sortList(0)" class="form-select" aria-label="Default select">
          <option selected>Sortiraj po</option>
          <option value="1">Datum</option>
          <option value="2">Trajanja</option>
          <option value="3">Naziv</option>
          <option value="4">Cena</option>
        </select>
      </div>
    </div>


    <div>
      <clientReservationHistoryList :type="type" @reviewed="itemReviewed" :key="myKey" :numToDisplay="numberOfElementsForDisplay" :from="fromElement" :cottagesHistory="cottageReservationHistory"> </clientReservationHistoryList>
    </div>

    <pagination-component :key="paginationKey" :numberOfElementsToDisplay="numberOfElementsForDisplay" :fromElement="fromElement" :numberOfElements="listLength" @pagination="fromUntilElement" class="d-flex justify-content-center"></pagination-component>


  </div>
</template>

<script>
import clientReservationHistoryList from "@/components/client/clientReservationHistoryList";
import clientHeader from "@/components/client/clientHeader";
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
    this.type = this.$route.params.type;
    this.getReservations();
  },
  methods : {
    handleResponse(response){
      let listLengthObj = response.data[0];
      this.listLength = listLengthObj.listSize;
      if (this.listLength > 0)
        this.cottageReservationHistory = response.data.slice(1);
      else
        this.cottageReservationHistory = [];
      console.log("RESPONSE");
      console.log(this.cottageReservationHistory);
      this.forceRemounting();
    },
    getReservations(){
      if (this.type == "ship")
        reservationServce.getPastShipReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      else if (this.type == "cottage")
        reservationServce.getPastCottageReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then((response) => {
              this.handleResponse(response);
                }
            );
      else if (this.type == "adventure")
        reservationServce.getPastAdventureReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then((response) => {
              this.handleResponse(response);
                }
            );

    },
    fromUntilElement(from){
      this.fromElement = from;
      this.sortList(this.fromElement);
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
      this.paginationKey += 1;
    },
    sortList(fromElement){
      //axios
      this.fromElement = fromElement;
      this.forceRemounting();
      console.log(document.getElementById("sortBy").value);
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 3 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByName(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      } else if (sortBy == 1 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByDate(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 2 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByDuration(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 4 && this.type == "cottage"){
        reservationServce.getSortedPastCottageReservationsByPrice(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 3 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByName(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      } else if (sortBy == 1 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByDate(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 2 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByDuration(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 4 && this.type == "ship"){
        reservationServce.getSortedPastShipReservationsByPrice(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 3 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByName(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      } else if (sortBy == 1 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByDate(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 2 && this.type == "adventure"){
        reservationServce.getSortedPastAdventureReservationsByDuration(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      }else if (sortBy == 4 && this.type == "adventure") {
        reservationServce.getSortedPastAdventureReservationsByPrice(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response => {
              this.handleResponse(response);
            })
      }else if (this.type == "ship")
        reservationServce.getPastShipReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then(response =>{
              this.handleResponse(response);
            })
      else if (this.type == "cottage")
        reservationServce.getPastCottageReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then((response) => {
                  this.handleResponse(response);
                }
            );
      else if (this.type == "adventure")
        reservationServce.getPastAdventureReservations(JSON.parse(localStorage.user).id, this.fromElement, this.numberOfElementsForDisplay)
            .then((response) => {
                  this.handleResponse(response);
                }
            );
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
      numberOfElementsForDisplay: 3,
      paginationKey: 0,
      listLength: 0,
      type: ""
    }
  }
}
</script>

<style scoped>

</style>
