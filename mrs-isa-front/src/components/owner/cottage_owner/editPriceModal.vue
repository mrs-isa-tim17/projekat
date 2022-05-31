<template>
  <button style="font-size: 20px;background-color:#31708E;"  @click="openForm" class="btn btn-primary m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId>Izmenite cenovnik</button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <input type="text" v-model="numberOffer"/>
          <label style="font-size:20px;">Datum početka važenja</label><br>
          <input type="date" v-model="dateStart"/>
          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="editName" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "editPriceModal",
  props: ["price", "index", "header"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
      },
  methods: {
    openForm() {
      console.log("forma");
      this.numberOffer = this.price;
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    editName() {
      this.$emit('edit-price', this.numberOffer, this.dateStart);
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
  data() {
    return {
      modalId: "",
      numberOffer:"",
      dateStart:""
    }
  }
}
</script>

<style scoped>

</style>