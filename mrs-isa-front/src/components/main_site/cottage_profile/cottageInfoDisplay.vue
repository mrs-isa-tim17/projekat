<template>
    <div align="left">
      <div class="row">
        <div class="col-9">
          <p>{{offer.description}}</p>
        </div>
        <div class="col">
          <div class="row p-1">
              <res-mod :offer-type="'cottage'" :price="offer.price" :verified-client="verifiedClient" :additionalServices="offer.additionalServices" :additionalServicesPrice="offer.additionalServicesPrice"></res-mod>
              <button v-show="!verifiedClient" style="min-width: 150px; " @click="reserveOffer" class="btn btn-secondary"> Rezerviši </button>
          </div>
          <div class="row p-1">
            <button style="min-width: 150px; " @click="subscribeToTheOffer" class="btn btn-secondary"> Prati </button>
          </div>
          <div align="left" class="row p-1">
              <quick-reservation-modal :key="saleAppointmentKey" @sale-modal-rerender="rerender" :verifiedClient="verifiedClient" :offerId="offerId"></quick-reservation-modal>
              <button v-show="!verifiedClient" style="min-width: 150px;" @click="viewQuickReservation" class="btn btn-secondary" type="button">Brze rezervacije</button>
          </div>
          <quick-reservation-modal></quick-reservation-modal>
        </div>
      </div>
    </div>
    <div align="left" class="row">
      <div class="col m-2" >
        <table class="table" style="background-color: #E9E9E9;">
          <thead>
          <tr>
            <th scope="col">Osnovne informacije</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>Broj soba: </td>
            <td>{{offer.numberOfRooms}}</td>
          </tr>
          <tr>
            <td>Broj kreveta: </td>
            <td>{{offer.numberOfBeds}}</td>
          </tr>
          <tr>
            <td>Cena: </td>
            <td>{{offer.price}}</td>
          </tr>
          <tr>
            <td>Ocena: </td>
            <td v-show="offer.rating > 0">
              {{ offer.rating }}
              <img class="mb-2" style="width: 20px;height: 18px;" src="/Star_icon_stylized.svg.png">
            </td>
            <td v-show="offer.rating <= 0">nema ocenu</td>
          </tr>

          <tr>
            <td>Slobodni termini: </td>
            <td>
              <calendar-modal :availability-period="availabilityPeriod"></calendar-modal>
            </td>
          </tr>
          </tbody>
        </table>

      </div>

      <div class="col m-2">
        <table class="table" style="background-color: #E9E9E9;">
          <thead>
          <tr>
            <th scope="col">Pravila ponašanja</th>
          </tr>
          </thead>
          <tbody class="table-borderless">
              <ul v-if="offer.behavioralRules.length > 0">
                <div v-for="(text,index) of offer.behavioralRules" :key="index">
                    <li>{{text}}</li>
                </div>
              </ul>
              <p v-else class="m-1">Nema dodatih pravila ponašanja</p>

          </tbody>
        </table>
      </div>

    </div>
    <div align="left" class="row m-2">

      <div class="col" style="background-color: #E9E9E9; max-width: 45%;">
        <div v-if="offer.additionalServices.length > 0">
          <table class="table" style="background-color: #E9E9E9;">
            <thead>
            <tr>
              <th scope="col">Dodatne usluge</th>
              <th scope="col">Cena</th>
            </tr>
            </thead>
            <tbody class="table-border">
            <tr v-for="(text, index) of offer.additionalServices" :key="index">
              <td scope="col">{{ text }} </td>
              <td scope="col">{{offer.additionalServicesPrice[index]}}</td>
            </tr>
            </tbody>

          </table>
        </div>
        <p v-else class="m-1">Nema dodatih dodatne usluge</p>
      </div>
    </div>
    <div align="left" class="row m-2">
      <button @click="toggleExperienceReview" class="btn btn-outline-secondary mb-4" style="max-width: 400px;">Pročitaj mišljenje klijenata</button>
      <experience-review-view :ket="reviewsKey" @remountReviews="remountReviews" :id="offerId" :offer-type="offerType" :key="commentsKey" v-show="showExperienceReview"></experience-review-view>
    </div>


</template>

<script>
import swal from "sweetalert2";

import ExperienceReviewView from "@/components/main_site/offer_profile/experienceReviewView";
import QuickReservationModal from "@/components/client/quickReservationModal";
import CalendarModal from "@/components/main_site/offer_profile/calendarModal";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import ResMod from "@/components/client/resMod";
//import ReservationModal from "@/components/client/reservationModal";
export default {
  name: "cottageInfoDisplay",
  components: {ResMod, CalendarModal, QuickReservationModal, ExperienceReviewView},
  props: ["offer"],
  created:
      function () {
        this.offerId = this.offer.id;

        try{

          if (JSON.parse(localStorage.user) == null) {
            this.verifiedClient = false;
          } else {
            this.verifiedClient = true;
          }
        }catch (error){
          this.verifiedClient = false;

        }


      },
  mounted() {
    if (this.offer.id !== "")
      PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.offer.id).then((response) => {
        this.availabilityPeriod = response.data;
      });
  },
  methods: {
    rerender(){
      this.saleAppointmentKey++;
    },
    reserveOffer(){
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli da rezervišete entitete!')
    },
    fireAlertOn(eventText){
      swal.fire({
        title: "Upozerenje",
        text: eventText,
        background:'white',
        color:'black',
        confirmButtonColor:'#FECDA6'});
    },
    subscribeToTheOffer(){
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli pretplatiti na obavaštenje za brze rezervacije!');
    },
    viewQuickReservation(){
      if (localStorage.user == null) {
        this.fireAlertOn('Morate da se prijavite da biste se mogli da izvršete brze rezervacije!');
      }
    },
    remountReviews(){
      this.reviewsKey++;
    },
    toggleExperienceReview(){
      this.offerId = this.offer.id;
      this.showExperienceReview = !this.showExperienceReview;
      this.commentsKey++;
    }
  },
  data(){
    return {
      quickReservations: [],
      showExperienceReview: false,
      commentsKey: 0,
      offerType: "cottage",
      reviewsKey: 0,
      offerId: 0,
      verifiedClient: false,
      saleAppointmentKey: 0,
      availabilityPeriod: []
    }
  }
}
</script>

<style scoped>

</style>
