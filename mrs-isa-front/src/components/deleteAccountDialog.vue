<template>
  <a id="linkModal" href="#" data-toggle="modal" data-target="#myModal">
    <img src="@/assets/icons/deleteAcc.png">
    <p id="link">Brisanje naloga</p>
  </a>

  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
          <h4 class="modal-title">Zahtev za brisanje naloga</h4>
        </div>
        <div class="modal-body">
          <p>Razlog za brisanje naloga</p>
          <textarea class="my-4" rows="5" cols="50" name="text" v-model="text"></textarea>
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

  methods:{
    openForm(){
      console.log("forma");
      $('#myModal').modal('show');
    }
  }
}

</script>

<style scoped>
#link{
  position: absolute;
  left:0px;
  right:0px;
  bottom:0px;
  padding:10px;
  background-color: rgba(0,0,0,40%);
  margin-bottom:0px;
  color: white;
  font-weight:bold;
  border-top: 1px solid white;
  height: 50px;
}
</style>