<template>
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img :src="getFirstImg()" class="d-block w-100" alt="...">
      </div>

      <div class="carousel-item" v-for="path in filterInactiveImages()" :key="path">
        <img :src="path" class="d-block w-100" alt="...">
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
  props: ['image_paths'],

  mounted() {
  },
  created:
      function () {
      },

  methods: {
    getFirstImg(){
      console.log("LEEEN");
      console.log(this.image_paths.length);
      if (this.image_paths.length === 0) {
        this.activeImage = this.image
        //this.images.push(this.activeImage);
      } else {
        this.activeImage = this.image_paths[0];
      }
      return this.activeImage;
    },
    filterInactiveImages() {
      console.log("OVDE ");
      console.log(this.image_paths);
      this.images=[];
      if (this.image_paths.length === 0) {
        this.images=[];//\.push(this.image);
      } else {
        for (let i in this.image_paths) {
          if (i !== 0) {
            this.images.push(this.image_paths[i]);
          }
        }
      }
      return this.images;
    },
  },
  data() {
    return {
      image: require("@/assets/icons/img.jpg"),
      images: [],
      activeImage: "",
    }
  }
}
</script>

<style scoped>

</style>