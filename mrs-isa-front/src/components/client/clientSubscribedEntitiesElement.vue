<template>
  <div class="d-flex justify-content-center mw-90">
    <div class="card mb-3 mw-90" style="width: 80%;">

      <div class="row g-0" style="background-color: #E9E9E9;">

        <div class="col-md-4">
          <img :src="offer.img" class="img-fluid rounded-start" @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>

        <div class="col-md-8">
          <div class="card-body">

            <h5 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{offer.offerName}}</h5>

            <div class="card-text">
              <div class="row">
                <div class="col-5 " @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                  {{offer.description}}
                </div>


                <div class="col">
                  <button @click="unsubscribe" type="button" class="btn btn-secondary" >Odprati</button>
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
import offerService from "@/servieces/OfferService";
import Swal from "sweetalert2";

export default {
  name: "clientSubscribedEntitiesElement",
  props: ["offer"],
  methods:{
    goToOffer(){
      this.$router.push('/book/' + this.offer.offerType.toLowerCase() + '/site/' + +this.offer.offerId);
    },
    unsubscribe(){
      console.log("UNSUBSCRIBE");
      console.log(JSON.parse(localStorage.user).id);
      console.log(this.offer.offerId);
      offerService.unsubscribeForOffer(JSON.parse(localStorage.user).id, this.offer.offerId)
          .then((response) =>{
            console.log(response.data);
            this.$emit('rerender');
            Swal.fire({
              icon: 'success',
              title: 'Uspešno ste otkazali rezervaciju.',
              showConfirmButton: false,
              timer: 1500
          })});
    }
  }
}
</script>

<style scoped>

</style>
