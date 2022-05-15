<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>

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
