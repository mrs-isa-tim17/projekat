<template>
  <div class="row">
    <div class="col-6">
      <div :id=carouselId class="carousel slide" data-bs-ride="carousel">


        <div class="carousel-inner px-2" >

          <div class="carousel-item active" @click="goToOffer(item)">
            <img :src="offers[0].images[0]" class="d-block w-100" alt="...">
              <div class="carousel-caption d-none d-md-block" style="background-color: #E9E9E9; opacity: 0.5;">

                <h5 class="text-black" style="opacity: 1;">{{offers[0].name}}</h5>
                <h6 class="text-black" style="opacity: 1;">{{offers[0].address.country}}, {{offers[0].address.city}}</h6>
                <p class="text-black" style="opacity: 1;">{{ offers[0].description }}</p>
              </div>
          </div>

          <div class="carousel-item" v-for="(item) in filterOffers()" :key="item.id" @click="goToOffer(item)">
            <img :src="item.images[0]" class="d-block w-100" alt="..." >
              <div class="carousel-caption d-none d-md-block" style="background-color: #E9E9E9; opacity: 0.5;">
                <h5 class="text-black" style="opacity: 1;">{{item.name}}</h5>
                <h6 class="text-black" style="opacity: 1;">{{item.address.country}}, {{item.address.city}}</h6>
                <p class="text-black" style="opacity: 1;">{{ item.description }}</p>
              </div>
          </div>

        </div>


        <button class="carousel-control-prev" type="button" :data-bs-target=carouselHash data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>

        <button class="carousel-control-next" type="button" :data-bs-target=carouselHash data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>

    <div class="col-6">
      <h3> {{ title }} </h3>
      <br>
      <p>{{ description }}</p>

    </div>

  </div>

</template>

<script>
export default {
  name: "homePageCarouselLeft",
  props: ["offers", "carouselId", "title", "description"],
  created:
      function () {
        this.carouselHash = "#"+this.carouselId;
      }
  ,
  methods: {
    filterOffers(){
      if (this.offers.length > 1){
        return this.offers.slice(1);
      }
    },
    goToOffer(offer){
      this.$router.push("...");
      console.log(offer.id);
    }
  }
}
</script>

<style scoped>

</style>