<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>
    <div class="row">
      <div class="col-3" style="width: 240px;">
      </div>
      <div class="col">
        <site-ship-list></site-ship-list>
      </div>
    </div>
  </div>
</template>

<script>
import SiteShipList from "@/components/main_site/siteShipList";
import BasicHeader from "@/components/basicHeader";
import ClientHeader from "@/components/clientHeader";
export default {
  name: "siteShipListView",
  components: {ClientHeader, BasicHeader, SiteShipList},
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
