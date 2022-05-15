<template>

  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>
  <div>
    <div class="row">
      <div class="col-3" style="width: 240px;">
        <site-adventure-search-nav></site-adventure-search-nav>
      </div>
      <div class="col">
        <site-adventure-list></site-adventure-list>
      </div>
    </div>
  </div>
</template>

<script>
import siteAdventureList from "@/components/main_site/siteAdventureList";
import BasicHeader from "@/components/main_site/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import SiteAdventureSearchNav from "@/components/main_site/siteAdventureSearchNav";
export default {
  name: "siteAdventureListView",
  components: {SiteAdventureSearchNav, ClientHeader, BasicHeader, siteAdventureList},
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
