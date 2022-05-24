<template>

  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img :key="getFirstImage()" style="max-height: 300px; max-width: 100%; background-color: #E9E9E9;" :src="getFirstImage()" class="d-block w-100" alt="first pic missing">
      </div>

        <div class="carousel-item" v-for="(path, index) in filteredImages()" :key="index">
          <img style="max-height: 300px; max-width: 300px;" :src="path" class="d-block w-100" alt="missing img">
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
  name: "offerCarousel",
  props: ["images"],
  mounted() {
    if (this.images.length === 0){
      this.imagesWithoutFirst =  [];
      this.firstImagePath =  require("@/assets/icons/img.jpg");
    }else{
      this.firstImagePath = this.images[0];
      if (this.images.length > 1)
        this.imagesWithoutFirst = this.images.slice(1);
    }
    this.firstImgKey++;
  },
  methods: {
    filteredImages(){
      if (this.images.length === 0){
        return [];
      }else{
        console.log("Image slice:")
        console.log(this.images.slice(1, this.images.length-1));
        return this.images.slice(1);
      }
    },
    getFirstImage(){
      if (this.images.length === 0){
        return require("@/assets/icons/img.jpg");
      }else{
        //return require("@/assets/icons/img.jpg");
        return this.images[0];
      }
    }
  },
  data() {
    return {
      imgPath: require('@/assets/icons/cottage.png'),
      imgPath2: require('@/assets/icons/adventurer.png'),
      firstImagePath: "",
      imagesWithoutFirst: [],
      firstImgKey: 0
    }
  }
}
</script>

<style scoped>

</style>
