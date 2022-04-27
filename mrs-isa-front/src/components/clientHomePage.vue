<template>
  <div id="client-home-page">
    <!--<client-header></client-header>-->
    <clientHeader></clientHeader>
    <div class="container">
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

      <div class="row justify-content-center">
        <clientHomePageOption :image_path="cottageImgPath" :option_link="cottageLink" :text="cottageText"></clientHomePageOption>

        <clientHomePageOption :image_path="shipImgPath" :option_link="shipLink" :text="shipText"></clientHomePageOption>

        <clientHomePageOption :image_path="instructorImgPath" :option_link="instructorLink" :text="instructorText"></clientHomePageOption>
      </div>

      <div class="row justify-content-center ">
        <clientHomePageOption :image_path="upcomingImgPath" :option_link="upcomingLink" :text="upcomingText"></clientHomePageOption>

        <div class="col-3 px-5" >
          <div class="dropdown">
            <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
              <div class="card-lg">
                <div class="card-body">
                  <img :src=historyOfReservationImgPath height="150">
                </div>
                <div class="card-footer text-center text-primary">
                  {{historyOfReservationText}}
                </div>
              </div>
            </a>

            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <li><a class="dropdown-item" :href="historyOfCottageReservationsLink">Vikendice</a></li>
              <li><a class="dropdown-item" :href="historyOfShipReservationsLink">Brodovi</a></li>
              <li><a class="dropdown-item" href="#">Avanture</a></li>
            </ul>
          </div>
        </div>


        <clientHomePageOption :image_path="subscriptionImgPath" :option_link="subscriptionLink" :text="subscriptionText"></clientHomePageOption>
      </div>

      <div class="row justify-content-center ">
        <clientHomePageOption :image_path="complaintImgPath" :option_link="complaintLink" :text="complaintText"></clientHomePageOption>

        <clientHomePageOption :image_path="deleteAccImgPath" :option_link="deleteAccLink"
                       :text="deleteAccText"></clientHomePageOption>

        <clientHomePageOption :image_path="profileImgPath" :option_link="profileLink" :text="profileText"></clientHomePageOption>
      </div>
    </div>

  </div>
</template>

<script>
import clientHomePageOption from "@/components/clientHomePageOption";
import clientHeader from "@/components/clientHeader";
export default {
  name: "client-home",
  components: {
    clientHomePageOption,
    clientHeader
  },
  data() {
    return {
      penalties: 3,
      textOnePanelty: "Ups, imate jedan penal.\nAko sakupite 3 panele, ne možete više da rezervišete u tom mesecu.",
      textTwoPanelty: "Budite oprezni imate 2 panele. \nAko dobijete treći panel, ne možete da koristite sajt do kraja meseca.",
      textThreePanelty: "Imate 3 panele, do kraja meseca ne možete da rezervišete entitete.",

      cottageImgPath : require("@/assets/icons/wood-house-color.png"),
      cottageText: 'Vikendice',
      cottageLink: '...',

      shipImgPath : require("@/assets/icons/ship.png"),
      shipText: 'Brodovi',
      shipLink: '...',

      instructorImgPath: require("@/assets/icons/fishing.png"),
      instructorText: 'Instrukture',
      instructorLink: '...',

      upcomingImgPath: require("@/assets/icons/booking.png"),
      upcomingLink: "...",
      upcomingText: "Zakazane rezervacije",

      historyOfReservationImgPath: require("@/assets/icons/istorijaRezervacija.png"),
      //historyOfReservationLink: "...",
      historyOfReservationText: "Istorija rezervacija",
      historyOfCottageReservationsLink: "/client/history/cottage",
      historyOfShipReservationsLink: "/client/history/ship",


      subscriptionImgPath: require("@/assets/icons/subscription.png"),
      subscriptionLink: "...",
      subscriptionText: "Pretplaćeni entiteti",

      complaintImgPath: require("@/assets/icons/angry.png"),
      complaintLink: "...",
      complaintText: "Žalbe",

      deleteAccImgPath: require("@/assets/icons/deleteAcc.png"),
      deleteAccLink:"...",
      deleteAccText: "Brisanje naloga",

      profileImgPath: require("@/assets/icons/profile.png"),
      profileLink: "/client/profile/1",
      profileText: "Profil"

    }
  }
}
</script>

<style scoped>

</style>