<template>
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
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "cottageElement",
  props: ["cottage"],
}
</script>

<style scoped>

</style>