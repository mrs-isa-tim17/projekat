<template>
  <div class="d-flex justify-content-center mw-100">
    <div class="card mb-3 mw-100" style="width: 80%;">

      <div class="row g-0" style="background-color: #E9E9E9;">

        <div class="col-md-4"  @click="goToOffer">
          <img :src="image" class="img-fluid rounded-start" data-toggle="tooltip" data-placement="right" title="Poseti stranicu" :alt="defaultImage">
        </div>

        <div class="col-md-8">
          <div class="card-body text-black ">

            <h5  class="card-title d-flex justify-content-lg-center" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</h5>

            <div class="card-text ">
              <div class="row">
                <div class="col  d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                  {{cottage.description}}
                  <br>
                  <br>
                  Cena: {{parseFloat(cottage.price).toFixed(2)}}
                  <br>
                  Ocena: {{parseFloat(cottage.mark).toFixed(2)}}
                  <br>
                  Broj soba: {{cottage.roomQuantity}}
                  <br>
                  Broj kreveta: {{cottage.bedQuantity}}
                </div>

                <div class="col">
                  <bird-vue-map :index="cottage.id" style="width: 280px; height: 200px;" :lon="cottage.longitude" :lat="cottage.latitude"></bird-vue-map>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BirdVueMap from "@/components/main_site/birdVueMap";
export default {
  name: "siteCottageElement",
  components: {BirdVueMap},
  props: ["cottage"],
  methods:{
    goToOffer(){
      this.$router.push('/book/cottage/site/'+this.cottage.id);
    },
  },
  created() {
    if (this.cottage.image != null) {
      this.image = this.cottage.image;
    }else {
      this.image = require('@/assets/icons/cottage.png');
    }
  },
  data(){
    return {
      image: ""
    }
  }
}
</script>

<style scoped>

</style>
