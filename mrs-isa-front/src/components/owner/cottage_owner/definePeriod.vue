<template>
  <button style="background-color:#31708E;color:whitesmoke;border:none;height: 30pt;font-weight: bold; " @click="openForm" class=" m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId>{{buttonName}}</button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;width:500pt;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          <div class="row">
            <div class="col-1"></div>
            <div class="col-5">
              <label><b>Početni datum</b></label>
              <Datepicker v-model="periodDate.start"></Datepicker>
            </div>
            <div class="col-5">
              <label><b>Krajnji datum</b></label>
              <Datepicker v-model="periodDate.end"></Datepicker>
            </div>
            <div class="col-1"></div>
          </div>
          <br><br>

          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="definePeriod" class="btn btn-primary">Potvrdi</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import Datepicker from "@vuepic/vue-datepicker";
import swal from "sweetalert2";

export default {
  name: "definePeriod",
  props:["header","index","buttonName","offerId"],
  components:{Datepicker},
  created() {
    console.log(this.index);
    this.modalId = "#" + this.index;
  },
  data(){
    return{
      periodDate: {
        start: "",
        end: "",
      },

    }
  },

  methods: {

    openForm() {
      console.log("forma");
      this.nameOffer = this.name;
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    definePeriod(){
      console.log(this.periodDate.start);
      console.log(this.periodDate.end);
      let today = new Date();
      if(this.periodDate.start === "" || this.periodDate.end === ""){
        swal.fire({title:'Unesite i početni i krajnji datum!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(this.periodDate.end - this.periodDate.start < 1){
        swal.fire({title:'Krajnji datum ne može biti pre početnog!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(this.periodDate.end < today || this.periodDate.start){
        swal.fire({title:'Unesite datume u budućnosti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else {
        this.$emit('define-period', this.periodDate);
        const modal = document.getElementById(this.index);
        modal.classList.remove('show');
        modal.setAttribute('aria-hidden', 'true');
        modal.setAttribute('style', 'display: none');
        const modalBackdrops = document.getElementsByClassName('modal-backdrop');

        // remove opened modal backdrop
        document.body.removeChild(modalBackdrops[0]);
        document.body.style.overflow = 'auto';
      }
      }
    }
}
</script>

<style scoped>

</style>