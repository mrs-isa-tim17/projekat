<template>
  <div class="container" >
    <shipOwnerHeader></shipOwnerHeader>
    <div id="addShip">
      <button id="add" @click="addShipPage">Dodaj novi brod</button>
    </div>
    <div class="p-2" v-for="(s) in allShips"  :key="s.id">
      <shipElement :ship="s"></shipElement>
    </div>
  </div>
</template>

<script>
import shipElement from "@/components/shipElement";
import ShipService from "@/servieces/ShipService";
import shipOwnerHeader from "@/components/shipOwnerHeader";
export default {
  name: "shipList",
  components:{shipElement,shipOwnerHeader},
  data(){

    return {
      allShips: null,
      myKey: 1
    }
  },
  mounted(){
    this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
    console.log(this.coID);
    ShipService.getShipByOwner(this.coID)
        .then((response)=>{
          this.allShips = response.data;
          console.log(this.allShips);
        })
  },
  methods:{
    addShipPage(){
      this.$router.push('/ship/add');
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