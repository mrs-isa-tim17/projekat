<template>
  <div id="instructor-home-page">
    <instructor-header></instructor-header>
    <div class="container">

      <div class="row justify-content-center">
        <instructorHomePageOption :image_path="adventureImgPath" :option_link="adventureLink"
                                  :text="adventureText"></instructorHomePageOption>

        <instructorHomePageOption :image_path="calendarImgPath" :option_link="calendarLink"
                                  :text="calendarText"></instructorHomePageOption>

        <instructorHomePageOption :image_path="availabilityImgPath" :option_link="availabilityLink"
                                  :text="availabilityText"></instructorHomePageOption>

        <instructorHomePageOption :image_path="fastReservationImgPath" :option_link="fastReservationLink"
                                  :text="fastReservationText"></instructorHomePageOption>
      </div>

      <div class="row justify-content-center ">
        <instructorHomePageOption :image_path="reserveImgPath" :option_link="reserveLink"
                                  :text="reserveText"></instructorHomePageOption>

        <instructorHomePageOption :image_path="historyOfReservationImgPath" :option_link="historyOfReservationLink"
                                  :text="historyOfReservationText"></instructorHomePageOption>


        <instructorHomePageOption :image_path="reportReservationImgPath" :option_link="reportReservationLink"
                                  :text="reportReservationText"></instructorHomePageOption>

        <instructorHomePageOption :image_path="businessReportImgPath" :option_link="businessReportLink"
                                  :text="businessReportText"></instructorHomePageOption>


      </div>
      <div class="row justify-content-center ">
        <instructorHomePageOption :image_path="profileImgPath" :option_link="profileLink"
                                  :text="profileText"></instructorHomePageOption>

        <request-for-deleting-account-modal :image_path="deleteAccImgPath" :option_link="deleteAccLink"
                                            :text="deleteAccText" :index="deleteAccModal" :header="deleteAccHeader"></request-for-deleting-account-modal>

      </div>
    </div>
  </div>
</template>

<script>
import instructorHomePageOption from "@/components/homePageOption";
import instructorHeader from "@/components/insrtuctorHeader";
import RequestForDeletingAccountModal from "@/components/RequestForDeletingAccountModal";

export default {
  name: "instructor-home",
  components: {
    RequestForDeletingAccountModal,
    instructorHomePageOption,
    instructorHeader
  },
  data() {
    return {
      adventureImgPath: require("@/assets/icons/adventurer.png"),
      adventureText: 'Vaše ponude',
      adventureLink: '/instructor/adventures',

      calendarImgPath: require("@/assets/icons/calendar.png"),
      calendarText: 'Kalendar',
      calendarLink: '...',

      availabilityImgPath: require("@/assets/icons/availability.png"),
      availabilityText: 'Definiši period dostupnosti',
      availabilityLink: '...',

      fastReservationImgPath: require("@/assets/icons/sale.png"),
      fastReservationLink: "...",
      fastReservationText: "Brze rezervacije",


      reserveImgPath: require("@/assets/icons/reserve.png"),
      reserveLink: "...",
      reserveText: "Rezerviši za klijenta",

      historyOfReservationImgPath: require("@/assets/icons/istorijaRezervacija.png"),
      historyOfReservationText: "Istorija rezervacija",
      historyOfReservationLink: "...",

      reportReservationImgPath: require("@/assets/icons/reportRes.png"),
      reportReservationLink: "...",
      reportReservationText: "Popuni izveštaj o rezervacijama",

      businessReportImgPath: require("@/assets/icons/reportBusiness.png"),
      businessReportLink: "...",
      businessReportText: "Izveštaji o poslovanju",


      deleteAccImgPath: require("@/assets/icons/deleteAcc.png"),
      deleteAccLink: "...",
      deleteAccText: "Brisanje naloga",
      deleteAccHeader: "Razlog za brisanje",
      deleteAccModal: "modalId",

      profileImgPath: require("@/assets/icons/profile.png"),
      profileLink: "...",
      profileText: "Profil"

    }
  }
}
</script>

<style scoped>

</style>
