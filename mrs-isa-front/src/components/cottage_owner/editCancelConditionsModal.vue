<template>
  <button style="border:none;background:none;" @click="openForm" class="m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId><i style="font-size: 24px;" class="fa fa-edit"></i></button>
  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          <label>Procenat za rezervacije koje se otkažu <b> 5 dana</b> pre početka:</label>
          <input type="text" v-model="p1">
          <label>Procenat za rezervacije koje se otkažu <b> 10 dana</b> pre početka:</label>
          <input type="text" v-model="p2">
          <label>Procenat za rezervacije koje se otkažu <b> 15 dana</b> pre početka:</label>
          <input type="text" v-model="p3">
          <label>Procenat za rezervacije koje se otkažu više od<b> 20 dana</b> pre početka:</label>
          <input type="text" v-model="p4">
          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="editCC" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "editCancelConditionsModal",
  props:["offer","header","index"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
        console.log(this.offer.percents);
        this.p1 = this.offer.percents[0];
        this.p2 = this.offer.percents[1];
        this.p3 = this.offer.percents[2];
        this.p4= this.offer.percents[3];
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
    editCC() {
      this.new_percents.push(this.p1);
      this.new_percents.push(this.p2);
      this.new_percents.push(this.p3);
      this.new_percents.push(this.p4);

      this.$emit('edit-cancel-condition', this.new_percents);
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
      p1:"",
      p2:"",
      p3:"",
      p4:"",
      new_percents:[]

    }
  }
}
</script>

<style scoped>

</style>