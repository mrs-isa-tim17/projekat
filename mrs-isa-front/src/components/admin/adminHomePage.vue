<template>
  <admin-header></admin-header>
  <div id="admin-home-page">

    <div class="container">

      <div class="row justify-content-center">
        <adminHomePageOption :image_path="requestForRegistrationImgPath" :option_link="registrationLink"
                             :text="registrationText"></adminHomePageOption>

        <adminHomePageOption :image_path="complaintImgPath" :option_link="complaintLink"
                             :text="complaintText"></adminHomePageOption>

        <adminHomePageOption :image_path="revisionImgPath" :option_link="revisionLink"
                             :text="revisionText"></adminHomePageOption>

      </div>

      <div class="row justify-content-center ">

        <adminHomePageOption :image_path="percentageImgPath" :option_link="percentageLink"
                             :text="percentageText"></adminHomePageOption>

        <adminHomePageOption :image_path="businessReportImgPath" :option_link="businessReportLink"
                             :text="businessReportText"></adminHomePageOption>


          <div class="col-3 px-5" >
            <div class="dropdown">
              <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                <div class="card-lg">
                  <div class="card-body">
                    <img :src=loyaltyImgPath height="150">
                  </div>
                  <div class="card-footer text-center text-primary">
                    {{loyaltyText}}
                  </div>
                </div>
              </a>

              <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <li><a class="dropdown-item" :href="'/admin/loyalty/scale'">Skala</a></li>
                <li><a class="dropdown-item" :href="'/admin/loyalty/points'">Poeni</a></li>
              </ul>
            </div>
          </div>

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
              <li><a class="dropdown-item" :href="'/admin/cottages'">Vikendice</a></li>
              <li><a class="dropdown-item" :href="'/admin/ships'">Brodovi</a></li>
              <li><a class="dropdown-item" href="/admin/adventures">Avanture</a></li>
            </ul>
          </div>
        </div>

        <adminHomePageOption :image_path="usersImgPath" :option_link="usersLink"
                             :text="usersText"></adminHomePageOption>

        <adminHomePageOption :image_path="penaltyImgPath" :option_link="penaltyLink"
                             :text="penaltyText"></adminHomePageOption>


      </div>

      <div class="row justify-content-center ">


        <adminHomePageOption :image_path="requestForDeletingAccImgPath" :option_link="requestForDeletingAccAccLink"
                             :text="requestForDeletingAccText"></adminHomePageOption>

        <adminHomePageOption :image_path="registerAdminImgPath" :option_link="registerAdminLink"
                             :text="registerAdminText"></adminHomePageOption>

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
      revisionLink: "/admin/revisions",

      complaintImgPath: require("@/assets/icons/angry.png"),
      complaintText: "Žalbe",
      complaintLink: "/admin/complaints",


      percentageImgPath: require("@/assets/icons/percentage.png"),
      percentageLink: '/admin/percentage',
      percentageText: "Procenat zarade",

      businessReportImgPath: require("@/assets/icons/reportBusiness.png"),
      businessReportLink: "/admin/business/report",
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
      penaltyText: "Izveštaji o rezervacijama i penali",
      penaltyLink: "/admin/reports",



      reportReservationImgPath: require("@/assets/icons/reportRes.png"),
      reportReservationLink: "...",
      reportReservationText: "Popuni izveštaj o rezervacijama",

      requestForDeletingAccImgPath: require("@/assets/icons/requesForDelete.png"),
      requestForDeletingAccAccLink: "/admin/delete/requests",
      requestForDeletingAccText: "Zahtevi za brisanje naloga",

      registerAdminImgPath: require("@/assets/icons/registerAdmin.png"),
      registerAdminLink: "/admin/register/new",
      registerAdminText: "Registracija admina"

    }
  }
}
</script>

<style scoped>

</style>