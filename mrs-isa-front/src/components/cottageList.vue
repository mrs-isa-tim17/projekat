<template>
  <div class="container" >
    <ownerHeader></ownerHeader>
    <div id="addCottage">
      <button id="add" @click="addCottagePage">Dodaj novu vikendicu</button>
    </div>
    <div class="p-2" v-for="(c) in allCottages"  :key="c.id">
      <cottageElement :cottage="c"></cottageElement>
    </div>
  </div>
</template>

<script>
import cottageElement from "@/components/cottageElement";
import CottageService from "@/servieces/CottageService";
import ownerHeader from "@/components/cottageOwnerHeader";
export default {
  name: "cottageList",
  components:{cottageElement,ownerHeader},
  data(){

    return {
      allCottages: null,
      myKey: 1,
      addNewCottage:"/cottage/add"
    }
  },
  mounted(){
      this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
      console.log(this.coID);
      CottageService.getCottageByOwner(this.coID)
          .then((response)=>{
            this.allCottages = response.data;
            console.log(this.allCottages);
          })
  },
  methods:{
    addCottagePage(){
      this.$router.push('/cottage/add');
    }
  }
}
</script>

<style scoped>
#add{
  margin-left:850px;
  margin-bottom: 30px;
  margin-top:30px;
  height:50px;
}
</style>