<template>

  <div class="col-3 px-5" >

    <button id="delete" type="button" @click="openForm"  class="btn" data-bs-toggle="modal" :data-bs-target=modalId>
      <div class="card-lg" style="min-width: 200px;">
        <div class="card-body">
          <img :src=image_path height="150">
        </div>
        <div class="card-footer text-center" style="color: #0645AD ;">
          {{text}}
        </div>
      </div>
    </button>
    <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">

            <textarea class="my-4" style="width: 100%; min-height: 150px;" name="text" v-model="reason"></textarea>
          </div>
          <div class="modal-footer">
            <p id="emptyError" style="visibility: hidden; color: red;">Ne može da se pošalje prazan zahtev</p>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
            <button type="button" class="btn btn-primary" @click="sendRequest">Pošalji</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import $ from "jquery";
import deleteRequestServce from "@/servieces/DeleteRequestServce";

export default {
  name: "RequestForDeletingAccountModal",
  props:["index","header", "text", "image_path"],
  created:
      function () {
        this.modalId = "#"+this.index;
      },
  methods:{
    openForm(){
      console.log("forma");
      //$('#'+this.index).modal('show');
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#'+this.index).show(modalToggle);
      $('#'+this.index).focus(modalToggle);
    },
    sendRequest(){
      this.clientID = JSON.parse(localStorage.user).id;//this.$route.params.id;
      if (this.reason === ""){
        let p = document.getElementById('emptyError');
        p.style.visibility= "visible";
        return;
      }
      let resObj = {
        reason: this.reason
      }
      deleteRequestServce.makeDeleteRequest(this.clientID, resObj)
          .then(response => {
            console.log(response.data);
          })

      const modal = document.getElementById(this.index);

      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';
    }
  },
  data(){
    return{
      modalId:"",
      reason: ""
    }
  }
}
</script>

<style scoped>

</style>
