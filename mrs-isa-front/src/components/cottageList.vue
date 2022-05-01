<template>
  <div class="container" >
    <div class="p-2" v-for="(c) in allCottages"  :key="c.id">
      <cottageElement :cottage="c"></cottageElement>
    </div>
  </div>
</template>

<script>
import cottageElement from "@/components/cottageElement";
import axios from "axios";
export default {
  name: "cottageList",
  components:{cottageElement},
  data(){
    return {
      allCottages: null,
      myKey: 1
    }
  },
  mounted() {
    axios
        .get('/api/cottages/all')
        .then(response => {

          (this.allCottages = response.data);
          console.log(this.allCottages);
        })
  }
}
</script>

<style scoped>

</style>