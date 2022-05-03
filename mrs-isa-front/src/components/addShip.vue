<template>
  <div id="dataForm" class="row">
    <div class="column">
      <label>Naziv broda</label><br>
      <input id="name" name="name" type="text" style="width: 250px;margin-left:0px;" v-model="ship.name" ><br>
      <br>
      <label>Adresa broda</label><br>
      <open-maps :lon="ship.longitude" :lat="ship.latitude" @coordinate-changed="updateCoordinats" style="width: 250px; height: 250px; margin-left:30px;"></open-maps>
      <br>
      <label>Opis broda</label><br>
      <textarea id="description" name="description" type="text" rows="5" cols="26" v-model="ship.description"></textarea> <br>
      <br>
    </div>
    <div class="column">
      <input id="type" name="type" type="text" style="width:100px;margin-left: 10px;" v-model="ship.type" placeholder="Tip"><br>
      <br>
      <input id="length" name="length" type="text" style="width:100px;margin-left: 10px;" v-model="ship.length" placeholder="Dužina"><br>
      <br>
      <input id="engine_designation" name="engine_designation" type="text" style="width:100px;margin-left: 10px;" placeholder="Broj motora" v-model="ship.engineDesignation"><br>
      <br>
      <input id="engine_power" name="engine_power" type="text" style="width:100px;margin-left: 10px;" placeholder="Snaga motora" v-model="ship.enginePower"><br>
      <br>
      <input id="max_speed" name="max_speed" type="text" style="width:100px;margin-left: 10px;" v-model="ship.maxSpeed" placeholder="Maksimalna brzina"><br>
      <br>
      <input type="number" ref="input"  size="30" style="width:100px;margin-left: 10px;" v-model="ship.capacity" placeholder="Kapacitet"><br>
      <br>
      <input id="price" name="price" type="text" style="width:100px;margin-left: 10px;" v-model="ship.price" placeholder="Cena"><br>
    </div>

    <div class="column" >
      <label for="formFile" class="form-label">Slike broda</label><br>
      <input class="form-control" type="file" :value="ship.images" id="formFile" @change="onFileSelected">
      <br>
      <div id="equipment">
        Navigaciona oprema
        <div align="left" class="checkBox">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq1 id="flexCheckDefault"
                   v-model="ship.navigationEquipment">
            <label class="form-check-label" :for=eq1>{{eq1}}</label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq2 id="flexCheckDefault"
                   v-model="ship.navigationEquipment">
            <label class="form-check-label" :for=eq2>
              {{eq2}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq3 id="flexCheckDefault"
                   v-model="ship.navigationEquipment">
            <label class="form-check-label" :for=eq3>
              {{eq3}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq4 id="flexCheckDefault"
                   v-model="ship.navigationEquipment">
            <label class="form-check-label" :for=eq4>
              {{eq4}}
            </label>

        </div>
      </div>
      <br>


      <div id="behaviorRules">
        <br>
        Pravila ponašanja<br>
        <div class="checkBox">
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=br1 id="flexCheckDefault"
                   v-model="ship.behavioralRules">
            <label class="form-check-label" :for=br1>
              {{br1}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=br2 id="flexCheckDefault"
                   v-model="ship.behavioralRules">
            <label class="form-check-label" :for=br2>
              {{br2}}
            </label>
          </div>
        </div>
      </div>
    </div>

    <div class="column">
      <label> Uslovi otkazivanja rezervacije</label>
      <br>

      <div class="p-2" style="border-style: solid; border-width: medium;">

        <p>Za odustanak od rezervacije u roku <b>manje od 5</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percentage[0]"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 5</b> dana plaća se <input type="number"
                                                                                    v-model="ship.percentage[1]"
                                                                                    size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 10</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percentage[2]"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 15</b> dana plaća se <input type="number"
                                                                                     v-model="ship.percentage[3]"
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
import ShipService from "@/servieces/ShipService";
export default {
  name: "addShip",
  components:{
    OpenMaps
  },
  methods:{
    updateCoordinats(lon, lat){
      this.ship.longitude = lon;
      this.ship.latitude = lat;
      console.log(lon, lat)
    },
    addShip(){
      ShipService.saveShip(this.ship);
    },
    back(){
      this.$router.push('/owner/ships');
    }
  },
  data(){
    return{
      eq1:"GPS",
      eq2:"radar",
      eq3:"fishfinder",
      eq4:"VHF radio",
      br1:"zabranjeno pušenje",
      br2:"dozvoljeni kućni ljubimci",
      ship: {

        name: "",
        longitude:0,
        latitude:0,
        description: "",
        length:"",
        type:"",
        engineDesignation:"",
        enginePower:"",
        maxSpeed:"",

        behavioralRules: [],
        images: [],
        cancelConditions: [],
        price: "",
        capacity:"",
        navigationEquipment: [],
        days: ['5', '10', '15', '20'],
        percentage: ['0', '0', '0', '0'],
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
</style>