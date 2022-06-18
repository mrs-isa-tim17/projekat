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
          <div v-for="(s,i) in behaviorList" :key="i" >
            <input type="checkbox" width="20" height="20"  @change="checkRule(s)">{{s}}
          </div>

          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="editRules" class="btn btn-primary">Izmeni</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "editBehaviorRulesModal",
  props: ["behaviorList", "index", "header"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
        console.log(this.behaviorList);

      },
  methods: {

    openForm() {
      console.log("forma");
      this.nameOffer=this.name;
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    checkRule(s){

      if(this.checked_behavior.includes(s)){
        var i = this.checked_behavior.indexOf(s);
        this.checked_behavior.splice(i,1);
      }
      else{
        this.checked_behavior.push(s);
      }
    },

    editRules() {

      this.$emit('edit-rules', this.checked_behavior);
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
      behavior_for_show:[],
      checked_behavior:[]
    }
  }
}
</script>

<style scoped>

</style>