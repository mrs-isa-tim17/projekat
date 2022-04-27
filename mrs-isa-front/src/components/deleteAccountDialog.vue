<template>
  <a data-toggle="modal" href="#myModal">Open Modal</a>

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
          <button type="button" class="btn btn-primary" @click.prevent="emitValue">Pošalji</button>
        </div>
      </div>

    </div>
</template>

<script>
export default {
  name: "deleteAccountDialog"
}
</script>

<style scoped>

</style>