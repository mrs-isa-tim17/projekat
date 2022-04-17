<template>
  <div class="card mb-3 d-flex justify-content-center" style="max-width: 800px;">
    <div class="row g-0">
      <div class="col-md-4">
        <img :src=path class="img-fluid rounded-start" alt="...">
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <div class="row">
            <div class="col">
              <a :href=detailAdventure ><h5 align="left" class="card-title"> {{ adventure.name }} </h5></a>
              <p align="left" class="card-text"> {{ adventure.address.place.name }}</p>
              <p align="left" class="card-text"><small align="left" class="text-muted"> {{ adventure.address.streetName }}
                {{ adventure.address.serialNumber }} </small></p>
              <button class="btn btn-primary  btn-sm  me-md-2" @click="goToDetailAdventure"> Detalji</button>
              <button class="btn btn-secondary  btn-sm  me-md-2"> Obriši</button>
            </div>

            <div class="col">
              <p class="card-text"> {{ adventure.description }}</p>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "simple-adventure",
  components: {},
  props: ['adventure', 'path'],
  methods:{
    goToDetailAdventure(){
      console.log('/'+this.adventure.id);
      this.$router.push({name:'http://localhost:8081/instructor/adventure/detailed', params: {adventure: [this.adventure]}});
    }

  }
,
 /* data() {
    return {
      detailAdventure: "http://localhost:8081/instructor/adventures/detail"
    }
  }*/
}
</script>

<style scoped>

</style>