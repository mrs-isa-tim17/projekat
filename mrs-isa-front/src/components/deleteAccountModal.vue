<template>
  <button id="delete" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    Brisanje naloga
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <textarea class="my-4" rows="4" cols="20" name="text" v-model="text"></textarea>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" >Pošalji</button>
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
#delete{
float:right;
  margin-right:155px;
}
</style>