<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>

    <div  style="left: 2%; position: absolute;">
      <div class="input-group rounded">
        <input v-model="searchBy" id="searchInput" type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
        <button style="border-width: 0;" @click="searchAdventures">
        <span class="input-group-text border-0" id="search-addon">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
        </span>
        </button>
      </div>

    </div>
    <div>
      <select style="right: 10%; width: 200px; position: absolute;" id="sortBy" @change="sortList" class="form-select" aria-label="Default select">
        <option selected>Sortiraj po</option>
        <option value="1">Naziv</option>
        <option value="2">Location</option>
        <option value="3">Ocena</option>
        <option value="4">Cena</option>
        <option value="5">Kapacitet</option>
      </select>
    </div>
    <br>
    <br>

    <div class="row">
      <div class="col-3" style="width: 240px;">
        <site-adventure-search-nav @filter="filterAdventures"></site-adventure-search-nav>
      </div>
      <div class="col">
        <site-adventure-list :cottages="cottages" :listLength="listLength" :key="adventuresKey"></site-adventure-list>
      </div>
    </div>
  </div>
</template>

<script>
import siteAdventureList from "@/components/main_site/siteAdventureList";
import BasicHeader from "@/components/main_site/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import SiteAdventureSearchNav from "@/components/main_site/siteAdventureSearchNav";
import adventureServce from "@/servieces/AdventureServce";
export default {
  name: "siteAdventureListView",
  components: {SiteAdventureSearchNav, ClientHeader, BasicHeader, siteAdventureList},
  created:
      function () {
        adventureServce.getAdventures().then(
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
    searchAdventures(){
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

    },
    sortList(){
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 1){
        adventureServce.sortAdventureListByName(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 2){
        adventureServce.sortAdventureListByLocation(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 3){
        adventureServce.sortAdventureListByRating(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4){
        adventureServce.sortAdventureListByPrice(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 5){
        adventureServce.sortAdventureListByCapacity(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }
    },

    filterAdventures(filterDto){
      console.log(filterDto);
      filterDto.searchBy = this.searchBy;
      adventureServce.filterAdventures(filterDto)
          .then((response) => {
                this.cottages   = response.data;
                this.listLength = this.cottages.length;
                this.adventuresKey++;
                console.log(this.listLength);
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
      searchBy: ""
    }
  }
}
</script>
