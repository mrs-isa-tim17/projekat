<template>
  <button id="update" type="button" @click="openForm" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    <i class="fa fa-delete icon"></i> Izbriši brod
  </button>
  <div class="alert alert-success alert-dismissible fade show" id="successChange" role="alert" style="visibility: hidden;">
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    Brod je obrisan!
  </div>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="deleteCottageModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Da li ste sigurni da želite da obrišete Vaš brod <b> {{ship.name}}</b>?
          <div class="modal-footer">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="deleteShip" class="btn btn-primary">Potvrdi</button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import ShipService from "@/servieces/ship_owner/ShipService";

export default {
  name: "deleteCottageModal",
  props: ["ship", "index", "header"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#"+this.index;
      },
  methods:{
    openForm(){
      console.log("forma");
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#'+this.index).show(modalToggle);
      $('#'+this.index).focus(modalToggle);
    },
    deleteShip(){
      ShipService.deleteShip(this.ship.id).then((response) =>
          {
            if(response.data){
              console.log(response.data);
              document.getElementById("successChange").style.visibility = 'visible';
            }

          }
      )
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';
    }
  },
  data(){
    return{
      modalId:""
    }
  }
}
</script>

<style scoped>

</style>