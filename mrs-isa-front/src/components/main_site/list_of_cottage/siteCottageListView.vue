<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>

    <div class="row my-1">
      <div class="col mx-4 d-flex justify-content-start my-1" style="width: 300px;" >
        <div style="width: 278px;" class="input-group rounded">
          <input id="searchInput" type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
          <button style="border-width: 0;" @click="searchCottages">
          <span class="input-group-text border-0" id="search-addon">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
          </span>
          </button>
        </div>
      </div>

      <div class="col d-flex justify-content-end mr-3 my-1" style="width: 200px;" >
        <select style="width: 200px; margin-right: 100px; margin-left: 25px;" id="sortBy" @change="sortList" class="form-select" aria-label="Default select">
          <option selected>Sortiraj po</option>
          <option value="1">Naziv</option>
          <option value="2">Location</option>
          <option value="3">Ocena</option>
          <option value="4">Cena</option>
          <option value="5">Broj kreveta</option>
          <option value="6">Broj soba</option>
        </select>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-3">
      <site-cottage-search-nav @filter="filterCottages"></site-cottage-search-nav>
    </div>
    <div class="col">
      <site-cottage-list :fromElement="fromElement" :numberOfElementsForDisplay="numberOfElementsForDisplay" @get-list-from="getCottageList" :cottages="cottages" :listLength="listLength" :key="cottagesKey"></site-cottage-list>
    </div>
  </div>

</template>

<script>
import SiteCottageSearchNav from "@/components/main_site/list_of_cottage/siteCottageSearchNav";
import SiteCottageList from "@/components/main_site/list_of_cottage/siteCottageList";
import BasicHeader from "@/components/main_site/main_home_page/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import cottageServce from "@/servieces/CottageServce";
export default {
  name: "siteCottageListView",
  components: {ClientHeader, BasicHeader, SiteCottageList, SiteCottageSearchNav},
  created:
      function () {
        this.getCottageList(0);
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
    getCottageList(fromElement){
      if (isNaN(fromElement)) {
        fromElement = 0;
      }
      this.callFilterCottages(fromElement);
    },
    searchCottages(){
      let searchByInput = document.getElementById("searchInput").value;
      this.filterDto.searchBy = searchByInput;
      this.searchBy = searchByInput;
      this.callFilterCottages(0);
    },
    callFilterCottages(fromElement){
      this.fromElement = fromElement;
      cottageServce.filterCottages(this.filterDto, fromElement, this.numberOfElementsForDisplay)
          .then((response) => {
                let listLengthObj = response.data[0];
                this.listLength = listLengthObj.listSize;
                if (this.listLength > 0)
                  this.cottages = response.data.slice(1);
                else
                  this.cottages = [];
                this.cottagesKey += 1;
              }
          )
    },
    sortList(){
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 1){
        this.filterDto.sortBy = "name";
        this.callFilterCottages(0);
      } else if (sortBy == 2){
        this.filterDto.sortBy = "location";
        this.callFilterCottages(0);
      }else if (sortBy == 3){
        this.filterDto.sortBy = "rating";
        this.callFilterCottages(0);
      }else if (sortBy == 4){
        this.filterDto.sortBy = "price";
        this.callFilterCottages(0);
      }else if (sortBy == 5){
        this.filterDto.sortBy = "beds";
        this.callFilterCottages(0);
      }else if (sortBy == 6){
        this.filterDto.sortBy = "rooms";
        this.callFilterCottages(0);
      }else {
        this.filterDto.sortBy="";
      }
    },
    filterCottages(filterDto){
      filterDto.searchBy = this.searchBy;
      filterDto.sortBy = this.filterDto.sortBy;
      this.filterDto = filterDto;
      cottageServce.filterCottages(this.filterDto, 0, this.numberOfElementsForDisplay)
          .then((response) => {
            let listLengthObj = response.data[0];
            this.listLength = listLengthObj.listSize;
            if (this.listLength > 0)
              this.cottages = response.data.slice(1);
            else
              this.cottages = [];
            this.fromElement = 0;
            this.cottagesKey += 1;
            localStorage.cottage = JSON.stringify(filterDto);
              }
          )
    },
    forceRemounting() {
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
      this.cottagesKey += 1;
    },
  },
  data() {
    return {
      cottages: [],
      listLength: 0,

      verifiedClient: false,
      basicHeaderKey: 0,
      clientHeaderKey: 0,

      cottagesKey: 1,
      filterDto: {
        longitude: 0,
        latitude: 0,
        dateFrom: "",
        dateUntil: "",
        ratingRelOp: "",
        rating: "",
        roomsRelOp: "",
        numberOfRooms: "",
        bedsRelOp: "",
        numberOfBed: "",
        priceRelOp: "",
        price: "",
        searchBy: "",
        sortBy: ""
      },
      searchBy: "",
      numberOfElementsForDisplay: 2,
      fromElement: 0
    }
  }
}
</script>

<style scoped>

</style>
