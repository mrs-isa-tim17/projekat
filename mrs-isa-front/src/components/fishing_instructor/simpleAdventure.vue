<template>
  <div class="d-flex justify-content-center mw-90" >
  <div class="card mb-3 d-flex justify-content-center" style="max-width: 800px; min-width: 800px">
    <div class="row g-0">
      <div class="col-md-4">
        <img :src="path" class="img-fluid rounded-start" alt="Nema slike"/>
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <div class="row">
            <div class="col">
              <a :href=detailAdventure><h5 align="left" class="card-title"> {{ adventure.name }} </h5></a>
              <button class="btn btn-primary  btn-sm  me-md-2" @click="goToDetailAdventure"> Detalji</button>
              <button class="btn btn-primary  btn-sm  me-md-2" @click="goToUpdateAdventure"> Izmeni</button>
              <button class="btn btn-secondary  btn-sm  me-md-2" @click="deleteAdventure"> Obriši</button>
            </div>

            <div class="col">
              <p class="card-text"> {{ adventure.description }}</p>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
  </div>

</template>

<script>
import AdventureService from "@/services/AdventureService";
import swal from "sweetalert2";

export default {
  name: "simple-adventure",
  components: {},
  props: ['adventure', 'path'],
  methods: {
    goToDetailAdventure() {
      console.log(this.adventure.id);
      this.$router.push('/adventures/detail/' + this.adventure.id);
    },

    goToUpdateAdventure() {
      console.log(this.adventure.id);
      this.$router.push('/adventure/update/' + this.adventure.id + '/1');
    },

    deleteAdventure() {
      console.log(this.adventure.id + "  za delete");
      AdventureService.deleteAdventure(this.adventure.id).then((response) => {
        console.log(response);
        this.answerDelete = response.data;
        if(this.answerDelete === false){
          swal.fire({
            title: "Upozorenje",
            text: "Nije moguće brisanje jer avantura ima zakazane rezervacije",
            background: 'white',
            color: 'black',
            confirmButtonColor: '#FECDA6',
            timer:2000
          });
        }
        else{
          swal.fire({
            title: "Obaveštenje",
            text: "Avantura je obrisana",
            background: 'white',
            color: 'black',
            confirmButtonColor: '#8DF172',
            timer: 2000
          });
          this.$emit('rerender');
        }


      })
          .catch(function (error) {
            console.log(error.toJSON());
            if (error.response) {
              // The request was made and the server responded with a status code
              // that falls out of the range of 2xx
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            } else if (error.request) {
              // The request was made but no response was received
              // error.request is an instance of XMLHttpRequest in the browser and an instance of
              // http.ClientRequest in node.js
              console.log(error.request);
            } else {
              // Something happened in setting up the request that triggered an Error
              console.log('Error', error.message);
            }
            console.log(error.config);

          });
    }
  },

  data() {
    return {
      adventures: [],
      defaultImg: '/img/instructor/adventurer.png',
      answerDelete:false,
    }
  }
}
</script>

<style scoped>

</style>
