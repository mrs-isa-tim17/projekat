<template>
    <div class="px-5">
      <basic-header></basic-header>
      <HomePageOffers></HomePageOffers>
    </div>
</template>

<script>
import HomePageOffers from "@/components/homePageOffers";
import BasicHeader from "@/components/basicHeader";
export default {
  components: {BasicHeader, HomePageOffers}
}
</script>

<style scoped>

</style>
