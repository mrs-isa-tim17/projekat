<template>
  <div>
    <div align="left">
      <div class="row">
        <div class="col-9">
          <p>{{offer.description}}</p>
        </div>
        <div class="col">
          <div class="row p-1"><button @click="reserveOffer" class="btn btn-secondary"> Rezerviši </button></div>
          <div class="row p-1"><button @click="subscribeToTheOffer" class="btn btn-secondary"> Prati </button></div>
          <div class="row p-1"><button @click="viewQuickReservation" class="btn btn-secondary"> Brze rezervacije </button></div>
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
      <button @click="toggleExperienceReview" class="btn btn-outline-secondary mb-4" style="max-width: 400px;">Pročitaj mišljenje klijenata</button>
      <experience-review-view :ket="reviewsKey" @remountReviews="remountReviews" :id="offerId" :offer-type="offerType" :key="commentsKey" v-show="showExperienceReview"></experience-review-view>
    </div>

  </div>




<!--
  <div class="modal fade" id="calendarModal" tabindex="-1" aria-labelledby="calendarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="calendarModalLabel">Slobodni termini</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" style="height: 500px;">
          <CalendarMrs style="width: 100%; height: 500px;" :key="calendarKey" :availability-period="this.availabilityPeriod" :unavailability-period="this.unavailabilityPeriod" :my-events="this.reservations"></CalendarMrs>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-outline-primary">Zatvori</button>
        </div>
      </div>
    </div>
  </div>
  -->
</template>

<script>
import ExperienceReviewView from "@/components/main_site/offer_profile/experienceReviewView";
import swal from "sweetalert2";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import CalendarModal from "@/components/main_site/offer_profile/calendarModal";
export default {
  name: "shipInfoDisplay",
  components: {CalendarModal, ExperienceReviewView},
  props: ["offer"],
  created() {
  },
  mounted() {
    if (this.offer.id !== "")
      PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.offer.id).then((response) => {
        this.availabilityPeriod = response.data;
        this.calendarKey++;
      });
  },
  methods: {
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
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli da izvršete brze rezervacije!')
    },
    remountReviews(){
      this.reviewsKey++;
    },
    toggleExperienceReview(){
      console.log("EXPERIENCE:");
      console.log(this.offer.id);
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
      calendarKey: 1,

      availabilityPeriod:[""],
      unavailabilityPeriod:[""],
      reservations:[""],
    }
  }

}
</script>

<style scoped>

</style>
