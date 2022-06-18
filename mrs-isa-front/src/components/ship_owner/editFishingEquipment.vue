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
          <div v-for="(s,i) in eq_for_show" :key="i" >
            <input type="checkbox" width="20" height="20"  @change="checkEq(s)">{{s}}
          </div>

          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="editEq" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "editFishingEquipment",
  props: ["eqList", "index", "header"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
        console.log(this.behaviorList);
        /*for(let i=0;i<this.allBehavior.length;i++){

          if((this.behaviorList.includes(this.allBehavior[i]))){
            this.checked_behavior.push(this.allBehavior[i]);
          }
          else{
            this.behavior_for_show.push(this.allBehavior[i]);
          }
        }*/
      },
  methods: {

    openForm() {
      console.log("forma");
      this.nameOffer=this.name;
      var modalToggle = document.getElementById(this.index);
      this.eq_for_show = this.eqList;
      console.log(this.eqList);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    checkEq(s){

      if(this.checked_eq.includes(s)){
        var i = this.checked_eq.indexOf(s);
        this.checked_eq.splice(i,1);

      }
      else{
        this.checked_eq.push(s);
      }
    },

    editEq() {

      this.$emit('edit-eq', this.checked_eq);
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
  data(){
    return{
      modalId:"",
      nameOffer:"",
      allBehavior:[ "dozvoljeno pušenje",
        "zabranjeno pušenje"],
      eq_for_show:[],
      checked_eq:[]
    }
  }
}
</script>

<style scoped>

</style>