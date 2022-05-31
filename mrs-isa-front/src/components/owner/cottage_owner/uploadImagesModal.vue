<template>
<button style="border:none;background:none;" @click="openForm" class="m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId>Slike</button>

<div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
    <div class="modal-header">
      <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body" >
      <UploadImages @change="handleImages"></UploadImages>

      <div class="modal-footer" style="background-color:#31708E">
        <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
        <button type="button" @click="submitImages" class="btn btn-primary">Dodaj</button>
      </div>
    </div>
  </div>
</div>
</div>
</template>
<script>

import UploadImages from "vue-upload-drop-images";
import $ from "jquery";
//import axios from "axios";

export default {
  name: "uploadImagesModal",
  props:["index","header"],
  components:{UploadImages
    },
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
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
    handleImages(files){
      console.log(files);
    }
    /*uploadFile() {
      this.images = this.$refs.file.files[0];

    },
    submitImages() {
      const formData = new FormData();
      formData.append('file', this.images);
      const headers = {'Content-Type': 'multipart/form-data'};
      axios.post('https://httpbin.org/post', formData, {headers}).then((res) => {
        res.data.files; // binary representation of the file
        console.log(res.data.files);
        res.status; // HTTP status
        console.log(res.status);
      });
    }*/
  },
  data(){
    return{
      images:null
    }
  }
}
</script>

<style scoped>

</style>