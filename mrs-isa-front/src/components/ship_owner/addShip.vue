<template>
  <shipOwnerHeader></shipOwnerHeader>
  <div class="container">
  <div class="row mt-5">
    <div class="col-3">
      <input id="name" name="name" type="text" placeholder="Naziv broda" style="width: 200px;margin-left:0px;" v-model="ship.name" ><br>
      <br>
      <label>Adresa broda</label><br>
      <open-maps :lon="ship.longitude" :lat="ship.latitude" @coordinate-changed="updateCoordinats" style="width: 200px; height: 200px; margin-left:50px;"></open-maps>
      <br>
      <textarea placeholder="Promotivni opis broda" id="description" name="description" type="text" rows="5" cols="26" v-model="ship.description"></textarea> <br>
      <br>
    </div>
    <div class="col-3">
      <input id="type" name="type" type="text" style="max-width:200px;margin-left: 10px;" v-model="ship.type" placeholder="Tip broda"><br>
      <br>
      <input id="length" name="length" type="text" style="max-width:200px;margin-left: 10px;" v-model="ship.length" placeholder="Dužina broda"><br>
      <br>
      <input id="engine_designation" name="engine_designation" type="text" style="max-width:200px;margin-left: 10px;" placeholder="Broj motora" v-model="ship.engineDesignation"><br>
      <br>
      <input id="engine_power" name="engine_power" type="text" style="max-width:200px;margin-left: 10px;" placeholder="Snaga motora" v-model="ship.enginePower"><br>
      <br>
      <input id="max_speed" name="max_speed" type="text" style="max-width:200px;margin-left: 10px;" v-model="ship.maxSpeed" placeholder="Maksimalna brzina"><br>
      <br>
      <input type="number" ref="input"  size="30" style="max-width:200px;margin-left: 10px;" v-model="ship.capacity" placeholder="Kapacitet"><br>
      <br>
      <input id="price" name="price" type="text" style="max-width:200px;margin-left: 10px;" v-model="ship.price" placeholder="Cena"><br>
      <br>
      <label for="formFile" class="form-label">Slike broda</label><br>
      <input class="form-control" type="file" :value="ship.images" id="formFile" @change="onFileSelected">
    </div>

    <div class="col-3" >
     <div class="checkBox mt-2">
       <b>Navigaciona oprema</b><br>
       <div v-for="(s,i) in this.equipments" :key="i">
         <input type="radio" name="navEq" @change="checkNavEq(s)">{{s}}
       </div>
     </div>
      <br>

        <div class="checkBox mt-2">
        <b>Pravila ponašanja</b><br>
        <div  v-for="(s,i) in this.behRules" :key="i">

            <input type="checkbox" width="20" height="20"  @change="checkBehRule(s)">{{s}}

        </div>
        </div>
      <br>
      <div class=" checkBox mt-2">
      <b>Dodatne usluge</b><br>
        <div  v-for="(s,i) in this.addServices"  :key="i">
            <input type="checkbox" width="20" height="20"  @change="checkAddService(s)">{{s}}
        </div>
      </div>

      <br>
      <div class="checkBox mt-2 " >
        <b>Pecaroška oprema</b><br>
          <div v-for="(s,i) in this.fishEquipment"  :key="i">
            <input type="checkbox" width="20" height="20"  @change="checkFishEq(s)">{{s}}
          </div>
        </div>

    </div>

    <div class="col-3">
      <label> <b>Uslovi otkazivanja rezervacije</b></label>
      <br>

      <div class="p-2" style="border-style: solid; border-width: medium;">

        <p>Za odustanak od rezervacije u roku <b>manje od 5</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percents[0]"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 5</b> dana plaća se <input type="number"
                                                                                    v-model="ship.percents[1]"
                                                                                    size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 10</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percents[2]"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 15</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percents[3]"
                                                                                     size="15"/> %
          ukupnog iznosa</p>

      </div>
    </div>
    <br>

    <button id="cancelButton" @click="back">Odustani</button>
    <button id="addButton" @click="addShip">Dodaj</button>
  </div>
  </div>
</template>

<script>
import OpenMaps from "@/components/VueMaps";
import ShipService from "@/servieces/ship_owner/ShipService";
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
import AdditionalServicesService from "@/servieces/AdditionalServicesService";
import FishingEquipmentService from "@/servieces/FishingEquipmentService";
import BehaviorRulesService from "@/servieces/BehaviorRulesService";
import swal from "sweetalert2";
export default {
  name: "addShip",
  components:{
    OpenMaps,
    shipOwnerHeader
  },
  created() {
    this.equipments.push("GPS");
    this.equipments.push("radar");
    this.equipments.push("fishfinder");
    this.equipments.push("VHF radio");
    console.log(this.equipments);

    AdditionalServicesService.getAll().then((response)=>
        {
          this.addServices = response.data;
          console.log(this.addServices);
        }

    );

    FishingEquipmentService.getAll().then((response)=>
        {
          this.fishEquipment = response.data;
        }
    );

    BehaviorRulesService.getAll().then((response)=>
        {
          this.behRules = response.data;
        }
    )

    console.log(this.behRules);
  },
  methods:{
    updateCoordinats(lon, lat){
      this.ship.longitude = lon;
      this.ship.latitude = lat;
      console.log(lon, lat)
    },
    addShip(){

      this.ship.ownerId =JSON.parse(localStorage.user).id;
      console.log(this.ship);
      if(this.Validate()){
        ShipService.saveShip(this.ship).then((response)=>{
          if(this.ship.name === response.data.name){ //bar ta provera
            swal.fire({title:'Novi brod je uspešno dodat!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
            this.$router.push('/shipOwner/home');
          }
          else{
            swal.fire({title:'Novi brod nije dodat!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
        })
      }
    },
    checkAddService(s){
      if(this.ship.additionalServices.includes(s)){
        var i = this.ship.additionalServices.indexOf(s);
        this.ship.additionalServices.splice(i,1);

      }
      else{
        this.ship.additionalServices.push(s);
      }
    },
    checkFishEq(s){
      if(this.ship.fishingEquipment.includes(s)){
        var i = this.ship.fishingEquipment.indexOf(s);
        this.ship.fishingEquipment.splice(i,1);

      }
      else{
        this.ship.fishingEquipment.push(s);
      }
    },
    checkBehRule(s){
      if(this.ship.behavioralRules.includes(s)){
        var i = this.ship.behavioralRules.indexOf(s);
        this.ship.behavioralRules.splice(i,1);

      }
      else{
        this.ship.behavioralRules.push(s);
      }
    },
    Validate(){
      if(this.ship.name === ""){
        swal.fire({title:'Unesite naziv broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.longitude==="" && this.ship.latitude===""){
        swal.fire({title:'Izaberite adresu!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.type === ""){
        swal.fire({title:'Unesite tip broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.length ===""){
        swal.fire({title:'Unesite dužinu broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.engineDesignation===""){
        swal.fire({title:'Unesite oznaku motora!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.enginePower===""){
        swal.fire({title:'Unesite snagu motora!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.maxSpeed===""){
        swal.fire({title:'Unesite maksimalnu brzinu broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.capacity===""){
        swal.fire({title:'Unesite kapacitet broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.price===""){
        swal.fire({title:'Unesite cenu!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.behavioralRules.length == 0){
        swal.fire({title:'Pravila ponašanja nisu izabrana!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.additionalServices.length == 0){
        swal.fire({title:'Dodatne usluge nisu izabrane!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.fishingEquipment.length == 0){
        swal.fire({title:'Pecaroška oprema nije izabrana!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.navigationEquipment == 0){
        swal.fire({title:'Navigaciona oprema nije izabrana!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.ship.description ==""){
        swal.fire({title:'Unesite opis broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }

      else{
        return true;
      }
    },
    checkNavEq(s){
      console.log(s);
      this.ship.navigationEquipment = s;
    },
    back(){
      this.$router.push('/shipOwner/home');
    }
  },
  data(){
    return{

      equipments:[],
      br1:"zabranjeno pušenje",
      br2:"dozvoljeni kućni ljubimci",
      addServices:"",
      fishEquipment:"",
      behRules:"",
      ship: {

        name: "",
        ownerId:"",
        longitude:0,
        latitude:0,
        description: "",
        length:"",
        type:"",
        engineDesignation:"",
        enginePower:"",
        maxSpeed:"",
        additionalServices:[],
        fishingEquipment:[],
        behavioralRules: [],
        images: [],
        cancelConditions: [],
        price: "",
        capacity:"",
        navigationEquipment: "",
        days: ['5', '10', '15', '20'],
        percents: [0, 0, 0, 0],
        experienceReviews: [],
      }
    }
  }
}
</script>

<style scoped>
#dataForm {
  text-align: center;
  border: 2px solid black;
  background-color: #E8E8E8;
  font-size: 17px;
  padding: 20px;
  width: 90%;
  height: 90%;
  position: absolute;
  left: 37%;
  top: 50%;
  transform: translate(-35%, -50%);
  color: #5F9F9F;
}

.checkBox{
  border:2px solid #ccc; width:300px; height: 100px; overflow-y: scroll;
  float:left;
  text-align: left;
  padding-left:10pt;
}
#addButton{
  width:200px;
  margin-left:50px;

}
#cancelButton{
  width:200px;
  margin-left: 400px;
}
#behaviorRules{

  margin-top:40px;
}
#left{
  float:left;
}
#middle{
  float:right;
}
.column {
  float: left;
  width: 25%;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

#equipment{
  margin-bottom:30px;
}

button{
  color:whitesmoke;
  background-color: #31708E;
  border-color: #31708E;
  font-size:20px;
}
</style>