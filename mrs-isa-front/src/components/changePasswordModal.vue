
<template>

   <button id="update" type="button" @click="openFormChangePassword" class="btn btn-secondary my-2" data-bs-toggle="modal" :data-bs-target=modalId>
    Promena lozinke
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="passwordChangeModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="passwordChangeModal">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <div class="mb-3">
            <label class="form-label">Trenutna lozinka</label>
            <input type="password" class="form-control" id="password" name="password" v-model="old_password"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Nova lozinka</label>
            <input type="password" class="form-control" id="password_new" name="password_new" v-model="new_password"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Nova lozinka ponovo</label>
            <input type="password" class="form-control" id="password_new_again" name="password_new_again"/>
          </div>
          <div class="modal-footer d-block">
            <button type="submit" class="btn btn-warning float-end" @click="changePassword">Potvrdi izmene</button>
          </div>

          </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "changeAccountDialog",
  props:["index","header","owner"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#"+this.index;
      },
  methods:{
    openFormChangePassword(){
      console.log("forma");
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#'+this.index).show(modalToggle);
      $('#'+this.index).focus(modalToggle);
    },
    changePassword(){
      this.$emit('input-new-password', this.old_password, this.new_password);
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
      old_password:"",
      new_password:""
    }
  }
}

</script>

<style scoped>
#update {
  height: 45px;
  width: 100%;
  outline: none;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  background-color: #687864;
}
</style>
