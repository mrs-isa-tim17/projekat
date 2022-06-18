<template >
  <div class="d-flex justify-content-center mw-90">
    <div class="card mb-3 mw-90" style="width: 80%;">

      <div class="row g-0" style="background-color: #E9E9E9;">

        <div class="col-md-4">
          <img :src="image" class="img-fluid rounded-start" @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>

        <div class="col-md-8">
          <div class="card-body">

            <h5 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</h5>

            <div class="card-text">
              <div class="row">
                <div class="col-5 " @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                  {{cottage.description}}
                </div>

                <div class="col">
                  Od: {{cottage.startDate}}
                  <br>
                  Do: {{cottage.endDate}}
                  <br>
                  Cena: {{cottage.price}} din
                  <br>
                  <p v-if="cottage.canceled" class="text-danger">
                    otkazano
                  </p>
                  <p v-if="cottage.quickReservation" class="text-success">
                    na popustu
                  </p>
                </div>

                <div class="col">

                  <modalWithTextAreaAndRating :buttonText="firstButtonText" v-show="!cottage.reviewed && !cottage.canceled"
                                                  :buttonColor="firstButtonColor" :header="firstButtonHeader" @input-text-rating="sendReview"
                                                  :index="firstButtonId">
                  </modalWithTextAreaAndRating>

                  <modalWithTextArea :buttonText="secondButtonText"  v-show="!cottage.canceled"
                                       :buttonColor="secondButtonColor" :header="secondButtonHeader" @input-text="sendComplaint"
                                       :index="secondButtonId">
                  </modalWithTextArea>

                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import modalWithTextArea from "@/components/modalWithTextArea";
import modalWithTextAreaAndRating from "@/components/modalWithTextAreaAndRating";
import reviewServce from "@/servieces/ReviewServce";
import complaintServce from "@/servieces/ComplaintServce";
export default {
  name: "clientReservationHistoryElement",
  components: {
    modalWithTextAreaAndRating,
    modalWithTextArea
  },
  props: ["cottage", "type"],
  created() {
    if (this.cottage.images[0] != null) {
      this.image = this.cottage.images[0];
    }else {
      this.image = require("@/assets/icons/ship.png");
    }
  },
  methods : {
    goToOffer(){
      this.$router.push('/book/' + this.type + '/site/' + +this.cottage.id);
    },
    sendReview(text, rating){
      if (rating == "*")
        rating = "";
      let review = {
        cottageId : this.cottage.id,
        clientID : JSON.parse(localStorage.user).id,
        rating : rating,
        text : text,
        reservationId : this.cottage.reservationId,
        offerType : this.cottage.offerType
      }
      console.log(review);
      reviewServce.offerReviewed(review).then(() => {

        this.$emit('reviewed', this.cottage.reservationId);
      })
    },
    sendComplaint(value){
      let complaint = {
        offerId : this.cottage.id,
        clientID : JSON.parse(localStorage.user).id,
        text : value,
        offerType : this.cottage.offerType,
        reservationId: this.cottage.reservationId
      }
      console.log(complaint);
      console.log(complaint);
      complaintServce.complaintOnOffer(complaint).then(() => {

      })
    }
  },
  data() {
    return {
      firstButtonText: "Komentariši",
      firstButtonColor: "background-color: #6699ff; width: 125px",
      firstButtonHeader: "Unosi tvoje mišljenje:",
      firstButtonId: "review" + this.cottage.id,

      secondButtonText: "Podnesi žalbu",
      secondButtonColor: "background-color: #ccddff; width: 125px",
      secondButtonHeader: "Unosi tvoju žalbu",
      secondButtonId: "complaint" + this.cottage.id,
      image: ""
    }
  }
}
</script>

<style scoped>

</style>
