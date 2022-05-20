<template>
  <basic-header :key="basicHeaderKey" v-show="!verifiedClient"></basic-header>
  <client-header :key="clientHeaderKey" v-show="verifiedClient"></client-header>

  <div class="container text-black">
    <h3  style="margin-left: 10%;">{{offer.name}}</h3>

    <div class="grid">

      <div class="row align-items-start">

        <div class="row g-0">
          <div class="col-md-4">
            <offer-carousel  :images="offer.images"></offer-carousel>
            <bird-vue-map style="height: 300px; margin-top: 2%; " :lon="offer.longitude" :lat="offer.latitude"></bird-vue-map>
          </div>

          <div class="col-md-8">
            <div class="card-body">
              <adventure-info-display :offer="offer"></adventure-info-display>
            </div>
          </div>

        </div>

      </div>


      <div class="row g-0">

        <div class="col-5 my-2" style="height: 300px;">
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import BasicHeader from "@/components/main_site/main_home_page/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import OfferCarousel from "@/components/main_site/offer_profile/offerCarousel";
import BirdVueMap from "@/components/main_site/birdVueMap";
import adventureServce from "@/servieces/AdventureServce";
import AdventureInfoDisplay from "@/components/main_site/adventure_profile/adventureInfoDisplay";

export default {
  name: "shipProfile",
  components: {AdventureInfoDisplay, BirdVueMap, OfferCarousel, ClientHeader, BasicHeader},
  created:
      function () {
        this.offerId = this.$route.params.id;
        adventureServce.getAdvanture(this.offerId).then((response) => {
          this.offer = response.data;
          console.log(this.offer);
        })

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
  methods:{
    forceRemounting() {
      this.clientHeaderKey += 1;
      this.basicHeaderKey += 1;
      this.adventuresKey += 1;
    }
  },
  data() {
    return {
      verifiedClient: false,
      basicHeaderKey: 0,
      clientHeaderKey: 0,
      offer: {
        id: "",
        name: "",
        longitude: 0,
        latitude: 0,
        description: "",
        behavioralRules: ["", "", ""],
        images: [""],
        additionalServices: ["", "", ""],
        price: "",
        rating: "",
        biography: "",
        instructorBiography: [""],
        fishingEquipments: [""]
      }
    }
  }

}
</script>

<style scoped>

</style>
