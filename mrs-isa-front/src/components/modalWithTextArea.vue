<template>
  <div>

    <button type="button"  :style="buttonColor" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="modalIdHash">
      {{buttonText}}
    </button>

    <!-- Modal -->
    <div class="modal fade" style="overflow-y: auto" :id=modalId tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              Razlog za brisanje naloga:
              <textarea class="my-4" rows="5" cols="50" name="text" v-model="text"></textarea>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
            <button type="button" class="btn btn-primary" @click.prevent="sendValue">Pošalji</button>
          </div>
        </div>
      </div>
    </div>




  </div>
</template>

<script>
import $ from 'jquery';
export default {
  name: "modalWithTextArea",
  props: ['buttonText', 'buttonColor', "header", "index"],
  created:
      function () {
        this.modalId= this.index.replace(" ", "");
        this.modalIdHash = "#"+this.modalId;
      }
  ,
  methods : {
    openModel(){
      $('#'+this.index).modal('show');
      console.log(this.modalIdHash);
      /*
      const modal = document.getElementById(this.index);

      // change state like in hidden modal
      modal.classList.add('show');
      modal.setAttribute('aria-hidden', 'false');
      modal.setAttribute('style', 'display: block');

      // get modal backdrop
      const modalBackdrops = document.getElementsByClassName('modal fade');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      */
    },
    sendValue(){
      if (this.text == ""){
        document.getElementById("emptyErrorModalWithTextArea").style.color = "red";
        document.getElementById("emptyErrorModalWithTextArea").style.visibility = "visible";
        return;
      }

      //$('#'+this.modalId).modal('hide');
      this.$emit('input-text-rating', this.text, this.rating);

      const modal = document.getElementById(this.index);

      // change state like in hidden modal
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');

      // get modal backdrop
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';
      this.$emit('input-text', this.text);

    }
  },
  data() {
    return {
      text: "",
      modalId: "",
      modalIdHash: ""
    }
  }
}
</script>

<style scoped>

</style>