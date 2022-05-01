<template>
  <div>
    <button type="button" @click="openModel" :style="buttonColor" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="modalId">
      Izmeni podatke
    </button>

    <!-- Modal -->
    <div class="modal fade" style="overflow-y: auto">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Izmena podataka</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <label>Naziv</label><br>
            <input type="text" value=""><br>
            <label>Adresa</label><br>
            <input type="text"><br>
            <label>Opis</label><br>
            <input type="text" value=""><br>
          </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
            <button type="button" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>




</template>

<script>

export default {
  name: "modalWithTextArea",
  props: ['buttonText', 'buttonColor', "header", "index","cottage"],
  methods : {
    openModel(){

      const modal = document.getElementById(this.index);

      // change state like in hidden modal
      modal.classList.add('show');
      modal.setAttribute('aria-hidden', 'false');
      modal.setAttribute('style', 'display: block');

      // get modal backdrop
      const modalBackdrops = document.getElementsByClassName('modal fade');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);

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