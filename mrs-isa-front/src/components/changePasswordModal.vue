
<template>
  <button id="update" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    Promena lozinke
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

        <form>
          <div class="mb-3">
            <label class="form-label">Trenutna lozinka</label>
            <input type="password" class="form-control" id="password" name="password"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Nova lozinka</label>
            <input type="password" class="form-control" id="password_new" name="password_new"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Nova lozinka ponovo</label>
            <input type="password" class="form-control" id="password_new_again" name="password_new_again"/>
          </div>
          <div class="modal-footer d-block">
            <button type="submit" class="btn btn-warning float-end">Potvrdi izmene</button>
          </div>
        </form>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "deleteAccountDialog",
  props:["index","header"],
  created:
      function () {
        this.modalId = "#"+this.index;
      },
  methods:{
    openForm(){
      console.log("forma");
      $('#'+this.index).modal('show');
    }
  },
  data(){
    return{
      modalId:""
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