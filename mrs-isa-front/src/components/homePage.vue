<template>
    <div class="px-5">
      <basic-header></basic-header>
      <HomePageOffers :key="siteHomePage" @rerender="forceRerender"></HomePageOffers>
    </div>
</template>

<script>
import HomePageOffers from "@/components/homePageOffers";
import BasicHeader from "@/components/basicHeader";
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
