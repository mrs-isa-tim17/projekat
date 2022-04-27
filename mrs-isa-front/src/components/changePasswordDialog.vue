<template>
<div>
  <button id="edit" type="button" @click="openModel"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="modalId">
    Promeni lozinku
  </button>

  <!-- Modal -->
  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Promena lozinke</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <label>Trenutna lozinka</label><br>
          <input type="password"><br>
          <label>Nova lozinka</label><br>
          <input type="password"><br>
          <label>Ponovi novu lozinku</label><br>
          <input type="password"><br>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
            <button type="button" class="btn btn-primary" @click.prevent="emitValue">Izmeni</button>
          </div>
        </div>
      </div>
    </div>

</div>

</template>

<script>
import $ from "jquery";

export default {
  name:"changePasswordDialog",
  data(){
    return{
      modalId: ""
    }
  },
  props: ['show', 'title','cancel', 'confirm','index'],
  created:
    function () {
      this.modalId = "#"+this.index;
    }
,
  methods:{
    openModel(){
      $('#'+this.index).modal('show');
    },
  }

}
</script>
<style>
#edit{
  height: 45px;
  width: 100%;
}
</style>