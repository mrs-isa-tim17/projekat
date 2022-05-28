<template>
  <div id="admin-home-page">
    <admin-header></admin-header>
    <div class="container">

      <div class="row justify-content-center">
        <adminHomePageOption :image_path="requestForRegistrationImgPath" :option_link="registrationLink"
                             :text="registrationText"></adminHomePageOption>

        <adminHomePageOption :image_path="complaintImgPath" :option_link="complaintLink"
                             :text="complaintText"></adminHomePageOption>

        <adminHomePageOption :image_path="revisionImgPath" :option_link="registrationLink"
                             :text="revisionText"></adminHomePageOption>

      </div>

      <div class="row justify-content-center ">

        <adminHomePageOption :image_path="percentageImgPath" :option_link="penaltyLink"
                             :text="percentageText"></adminHomePageOption>

        <adminHomePageOption :image_path="businessReportImgPath" :option_link="businessReportLink"
                             :text="businessReportText"></adminHomePageOption>

        <adminHomePageOption :image_path="loyaltyImgPath" :option_link="loyaltyLink"
                             :text="loyaltyText"></adminHomePageOption>



      </div>
      <div class="row justify-content-center ">

        <div class="col-3 px-5" >
          <div class="dropdown">
            <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
              <div class="card-lg">
                <div class="card-body">
                  <img :src=offerImgPath height="150">
                </div>
                <div class="card-footer text-center text-primary">
                  {{offerText}}
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

        <adminHomePageOption :image_path="usersImgPath" :option_link="usersLink"
                             :text="usersText"></adminHomePageOption>

        <adminHomePageOption :image_path="penaltyImgPath" :option_link="penaltyLink"
                             :text="penaltyText"></adminHomePageOption>


      </div>

      <div class="row justify-content-center ">

        <adminHomePageOption :image_path="reportReservationImgPath" :option_link="reportReservationLink"
                             :text="reportReservationText"></adminHomePageOption>

        <adminHomePageOption :image_path="requestForDeletingAccImgPath" :option_link="requestForDeletingAccAccLink"
                             :text="requestForDeletingAccText"></adminHomePageOption>

        <adminHomePageOption :image_path="profileImgPath" :option_link="profileLink"
                             :text="profileText"></adminHomePageOption>

      </div>

    </div>
  </div>
</template>

<script>
import adminHomePageOption from "@/components/homePageOption";
import adminHeader from "@/components/admin/adminHeader";


export default {
  name: "adminHomePage",
  components: {
    adminHomePageOption,
    adminHeader,
  }
  , data() {
    return {
      requestForRegistrationImgPath: require("@/assets/icons/registration.png"),
      registrationText: 'Zahtevi za registraciju',
      registrationLink: '/admin/registration/request',

      revisionImgPath: require("@/assets/icons/revision.png"),
      revisionText: "Revizije",
      revisionLink: "...",

      complaintImgPath: require("@/assets/icons/angry.png"),
      complaintText: "Žalbe",
      complaintLink: "/admin/complaints",


      percentageImgPath: require("@/assets/icons/percentage.png"),
      percentageLink: "...",
      percentageText: "Procenat zarade",

      businessReportImgPath: require("@/assets/icons/reportBusiness.png"),
      businessReportLink: "...",
      businessReportText: "Izveštaji o poslovanju",

      loyaltyImgPath: require("@/assets/icons/loyalty.png"),
      loyaltyLink: "...",
      loyaltyText: "Loyalty program",




      offerImgPath: require("@/assets/icons/offer.png"),
      offerText: 'Sve ponude',
      offerLink: '...',

      usersImgPath: require("@/assets/icons/users.png"),
      usersText: 'Korisnici',
      usersLink: '/admin/users',

      penaltyImgPath: require("@/assets/icons/penalty.png"),
      penaltyText: "Penali",
      penaltyLink: "...",



      reportReservationImgPath: require("@/assets/icons/reportRes.png"),
      reportReservationLink: "...",
      reportReservationText: "Popuni izveštaj o rezervacijama",

      requestForDeletingAccImgPath: require("@/assets/icons/requesForDelete.png"),
      requestForDeletingAccAccLink: "/admin/delete/requests",
      requestForDeletingAccText: "Zahtevi za brisanje naloga",

      profileImgPath: require("@/assets/icons/profile.png"),
      profileLink: "...",
      profileText: "Profil"

    }
  }
}
</script>

<style scoped>

</style>