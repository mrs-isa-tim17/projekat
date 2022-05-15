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
        <option value="5">Brzina</option>
        <option value="6">Kapacitet</option>
      </select>
    </div>
    <br>
    <br>

    <div class="row">
      <div class="col-3" style="width: 240px;">
        <site-ship-search-nav @filter="filterShips"></site-ship-search-nav>
      </div>
      <div class="col">
        <site-ship-list :cottages="cottages" :listLength="listLength" :key="shipsKey"></site-ship-list>
      </div>
    </div>
  </div>
</template>

<script>
import SiteShipList from "@/components/main_site/siteShipList";
import BasicHeader from "@/components/main_site/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import SiteShipSearchNav from "@/components/main_site/siteShipSearchNav";
import shipServce from "@/servieces/ShipServce";
export default {
  name: "siteShipListView",
  components: {SiteShipSearchNav, ClientHeader, BasicHeader, SiteShipList},
  created:
      function () {
        shipServce.getShips().then(
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
        shipServce.sortShipListByName(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      } else if (sortBy == 2){
        shipServce.sortShipListByLocation(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 3){
        shipServce.sortShipListByRating(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 4){
        shipServce.sortShipListByPrice(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 5){
        shipServce.sortShipListBySpeed(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }else if (sortBy == 6){
        shipServce.sortShipListByCapacity(this.cottages)
            .then(response =>{
              this.cottages = response.data;
              this.forceRemounting();
            })
      }
    },
    filterShips(filterDto){
      shipServce.filterShips(filterDto)
          .then((response) => {
                this.cottages = response.data;
                this.listLength = this.cottages.length;
                this.shipsKey++;
                console.log(this.listLength);
              }
          )
    },
    forceRemounting() {
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
      this.shipsKey += 1;
    },
  },
  data() {
    return {
      cottages: [],
      listLength: 0,
      verifiedClient: false,
      basicHeaderKey: 0,
      clientHeaderKey: 0,
      shipsKey: 1
    }
  }
}
</script>

<style scoped>

</style>
