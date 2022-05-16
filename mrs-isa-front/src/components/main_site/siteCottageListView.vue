<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>
    <div  style="left: 2%; position: absolute;">
      <div class="input-group rounded">
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

    <div>
      <select style="right: 10%; width: 200px; position: absolute;" id="sortBy" @change="sortList" class="form-select" aria-label="Default select">
        <option selected>Sortiraj po</option>
        <option value="1">Naziv</option>
        <option value="2">Location</option>
        <option value="3">Ocena</option>
        <option value="4">Cena</option>
        <option value="5">Broj kreveta</option>
        <option value="6">Broj soba</option>
      </select>
    </div>
    <br>
    <br>

  <div class="row">
    <div class="col-3" style="width: 240px;">
      <site-cottage-search-nav @filter="filterCottages"></site-cottage-search-nav>
    </div>
    <div class="col">
      <site-cottage-list :cottages="cottages" :listLength="listLength" :key="cottagesKey"></site-cottage-list>
    </div>
  </div>

  </div>
</template>

<script>
import SiteCottageSearchNav from "@/components/main_site/siteCottageSearchNav";
import SiteCottageList from "@/components/main_site/siteCottageList";
import BasicHeader from "@/components/main_site/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import cottageServce from "@/servieces/CottageServce";
export default {
  name: "siteCottageListView",
  components: {ClientHeader, BasicHeader, SiteCottageList, SiteCottageSearchNav},
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
    searchCottages(){
      let searchByInput = document.getElementById("searchInput").value;
      console.log(searchByInput);
      let searchParam = {
        searchBy: searchByInput
      }
      cottageServce.search(searchParam)
          .then(response =>{
            this.cottages = response.data;
            this.listLength = this.cottages.length;
            this.forceRemounting();
          })
    },
    sortList(){
      let sortBy = document.getElementById("sortBy").value;
      if (sortBy == 1){
        cottageServce.sortCottageListByName(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 2){
        cottageServce.sortCottageListByLocation(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 3){
        cottageServce.sortCottageListByRating(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4){
        cottageServce.sortCottageListByPrice(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 5){
        cottageServce.sortCottageListByNumberOfBeds(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 6){
        cottageServce.sortCottageListByNumberOfRooms(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }
    },
    filterCottages(filterDto){
      console.log(filterDto);
      cottageServce.filterCottages(filterDto)
          .then((response) => {
              this.cottages = response.data;
              this.listLength = this.cottages.length;
              this.cottagesKey++;
              console.log(this.listLength);
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
      cottagesKey: 1
    }
  }
}
</script>

<style scoped>

</style>
