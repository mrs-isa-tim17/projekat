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
          <p> {{report.reportText}}</p>
          <label>Predlog za 1 penal:     </label><input class="form-check-input" type="checkbox" :value=penalty
                                                        :checked=report.penalty id="flexCheckDefault" :disabled="true">
          <h6 id="message"></h6>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" @click="Accept">Odobri</button>
          <button type="button" class="btn btn-primary" @click="Reject">Odbij</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "reservationReportModal",
  props:["index", "header", "reportId", "btnId","btnText", "report"],
  created:
      function () {
        this.modalId = "#"+this.index;
        console.log(this.index);
        console.log(this.modalId);
        console.log(this.reportId);
      },
  methods: {
    openForm() {
      console.log("forma");
      //    $('#'+this.index).modal('show');
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },

    Accept(){
      console.log(this.report);
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');
      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';

      this.$emit('accept-report', this.report);
    },
    Reject(){
      console.log(this.report);
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');
      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';

      this.$emit('reject-report', this.report);
    }
  },

  data(){
    return {
      modalId:"",


    }
  }
}
</script>

<style scoped>

</style>