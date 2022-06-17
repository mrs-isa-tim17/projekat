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
          <p> {{revisionText}}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" @click="Accept">Prihvati</button>
          <button type="button" class="btn btn-primary" @click="Reject">Odbij</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "revisionModal",
  props: ["index", "header", "revisionId", "btnId", "btnText", "revisionText"],
  created: function () {
    this.modalId = "#" + this.index;
    console.log(this.revisionText);
  },
  methods: {
    openForm() {
      var modalToggle = document.getElementById(this.index);
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },

    Accept() {

        const modal = document.getElementById(this.index);
        modal.classList.remove('show');
        modal.setAttribute('aria-hidden', 'true');
        modal.setAttribute('style', 'display: none');
        const modalBackdrops = document.getElementsByClassName('modal-backdrop');
        // remove opened modal backdrop
        document.body.removeChild(modalBackdrops[0]);
        document.body.style.overflow = 'auto';

        this.$emit('revision-accept-text', this.revisionId);

    },

    Reject() {
        const modal = document.getElementById(this.index);
        modal.classList.remove('show');
        modal.setAttribute('aria-hidden', 'true');
        modal.setAttribute('style', 'display: none');
        const modalBackdrops = document.getElementsByClassName('modal-backdrop');
        // remove opened modal backdrop
        document.body.removeChild(modalBackdrops[0]);
        document.body.style.overflow = 'auto';

        this.$emit('revision-reject-text', this.revisionId);

    }


  },
  data() {
    return {
      modalId: "",
      textDTO: {
        text: ""
      }

    }
  }
}
</script>

<style scoped>

</style>