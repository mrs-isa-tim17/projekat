<template>
  <div>

    <button type="button" @click="openModel" :style="buttonColor" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="modalId">
      {{buttonText}}
    </button>

    <!-- Modal -->
    <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <textarea class="my-4" rows="5" cols="50" name="text" v-model="text"></textarea>
            <div class="col-md-6">
              <div class="box box-orange box-example-1to10">
                <div class="box-body">
                  <p class="d-flex justify-content-start"> Ocena: </p>
                  <select @change="changeRating" class="form-select" style="width: 70px" id="ratingId" name="rating" aria-label="Default select">
                    <option selected>*</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </select>
                </div>
              </div>

            </div>

          <div class="modal-footer">

            <p id="emptyErrorModalWithTextAreaAndRating" style="visibility: hidden;"> Ne može da se pošalje prazano. </p>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
            <button type="button" class="btn btn-primary" @click.prevent="emitValue">Pošalji</button>
          </div>
        </div>
          </div>
      </div>
    </div>

  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "modalWithTextAreaAndRating",
  props: ['buttonText', 'buttonColor', "header", "index"],
  created:
      function () {
        this.modalId = "#"+this.index;
      }
  ,
  methods : {
    openModel(){

      $('#'+this.index).modal('show');
    },
    emitValue(){
      if (this.text == "" && this.rating=="*"){
        document.getElementById("emptyErrorModalWithTextAreaAndRating").style.color = "red";
        document.getElementById("emptyErrorModalWithTextAreaAndRating").style.visibility = "visible";
        return;
      }

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
      this.$emit('input-text-rating', this.text, this.rating);

    },
    changeRating(e){
      this.rating = e.target.value;
    }
  },
  data() {
    return {
      text: "",
      rating: "*",
      modalId: "",
    }
  }
}
</script>

<style scoped>

</style>
