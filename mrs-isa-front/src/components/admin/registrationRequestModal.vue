<template>
  <button :id="btnId" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="modalId">
    {{btnText}}
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <h6 style="text-align:left">Obrazloženje korisnika:</h6>
          <p> {{requestMessage}}</p>
          <h6 style="text-align:left"> Vaše obrazloženje u slučaju odbijanja zahteva: </h6>
          <textarea id="text" class="my-4" rows="4" cols="40" name="text" v-model="textDTO.text"></textarea>
          <h6 id="message"></h6>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" @click="Approve">Prihvati</button>
          <button type="button" class="btn btn-primary" @click="Reject">Odbij</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import swal from "sweetalert2";

export default {
  name: "inputTextModal",
  props:["index","header", "requestId", "btnId", "btnText", "requestMessage"],
  created:
      function () {
        this.modalId = "#"+this.index;
        console.log(this.index);
        console.log(this.modalId);
        console.log(this.requestId);
      },
  methods:{
    openForm(){
      console.log("forma");
  //    $('#'+this.index).modal('show');
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#'+this.index).show(modalToggle);
      $('#'+this.index).focus(modalToggle);
    },

    fireAlertOn(eventText){
      swal.fire({
        title: "Upozerenje",
        text: eventText,
        background:'white',
        color:'black',
        confirmButtonColor:'#FECDA6'});
    },

    Reject(){
      if(this.textDTO.text===""){
        this.fireAlertOn("Unesite obrazloženje.");
      }else {
        const modal = document.getElementById(this.index);
        modal.classList.remove('show');
        modal.setAttribute('aria-hidden', 'true');
        modal.setAttribute('style', 'display: none');
        const modalBackdrops = document.getElementsByClassName('modal-backdrop');
        // remove opened modal backdrop
        document.body.removeChild(modalBackdrops[0]);
        document.body.style.overflow = 'auto';


        this.$emit('reject-text', this.requestId, this.textDTO);

      }
    },

    Approve(){
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');
      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';

      this.$emit('approve-reg-req-text', this.requestId);
    }

  },
  data(){
    return{
      modalId:"",
      textDTO: {
        text: ""
      },
      errorMessage:"Unesite obrazloženje."
    }
  }
}

</script>

<style scoped>
</style>