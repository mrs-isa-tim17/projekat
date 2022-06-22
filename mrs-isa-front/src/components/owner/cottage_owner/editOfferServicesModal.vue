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
          <div v-for="(s,i) in this.services_for_show" :key="i" >
            <input type="checkbox" name="service" width="20" height="20"  @change="checkService(s)">{{s}}
          </div>

          <div class="modal-footer" style="background-color:#31708E">
            <button type="button" @click="back" class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="editServices" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {

  name: "editOfferServicesModal",
  props: ["offerId","servicesList", "index", "header"],
  created:
      function () {

        this.modalId = "#" + this.index;
        this.services_for_show = this.servicesList;
        console.log(this.checked_services);
        for(let i=0;i<this.checked_services.length;i++){
          console.log(this.checked_services[i]);
          this.checkService(this.checked_services[i]);
        }
        /*console.log(this.servicesList);
        for(let i=0;i<this.allServices.length;i++){
          if((this.servicesList.includes(this.allServices[i]))){
            this.checked_services.push(this.allServices[i]);
            console.log(this.checked_services);
          }
          else{
            this.services_for_show.push(this.allServices[i]);
            console.log(this.services_for_show);
          }
        }
        console.log(this.checked_services);*/
      },
  methods: {

    openForm() {

      var modalToggle = document.getElementById(this.index);
      this.services_for_show = this.servicesList;
      this.id = this.offerId;
      console.log(this.services_for_show);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    checkService(s){

      if(this.checked_services.includes(s)){
        var i = this.checked_services.indexOf(s);
        console.log(i);
        console.log(this.checked_services[i]);
        this.checked_services.splice(i,1);
        //this.services_for_show.push(s);
        console.log("odcekirano");
        console.log(s);
        console.log(this.checked_services);
      }
      else{
        this.checked_services.push(s);
      }
    },

    editServices() {
      console.log(this.checked_services);
      this.$emit('edit-services', this.checked_services);

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
    },
  },
  data(){
    return{
      modalId:"",
      nameOffer:"",
      allServices:["restoran",
        "doručak",
        "bar",
        "wifi",
        "bazen",
        "spa",
        "teretana",
        ],
      services_for_show:[],
      checked_services:[],
      id:""
    }
  }
}
</script>

<style scoped>

</style>