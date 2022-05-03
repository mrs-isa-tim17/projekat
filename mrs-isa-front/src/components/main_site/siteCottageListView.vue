<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>
  <div class="row">
    <div class="col-3" style="width: 240px;">
      <site-cottage-search-nav></site-cottage-search-nav>
    </div>
    <div class="col">
    <site-cottage-list></site-cottage-list>
    </div>
  </div>
  </div>
</template>

<script>
import SiteCottageSearchNav from "@/components/main_site/siteCottageSearchNav";
import SiteCottageList from "@/components/main_site/siteCottageList";
import BasicHeader from "@/components/basicHeader";
import ClientHeader from "@/components/clientHeader";
export default {
  name: "siteCottageListView",
  components: {ClientHeader, BasicHeader, SiteCottageList, SiteCottageSearchNav},
  created:
      function () {
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
    forceRemounting() {
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
    },
  },
  data() {
    return {
      verifiedClient: false,
      basicHeaderKey: 0,
      clientHeaderKey: 0
    }
  }
}
</script>

<style scoped>

</style>
