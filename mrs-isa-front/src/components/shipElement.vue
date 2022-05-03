<template>

  <div >
    <div class="d-flex justify-content-center mw-90" >
      <div class="card mb-3 mw-90" style="width: 80%;">

        <div class="row g-0" style="background-color: #8FC1E3;color:#F7F9FB;">

          <div class="col-md-4">

          </div>


          <div class="col-md-8">
            <div class="card-body">

              <div class="card-title text-black d-flex justify-content-lg-start" style="color:#F7F9FB" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</div>

              <div class="card-title text-black d-flex justify-content-lg-start" style="color:#F7F9FB" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">adresa</div>

              <div class="card-text">
                <div class="row">
                  <div class="col-5 " @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                    <b>Opis:</b>{{cottage.description}}<br><b>Cena:</b> {{cottage.price}} din
                  </div>

                  <div class="col-7">
                    <button id="deleteShip" @click="deleteShip">Obriši brod</button><br>
                    <br>
                    <button @click="updateDataPage">Izmeni podatke</button>
                  </div>

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
import $ from "jquery";
import ShipService from "@/servieces/ShipService";
export default {
  name: "shipElement",
  props: ["ship"],
  data(){
    return{


    }
  },
  methods:{
    openModel() {

      $('#' + this.index).modal('show');
    },
    deleteShip(){
      ShipService.deleteShip(this.ship.id);
    },
    updateDataPage(){
      this.$router.push('/ship/update/' + this.ship.id);
    }
  }
}
</script>

<style scoped>
button{
  width:150px;
  background-color: #687864;
}

</style>