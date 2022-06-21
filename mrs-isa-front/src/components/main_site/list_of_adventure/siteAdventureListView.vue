<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>

    <div>
    <div class="row">
      <div class="col mx-4 d-flex justify-content-start my-1" style="width: 300px;" >
        <div style="width: 278px;" class="input-group rounded">
          <input id="searchInput" type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
          <button style="border-width: 0;" @click="searchAdventures">
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
          <option value="2">Lokacija</option>
          <option value="3">Ocena</option>
          <option value="4">Cena</option>
          <option value="5">Kapacitet</option>
        </select>
      </div>
    </div>
    </div>

    <div class="row">
      <div class="col-3">
        <site-adventure-search-nav @filter="filterAdventures"></site-adventure-search-nav>
      </div>
      <div class="col">
        <site-adventure-list :cottages="cottages" :listLength="listLength" :key="adventuresKey"
                             :fromElement="fromElement" :numberOfElementsForDisplay="numberOfElementsForDisplay" @get-list-from="getAdventureList"
                            ></site-adventure-list>
      </div>
    </div>
  </div>
</template>

<script>
import siteAdventureList from "@/components/main_site/list_of_adventure/siteAdventureList";
import BasicHeader from "@/components/main_site/main_home_page/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import SiteAdventureSearchNav from "@/components/main_site/list_of_adventure/siteAdventureSearchNav";
import adventureServce from "@/servieces/AdventureServce";
export default {
  name: "siteAdventureListView",
  components: {SiteAdventureSearchNav, ClientHeader, BasicHeader, siteAdventureList},
  created:
      function () {
        this.getAdventureList(0);
    /*
        adventureServce.getAdventures().then(
            (response) => {
              this.cottages = response.data;
              this.listLength = this.cottages.length;

            }
        )*/
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
    getAdventureList(fromElement){
      if (isNaN(fromElement)) {
        fromElement = 0;
      }
      this.callFilterAdventues(fromElement);
    },
    searchAdventures(){
      let searchByInput = document.getElementById("searchInput").value;
      this.filterDto.searchBy = searchByInput;
      this.searchBy = searchByInput;
      this.callFilterAdventues(0);
      /*
      let searchByInput = document.getElementById("searchInput").value;
      let searchParam = {
        searchBy: searchByInput
      }

      adventureServce.search(searchParam)
          .then(response =>{
            this.cottages = response.data;
            this.listLength = this.cottages.length;
            this.forceRemounting();
          })
*/
    },
    callFilterAdventues(fromElement){
      this.fromElement = fromElement;
      adventureServce.filterAdventures(this.filterDto, fromElement, this.numberOfElementsForDisplay)
          .then((response) => {
                let listLengthObj = response.data[0];
                this.listLength = listLengthObj.listSize;
                if (this.listLength > 0)
                  this.cottages = response.data.slice(1);
                else
                  this.cottages = [];
                this.adventuresKey += 1;
              }
          )
    },
    sortList(){
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 1){
        this.filterDto.sortBy = "name";
        this.callFilterAdventues(0);
      } else if (sortBy == 2){
        this.filterDto.sortBy = "location";
        this.callFilterAdventues(0);
      }else if (sortBy == 3){
        this.filterDto.sortBy = "rating";
        this.callFilterAdventues(0);
      }else if (sortBy == 4){
        this.filterDto.sortBy = "price";
        this.callFilterAdventues(0);
      }else if (sortBy == 5){
        this.filterDto.sortBy = "capacity";
        this.callFilterAdventues(0);
      }
    },

    filterAdventures(filterDto){
      filterDto.searchBy = this.searchBy;
      filterDto.sortBy = this.filterDto.sortBy;
      this.filterDto = filterDto;
      adventureServce.filterAdventures(this.filterDto, 0, this.numberOfElementsForDisplay)
          .then((response) => {
                let listLengthObj = response.data[0];
                this.listLength = listLengthObj.listSize;
                console.log("LIST LENGTH");
                console.log(this.listLength);
                if (this.listLength > 0) {
                  this.cottages = response.data.slice(1);
                }else{
                  this.cottages = [];
                }
                this.fromElement = 0;
                this.adventuresKey += 1;
                localStorage.adventure = JSON.stringify(filterDto);
              }
          )
    },
    forceRemounting() {
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
      this.adventuresKey += 1;
    },
  },
  data() {
    return {
      cottages: [],
      listLength: 0,

      verifiedClient: false,
      basicHeaderKey: 0,
      clientHeaderKey: 0,

      adventuresKey: 0,
      searchBy: "",
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
      numberOfElementsForDisplay: 2,
      fromElement: 0
    }
  }
}
</script>
