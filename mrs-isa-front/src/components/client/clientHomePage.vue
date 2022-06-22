<template>
  <div id="client-home-page" >
    <!--<client-header></client-header>-->
    <clientHeader></clientHeader>
    <div class="container">
      <div class="alert alert-danger" role="alert" v-if="deleteRequestMade">
        {{deleteRequestMadeMessage}}
      </div>

      <div class="alert alert-danger" role="alert" v-if="penalties == 3">
        {{textThreePanelty}}
      </div>

      <div class="alert alert-warning  alert-dismissible fade show" role="alert" v-if="penalties == 2">
        {{textTwoPanelty}}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>

      <div class="alert alert-secondary  alert-dismissible fade show" role="alert" v-if="penalties == 1">
        {{textOnePanelty}}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <HomePageOffers></HomePageOffers>
    </div>
  </div>
</template>

<style>

[v-cloak] {
  display: none
}
</style>
<script>
import clientHeader from "@/components/client/clientHeader";
import ClientServce from "@/servieces/ClientServce";
import HomePageOffers from "@/components/main_site/main_home_page/homePageOffers";
export default {
  name: "client-home",
  components: {
    HomePageOffers,
    clientHeader
  },
  methods:{
  },
  created:
      function () {
        try {
          this.clientID = JSON.parse(localStorage.user).id;//this.$route.params.id;
          ClientServce.getClientPenalties(this.clientID)
              .then((response) => {
                console.log("Response" + response);
                this.penalties = response.data.penalties;
                this.deleteRequestMade = response.data.deleteRequestMade;
                if (this.deleteRequestMade)
                  localStorage.user = null;
              })
        }catch(error){
          console.log(error);
          this.penalties = 0;
          this.deleteRequestMade = true;
        }
      },
  data() {
    return {
      penalties: 0,
      deleteRequestMade: false,
      deleteRequestMadeMessage: "Napravili ste zahtev za brisanje dok zahtev nije odbijen ne možete više koristiti aplikaciju",

      textOnePanelty: "Ups, imate jedan penal.\nAko sakupite 3 panele, ne možete više da rezervišete u tom mesecu.",
      textTwoPanelty: "Budite oprezni imate 2 panele. \nAko dobijete treći panel, ne možete da koristite sajt do kraja meseca.",
      textThreePanelty: "Imate 3 panele, do kraja meseca ne možete da rezervišete entitete.",

      cottageImgPath : require("@/assets/icons/wood-house-color.png"),
      cottageText: 'Vikendice',
      cottageLink: "/book/cottage/site",

      shipImgPath : require("@/assets/icons/ship.png"),
      shipText: 'Brodovi',
      shipLink: "/book/ship/site",

      instructorImgPath: require("@/assets/icons/fishing.png"),
      instructorText: 'Instrukture',
      instructorLink: "/book/adventure/site",

      upcomingImgPath: require("@/assets/icons/booking.png"),
      upcomingLink: "...",
      upcomingText: "Zakazane rezervacije",

      historyOfReservationImgPath: require("@/assets/icons/istorijaRezervacija.png"),
      //historyOfReservationLink: "...",
      historyOfReservationText: "Istorija rezervacija",
      historyOfCottageReservationsLink: "/client/history/cottage",
      historyOfShipReservationsLink: "/client/history/ship",
      historyOfAdventureReservationsLink: "/client/history/adventure",


      subscriptionImgPath: require("@/assets/icons/subscription.png"),
      subscriptionLink: "...",
      subscriptionText: "Pretplaćeni entiteti",

      complaintImgPath: require("@/assets/icons/angry.png"),
      complaintLink: "...",
      complaintText: "Žalbe",

      deleteAccImgPath: require("@/assets/icons/deleteAcc.png"),
      deleteAccLink:"...",
      deleteAccText: "Brisanje naloga",
      deleteAccHeader: "Razlog za brisanje",
      deleteAccModal: "modalId",

      profileImgPath: require("@/assets/icons/profile.png"),
      profileLink: "/client/profile",
      profileText: "Profil",

    }
  }
}
</script>

<style scoped>

</style>
