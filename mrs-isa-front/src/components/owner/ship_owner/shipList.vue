<template>
  <div class="container" >

  <div class="row d-flex justify-content-center">
    <div class="col-8 d-flex justify-content-center">
      <div class="input-group mb-3 input-icons" >
        <i class="fa fa-search icon"></i><input placeholder="Pretraži po imenu..." type="search" class="input-field form-control" aria-label="Text input with dropdown button" v-model="search"  @keyup="filtered">
      </div>
      <button class="btn-sm "  @click="addShipPage"><i class="fa fa-ship"></i> Dodaj novi brod</button>
    </div>
  </div>
    <p style="font-size: 30px;">{{message}}</p>
    <div class="p-2" v-for="(s) in allShips"  :key="s.id">
      <shipElement :ship="s" @rerender="forceRerendering"></shipElement>
    </div>
  </div>
</template>

<script>
import shipElement from "@/components/owner/ship_owner/shipElement";
import ShipService from "@/servieces/ship_owner/ShipService";
export default {
  name: "shipList",
  components:{shipElement},
  data(){

    return {
      allShips: null,
      myKey: 1,
      search:"",
      message:""
    }
  },

  methods:{
    addShipPage(){
      this.$router.push('/ship/add');
    },
  filtered(){
    this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
    console.log(this.coID);
    ShipService.getShipByOwner(this.coID)
        .then((response) => {
          if(!this.search) {
            this.allShips = response.data;
            if(this.allShips.length == 0){
              this.message = "Nemate još uvek dodate brodove!";
            }
            console.log(this.allShips);
          }
          else{
            console.log(response.data);
            this.allShips = response.data.filter(ship =>
                ship.name.toLowerCase().includes(this.search.toLowerCase())
            );
            console.log(this.allShips);
          }
        })

  },
    forceRerendering(){
      window.location.reload();
    },
  },
  created(){
    this.filtered();
  }
}
</script>

<style scoped>

.input-icons{
  width: 100%;
  margin-bottom: 10px;
  float:left;
}
.icon {
  padding: 10px;
  min-width: 40px;
}
input{
  margin-right:20px;
}
button{
  width:250px;
  height:38px;
}
</style>
