<template>

  <client-header></client-header>
  <div class="px-5">
    <HomePageOffers></HomePageOffers>
  </div>
</template>

<script>
import ClientHeader from "@/components/client/clientHeader";
import HomePageOffers from "@/components/main_site/main_home_page/homePageOffers";
export default {
  name: "homePageClient",
  components: {HomePageOffers, ClientHeader}
}
</script>

<style scoped>

</style>
