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
              <cottage-info-display :key="infoDisplayKey" :client="verifiedClient" :offer="offer"></cottage-info-display>
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
import OfferCarousel from "@/components/main_site/offer_profile/offerCarousel";
import BasicHeader from "@/components/main_site/main_home_page/basicHeader";
import ClientHeader from "@/components/client/clientHeader";
import cottageServce from "@/servieces/CottageServce";
import BirdVueMap from "@/components/main_site/birdVueMap";
import CottageInfoDisplay from "@/components/main_site/cottage_profile/cottageInfoDisplay";
export default {
  name: "cottageProfile",
  components: {CottageInfoDisplay, BirdVueMap, ClientHeader, BasicHeader, OfferCarousel},
  created:
      function () {
        this.offerId = this.$route.params.id;
          cottageServce.getCottage(this.offerId).then((response) => {
            this.offer = response.data;
            console.log(this.offer);
            this.infoDisplayKey++;

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
      infoDisplayKey: 0,
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
        additionalServicesPrice: ["", "", ""],
        price: "",
        numberOfRooms: "",
        numberOfBeds: "",
        rating: ""
      }
    }
  }
}
</script>

<style scoped>

</style>
