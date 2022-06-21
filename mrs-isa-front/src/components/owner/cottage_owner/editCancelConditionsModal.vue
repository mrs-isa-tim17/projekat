<template>
  <button :id="ccIdbtn" style="border:none;background:none;" @click="openForm" class="m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId><i style="font-size: 20px;" class="fa fa-edit"></i></button>
  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          <label>Procenat za rezervacije koje se otkažu <b> 5 dana</b> pre početka:</label>
          <input type="text" v-model="pr1">
          <label>Procenat za rezervacije koje se otkažu <b> 10 dana</b> pre početka:</label>
          <input type="text" v-model="pr2">
          <label>Procenat za rezervacije koje se otkažu <b> 15 dana</b> pre početka:</label>
          <input type="text" v-model="pr3">
          <label>Procenat za rezervacije koje se otkažu više od<b> 20 dana</b> pre početka:</label>
          <input type="text" v-model="pr4">
          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  @click="back" class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
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
  props:["offerId","p1","p2","p3","p4","header","index"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
        console.log(this.percents);
        this.pr1 =this.p1;
        this.pr2 = this.p2;
        this.pr3 = this.p3;
        this.pr4 = this.p4;
        console.log(this.pr1);
        this.ccIdbtn = "cc";

      },
  methods: {
    openForm() {
      console.log("forma");
      this.numberOffer = this.price;
      this.id = this.offerId;
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    editCC() {
      if(this.pr1 == ""){
        this.pr1 = 0;
      }
      else if(this.pr2 == ""){
        this.pr2 = 0;
      }
      else if(this.pr3 == ""){
        this.pr3=0;
      }
      else if(this.pr4 == ""){
        this.pr4=0;
      }
      this.new_percents.push(parseInt(this.pr1));
      this.new_percents.push(parseInt(this.pr2));
      this.new_percents.push(parseInt(this.pr3));
      this.new_percents.push(parseInt(this.pr4));

      this.$emit('edit-cancel-condition', this.new_percents);
      console.log(this.new_percents);
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';
    },
    back(){
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
      pr1:"",
      pr2:"",
      pr3:"",
      pr4:"",
      new_percents:[],
      ccIdbtn:"",
      id:""

    }
  }
}
</script>

<style scoped>

</style>