<template>
  <div>
    <div align="left">
      <div class="row">
        <div class="col-9">
          <p>{{offer.description}}</p>
        </div>
        <div class="col">
          <div class="row p-1">
              <res-mod :offerId="offer.id" :additionalServicesPrice="offer.additionalServicesPrice" :price="offer.price" :offer-type="'ship'" :verified-client="verifiedClient" :additionalServices="offer.additionalServices"></res-mod>
              <button v-show="!verifiedClient" style="min-width: 150px; " @click="reserveOffer" class="btn btn-secondary"> Rezerviši </button>
          </div>
          <div align="left" class="row p-1">
            <quick-reservation-modal :key="saleAppointmentKey" @sale-modal-rerender="rerender" :verifiedClient="verifiedClient" :offerId="offerId"></quick-reservation-modal>
            <button v-show="!verifiedClient" style="min-width: 150px;" @click="viewQuickReservation" class="btn btn-secondary" type="button">Brze rezervacije</button>
          </div>
          <div class="row p-1">
            <button v-if="!subscribed" :key="subKey" @click="subscribeToTheOffer" class="btn btn-secondary"> Prati </button>
            <button v-if="subscribed" :key="unsubKey" @click="unsubscribeFromTheOffer" class="btn btn-light text-secondary"> Odprati </button>
          </div>
        </div>
      </div>
    </div>
    <div align="left" class="row m-1 mt-3">
      <div class="col" >
        <table class="table" style="background-color: #E9E9E9;">
          <thead>
          <tr>
            <th scope="col">Osnovne informacije</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>Kapacitet: </td>
            <td>{{offer.capacity}}</td>
          </tr>
          <tr>
            <td>Cena: </td>
            <td>{{parseFloat(offer.price).toFixed(2)}}</td>
          </tr>
          <tr>
            <td>Ocena: </td>
            <td v-show="offer.rating > 0">
              {{ parseFloat(offer.rating).toFixed(2) }}
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

      <div class="col">
        <table class="table" style="background-color: #E9E9E9;">
          <thead>
          <tr>
            <th scope="col">Informacije o brodu:</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>Tip: </td>
            <td>{{offer.type}}</td>
          </tr>
          <tr>
            <td>Dužina: </td>
            <td>{{offer.length}}</td>
          </tr>
          <tr>
            <td>Broj motora: </td>
            <td>{{offer.engineDesignation}}</td>
          </tr>
          <tr>
            <td>Snaga motora: </td>
            <td>{{offer.enginePower}}</td>
          </tr>
          <tr>
            <td>Maksimalna brzina: </td>
            <td>{{offer.maxSpeed}}</td>
          </tr>
          <tr>
            <td>Navigaciona oprema: </td>
            <td>{{offer.navigationEquipment}}</td>
          </tr>
          </tbody>
        </table>

      </div>

    </div>
    <div align="left" class="row  m-1">

      <div class="col">
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


      <div class="col">
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
    <div align="left" class="row  m-1">

      <div class="col">
        <table class="table" style="background-color: #E9E9E9; width: 50%">
          <thead>
          <tr>
            <th scope="col">Pecaroška oprema</th>
          </tr>
          </thead>
          <tbody class="table-borderless">
          <ul v-if="offer.fishingEquipments.length > 0">
            <div v-for="(text,index) of offer.fishingEquipments" :key="index">
              <li>{{text}}</li>
            </div>
          </ul>
          <p v-else class="m-1">Nema dodatih pecaroška oprema</p>
          </tbody>
        </table>
      </div>
    </div>
    <div align="left" class="row m-2">
      <button @click="toggleExperienceReview" class="btn btn-outline-secondary mb-4" style="max-width: 400px;">Pogledaj recenzije drugih klijenata</button>
      <experience-review-view :ket="reviewsKey" @remountReviews="remountReviews" :id="offerId" :offer-type="offerType" :key="commentsKey" v-show="showExperienceReview"></experience-review-view>
    </div>

  </div>

</template>

<script>
import ExperienceReviewView from "@/components/main_site/offer_profile/experienceReviewView";
import swal from "sweetalert2";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import CalendarModal from "@/components/main_site/offer_profile/calendarModal";
import QuickReservationModal from "@/components/client/quickReservationModal";
import ResMod from "@/components/client/resMod";
import clientServce from "@/servieces/ClientServce";
import OfferService from "@/servieces/OfferService";
export default {
  name: "shipInfoDisplay",
  components: {ResMod, QuickReservationModal, CalendarModal, ExperienceReviewView},
  props: ["offer"],
  created() {
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
    if (this.offer.id !== "") {
      if (this.verifiedClient)
        this.checkSubscription();
      PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.offer.id).then((response) => {
        this.availabilityPeriod = response.data;
      });
    }
  },
  methods: {
    checkSubscription(){
      let clientId = JSON.parse(localStorage.user).id;
      clientServce.checkIfSubscribed(clientId, this.offerId)
          .then((response) => {
            this.subscribed = response.data;
            console.log("SUBBB");
            console.log(this.subscribed);
            this.subKey++;
            this.unsubKey++;
          })
    },
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
    unsubscribeFromTheOffer(){
      OfferService.unsubscribeForOffer(JSON.parse(localStorage.user).id, this.offer.id)
          .then((response)=>{
            console.log(response.data);
            this.checkSubscription();
          })
    },
    subscribeToTheOffer(){
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli pretplatiti na obavaštenje za brze rezervacije!');
      else{
        OfferService.subscribeToOffer(JSON.parse(localStorage.user).id, this.offer.id)
            .then((response)=>{
              console.log(response.data);
              this.checkSubscription();
            })
      }
    },
    viewQuickReservation(){
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli da izvršete brze rezervacije!')
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
      showExperienceReview: false,
      commentsKey: 0,
      offerType: "adventure",
      reviewsKey: 0,
      offerId: 0,
      availabilityPeriod:[],
      verifiedClient: false,
      subscribed: false,
      saleAppointmentKey: 0,
      subKey: 0,
      unsubKey: 0
    }
  }

}
</script>

<style scoped>

</style>
