<template>
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
  <div align="left" class="row">
    <div class="col">
      <b>Informacije o instruktoru:</b>
      <br>
      <p class="mx-3">{{offer.instructorBiography}}</p>
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
        </tbody>
      </table>

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
      <table class="table" style="background-color: #E9E9E9;">
      <thead>
      <tr>
        <th scope="col">Dodatne usluge</th>
      </tr>
      </thead>
      <tbody class="table-borderless">

          <ul v-if="offer.additionalServices.length > 0">
            <div v-for="(text,index) of offer.additionalServices" :key="index">
              <li>{{text}}</li>
            </div>
          </ul>
          <p v-else class="m-1">Nema dodatih dodatne usluge</p>

      </tbody>
    </table>
    </div>
    <div class="col">
      <table class="table" style="background-color: #E9E9E9;">
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
</template>

<script>
import ExperienceReviewView from "@/components/main_site/offer_profile/experienceReviewView";
import swal from "sweetalert2";
export default {
  name: "adventureInfoDisplay",
  components: {ExperienceReviewView},
  props: ["offer"],
  mounted() {
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
      //this.$router.push("/book/site/login");
    },
    viewQuickReservation(){
      if (localStorage.user == null)
        this.fireAlertOn('Morate da se prijavite da biste se mogli da izvršete brze rezervacije!')
    },
    remountReviews(){
      console.log("Remount - cottage display");
      this.reviewsKey++;
    },
    toggleExperienceReview(){
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
      offerId: 0
    }
  }

}
</script>

<style scoped>

</style>
