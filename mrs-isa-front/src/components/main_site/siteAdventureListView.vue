<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>

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
      adventuresKey: 0
    }
  }
}
</script>
