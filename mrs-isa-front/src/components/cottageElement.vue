<template>
  <div>

  <div class="d-flex justify-content-center mw-90">
    <div class="card mb-3 mw-90" style="width: 80%;">

      <div class="row g-0" style="background-color: #E9E9E9;">

        <div class="col-md-4">
                  </div>

        <div class="col-md-8">
          <div class="card-body">

            <h5 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</h5>
            <h6 class="card-title text-black d-flex justify-content-lg-start" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">adresa</h6>

            <div class="card-text">
              <div class="row">
                <div class="col-5 " @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                  <b>Opis:</b>{{cottage.description}}<br><b>Cena:</b> {{cottage.price}} din
                </div>

                <div class="col-6">
                  <button id="deleteCottage">Obriši vikendicu</button>
                  <updateCottageModal :index=updateId></updateCottageModal>
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
import updateCottageModal from "@/components/updateCottageModal";
import $ from "jquery";
export default {
  name: "cottageElement",
  props: ["cottage"],
  components:{updateCottageModal},
  data(){
    return{
      updateId:"updateCottage"
    }
  },
  methods:{
    openModel() {

      $('#' + this.index).modal('show');
    }
  }
}
</script>

<style scoped>

</style>