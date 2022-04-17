<template>
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner" >
      <div class="carousel-item active" v-if="image_paths.length!=0">
        <img :src=image_paths[0].path class="d-block w-100"  alt="...">
      </div>

      <div class="carousel-item active" v-if="image_paths.length===0">
        <img src="../assets/img/adventure/Drina1.jpg">
      </div>

      <div class="carousel-item"  v-for="item in image_paths" :key="item" > <!--v-if="index > 0"> -->
        <img :src=item.path class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
      </button>
  </div>
</template>

<script>
export default {
  name: "images-carousel",
  props: ['image_paths']
}
</script>

<style scoped>

</style>