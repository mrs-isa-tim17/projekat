<template>

  <div class="px-5">
    <homePageCarouselLeft :type="cottageType" :key="cottageKey" :text="cottageTextBgColor" :offers="cottages" :carouselId="adventureId" :title="cottageTitle" :description="cottageDescription"></homePageCarouselLeft>

    <homePageCarouselRight :type="adventureType" :key="adventureKey" :text="adventureTextBgColor" :offers="adventures" :title="adventureTitle" :description="adventureDescription"></homePageCarouselRight>

    <homePageCarouselLeft :type="shipType" :key="shipKey" :text="shipTextBgColor" :offers="ships" :carousel-id="shipId" :title="shipTitle" :description="shipDescription"></homePageCarouselLeft>
  </div>
</template>

<script>
import homePageCarouselLeft from "@/components/main_site/main_home_page/homePageCarouselLeft";
import homePageCarouselRight from "@/components/main_site/main_home_page/homePageCarouselRight";
import AdventureServce from "@/servieces/AdventureServce";
import ShipServce from "@/servieces/ShipServce";
import CottageServce from "@/servieces/CottageServce";

export default {
  name: "homePageOffers",
  components: {
    homePageCarouselLeft,
    homePageCarouselRight
  },
  created() {
        try {

          CottageServce.getCottagesForHomePageView()
              .then( response =>{
                this.cottages = response.data;
                this.cottageResponse = true;
                this.forceCottageRemount();
              });

          ShipServce.getShipsForHomePageView()
              .then((response) => {
                this.ships = response.data;
                this.forceShipRemount();
                this.shipKey++;
              })


          AdventureServce.getAdventuresForHomePageView()
              .then((response) => {
                    this.adventures = response.data;
                    this.forceAdventureRemount();
                  }
              )


        } catch (error) {
          console.log(error);
        }
      },
  methods:{
    forceCottageRemount(){
      this.cottageKey++;
    },
    forceShipRemount(){
      this.shipKey++;
    },
    forceAdventureRemount(){
      this.adventureKey++;
    }
  },
  data() {
    return {
      cottageResponse: false,
      shipResponse: false,
      adventureResponse: false,

      not_logged_in : false,
      client_logged_in: false,

      offer: {
        name: "",
        description: "",
        images: ["", ""]
      },
      adventureTextBgColor: "background-color: #687864;",
      adventures:[{

        name: "",
        description: "",
        images: [undefined]
      } ],
      adventureId: "adventure",
      adventureTitle: "Najbolje adventure",
      adventureDescription: "Mnogo insktruktora, još više mogućnosti za pecanje, najbloji kvalitet,......",
      cottages: [ {
        name: "",
        description: "",
        images: [undefined]}],
      cottageTitle: "Najbolje vikendice",
      cottageDescription: "Mnogo vikendice, najbloji kvalitet,......",
      cottageId: "cottage",
      cottageTextBgColor: "background-color: #88BBD6;",
      ships: [{
        name: "",
        description: "",
        images: [undefined]} ],
      shipId: "ship",
      shipTitle: "Najbolji brodovi",
      shipDescription: "Mnogo brodova, najbloji kvalitet,......",
      shipTextBgColor: "background-color: #31708E;",
      cottageKey: 1,
      shipKey: 1,
      adventureKey: 1,
      cottageType: "cottage",
      shipType: "ship",
      adventureType: "adventure"
    }
  }
}
</script>

<style scoped>

</style>
