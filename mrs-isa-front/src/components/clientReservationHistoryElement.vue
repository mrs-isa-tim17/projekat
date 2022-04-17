<template >
  <div class="d-flex justify-content-center mw-90">
    <div class="card mb-3 mw-90" style="width: 80%;">

      <div class="row g-0" style="background-color: #E9E9E9;">

        <div class="col-md-4">
          <img :src=cottage.images[0] class="img-fluid rounded-start" @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>

        <div class="col-md-8">
          <div class="card-body">

            <h5 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</h5>
            <h6 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.address.country}}, {{cottage.address.city}}, {{cottage.address.address}}</h6>

            <div class="card-text">
              <div class="row">
                <div class="col-5 " @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                  {{cottage.description}}
                </div>

                <div class="col-4">
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

                <div class="col-3">

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
export default {
  name: "clientReservationHistoryElement",
  components: {
    modalWithTextAreaAndRating,
    modalWithTextArea
  },
  props: ["cottage"],
  methods : {
    goToOffer(){
      console.log('/'+this.cottage.id);
      this.$router.push('/'+this.cottage.id);
    },
    sendReview(value, rating){
      console.log(value);
      console.log(rating);
      //this.cottage.reviewed = true; NE MOZE DA SE MENJA ZATO STO JE DOSAO PREKO PROPS?
      //axios
      //review
      //this.cottage.reviewed = true;
      console.log(this.cottage.id);
      this.$emit('reviewed', this.cottage.id);
    },
    sendComplaint(value){
      console.log(value);
      //axios
      //complaint
    }
  },
  data() {
    return {
      firstButtonText: "Komentariši",
      firstButtonColor: "background-color: #6699ff; width: 125px",
      firstButtonHeader: "Unosi tvoje mišljenje:",
      firstButtonId: this.cottage.name + "review",

      secondButtonText: "Podnesi žalbu",
      secondButtonColor: "background-color: #ccddff; width: 125px",
      secondButtonHeader: "Unosi tvoju žalbu",
      secondButtonId: this.cottage.name + "complaint",
    }
  }
}
</script>

<style scoped>

</style>