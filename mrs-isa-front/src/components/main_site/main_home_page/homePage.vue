<template>
  <basic-header></basic-header>
    <div class="px-5 p-5">

      <HomePageOffers :key="siteHomePage" @rerender="forceRerender"></HomePageOffers>
    </div>
</template>

<script>
import HomePageOffers from "@/components/main_site/main_home_page/homePageOffers";
import BasicHeader from "@/components/main_site/main_home_page/basicHeader";
export default {
  components: {BasicHeader, HomePageOffers},
  methods: {
    forceRerender(){
      this.siteHomePage++;
    }
  },
  data(){
    return{
      siteHomePage: 1
    }
  }
}
</script>

<style scoped>

</style>
