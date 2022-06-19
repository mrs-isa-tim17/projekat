<template>
  <button id="delete" type="button" @click="openForm"  class="btn btn-secondary my-2" data-bs-toggle="modal" style="max-width: 200pt;" :data-bs-target=modalId>
    Brisanje naloga
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          {{textModal}}
          <textarea class="my-4" style="width: 100%; min-height: 150px;" name="text" v-model="text"></textarea>
        </div>
        <div class="modal-footer">
          <p id="emptyError" style="visibility: hidden; color: red;">Ne može da se pošalje prazan zahtev</p>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" @click="sendRequest">Pošalji</button>
        </div>
      </div>
    </div>
    </div>
</template>

<script>
import $ from "jquery";
import deleteRequestServce from "@/servieces/DeleteRequestServce";
import swal from "sweetalert2";

export default {
  name: "deleteAccountDialog",
  props:["index","header","textModal"],
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
      if (this.text === ""){
        swal.fire({title:'Unesite razlog brisanja!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else{

        let resObj = {
          reason: this.text
        }
        deleteRequestServce.makeDeleteRequest(this.clientID, resObj)
            .then(response => {
              let success = response.data;
              if (success) {
                swal.fire({
                  title: "Uspešno",
                  text: "Uspešno smo poslali zahtev za brisanje naloga.",
                  background: 'white',
                  color: '#687864',
                  confirmButtonColor: '#687864'
                });
              }else{
                swal.fire({
                  title: "Neuspešno",
                  text: "Već ste poslali zahtev za brisanje naloga.",
                  background: 'white',
                  color: '#687864',
                  confirmButtonColor: '#687864'
                });
            }
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
      text: ""
    }
  }
}}

</script>

<style scoped>
#delete{

  height: 45px;
  width: 100%;
  outline: none;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  background-color: #687864;
}
</style>
