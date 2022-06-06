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
                  Od: {{formatDate(offer.startDate)}}
                  <br>
                  Do: {{formatDate(offer.endDate)}}
                  <br>
                  Cena: {{offer.price}} din
                  <br>
                  <p v-if="offer.canceled" class="text-danger">
                    otkazano
                  </p>
                  <p v-if="offer.quickReservation" class="text-success">
                    na popustu
                  </p>
                </div>

                <div class="col">
                  <button @click="cancelReservation" type="button" class="btn btn-secondary" >Otkaži</button>
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
import Swal from 'sweetalert2';

export default {
  name: "clientUpcomingReservationElement",
  props: ["offer"],
  methods: {
    formatDate(date){
      return date[2] + ". " + date[1] + ". " + date[0] + " " + date[3] + ":" + date[4];
    },
    cancelReservation(){
      Swal.fire({
        title: 'Jeste li sigurni da hoćete da otkažete rezervaciju?',
        //showDenyButton: true,
        showCancelButton: true,
        cancelButtonText: "Odustani",
        cancelButtonColor: "blue",
        confirmButtonText: 'Otkaži rezervaciju',
        confirmButtonColor: "gray",
        //denyButtonText: `Don't save`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {

          //axios for canceling reservation!!
          //Swal.fire('Saved!', '', 'success')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
