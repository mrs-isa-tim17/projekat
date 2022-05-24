<template>
  <button id="update" type="button" @click="openForm" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    <i class="fa fa-delete icon"></i> Izbriši vikendicu
  </button>


  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="deleteCottageModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="background-color:#687864">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Da li ste sigurni da želite da obrišete Vašu vikendicu <b> {{cottage.name}}</b>?
          <div class="modal-footer">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="deleteCottage" class="btn btn-primary">Potvrdi</button>
          </div>
          <div class="alert alert-success alert-dismissible fade show" id="successChange" role="alert" style="visibility: hidden;">
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            Vikendica je obrisana!
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import CottageService from "@/servieces/cottage_owner/CottageService";

export default {
  name: "deleteCottageModal",
  props: ["cottage", "index", "header"],
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
    deleteCottage(){
      CottageService.deleteCottage(this.cottage.id).then((response) =>
      {
        if(response.data){
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