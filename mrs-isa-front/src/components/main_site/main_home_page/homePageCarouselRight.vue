<template>
  <div class="row" :style="text">


    <div class="col g-0">
      <h3 class="text-black"> {{ title }} </h3>
      <br>
      <p class="text-black">{{ description }}</p>


    </div>

    <div class="col">
      <div style="min-width: 100px;" id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">

        <div class="carousel-inner px-2" >

          <div class="carousel-item active" @click="goToOffer(offers[0].id)">
            <img :src="genereteImgUrl(offers[0])"   class="d-block w-100" alt="img">
            <div class="carousel-caption d-none d-md-block" style="background-color: #E9E9E9; opacity: 0.5;">>
              <h5 class="text-black" style="opacity: 1;">{{offers[0].name}}</h5>
            </div>
          </div>

          <div class="carousel-item" v-for="(item) in filterOffers()" :key="item.id">
            <img :src="genereteImgUrl(item)" @click="goToOffer(item.id)" class="d-block w-100" alt="img">
            <div class="carousel-caption d-none d-md-block" style="background-color: #E9E9E9; opacity: 0.5;">>
              <h5 class="text-black" style="opacity: 1;">{{item.name}}</h5>
            </div>
          </div>

        </div>


        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>

        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: "homePageCarouselRight",
  props: ["offers", "title", "description", "text", "type"],
  methods: {
    genereteImgUrl(offer){
      if (offer !== undefined && offer.images !== undefined && offer.images[0] !== undefined)
          return offer.images[0];
      else
        return require('@/assets/icons/img.jpg');
      //return require('@/assets/icons/adventurer.png');
    },
    filterOffers(){
      if (this.offers.length > 1){
        return this.offers.slice(1);
      }
    },
    goToOffer(offer){
      this.$router.push("/book/"+ this.type +"/site/" + offer);
      //console.log(offer);
    }
  }
}
</script>

<style scoped>

</style>
