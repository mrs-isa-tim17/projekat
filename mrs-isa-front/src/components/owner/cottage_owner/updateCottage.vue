<template>
  <div>
    <div class="alert alert-success alert-dismissible fade show" id="successChange" role="alert" style="visibility: hidden;">
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      Uspesno izmenjeno!
    </div>
  <div id="dataForm" class="row">

    <div class="column">
      <label>Naziv vikendice</label><br>
      <input id="name" name="name" type="text" style="width: 250px;margin-left:0px;" v-model="cottage.name" ><br>
      <br>
      <label>Adresa vikendice</label><br>
      <open-maps :index="cottage.id" style="width: 280px; height: 200px;" :lon="cottage.longitude" :lat="cottage.latitude" @coordinate-changed="updateCoordinats"></open-maps>
      <br>

      <label>Opis vikendice</label><br>
      <textarea id="description" name="description" type="text" rows="5" cols="26" v-model="cottage.description"></textarea> <br>

    </div>
    <div class="column">

      <label>Broj soba:  </label>
      <input id="roomsNumber" name="roomsNumber" type="number" size="6" style="width:100px;margin-left: 10px;" v-model="cottage.roomQuantity"><br><br>
      <label>Kapacitet:  </label>
      <input type="number" ref="input"  size="30" style="width:100px;margin-left: 10px;" v-model="cottage.bedQuantity"><br><br>
      <label style="margin-left: 27px;">Cena:  </label>
      <input id="price" name="price" type="text" style="width:100px;margin-left: 10px;" v-model="cottage.price">

    </div>

    <div class="column" >
      <label for="formFile" class="form-label">Slike vikendice</label><br>
      <input class="form-control" type="file" :value="cottage.images" id="formFile" @change="onFileSelected">
      <br>
      <div id="equipment">
        Dodatne usluge
        <div align="left" class="checkBox">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq1 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq1>{{eq1}}</label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq2 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq2>
              {{eq2}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq3 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq3>
              {{eq3}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq4 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq4>
              {{eq4}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq5 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq5>
              {{eq5}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq6 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq6>
              {{eq6}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=eq7 id="flexCheckDefault"
                   v-model="cottage.additionalEquipment">
            <label class="form-check-label" :for=eq7>
              {{eq7}}
            </label>
          </div>
        </div>
      </div>
      <br>


      <div id="behaviorRules">
        <br>
        Pravila ponašanja<br>
        <div class="checkBox">
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=br1 id="flexCheckDefault"
                   v-model="cottage.behavioralRules">
            <label class="form-check-label" :for=br1>
              {{br1}}
            </label>
          </div>
          <div align="left" class="form-check">
            <input class="form-check-input" type="checkbox" :value=br2 id="flexCheckDefault"
                   v-model="cottage.behavioralRules">
            <label class="form-check-label" :for=br2>
              {{br2}}
            </label>
          </div>
        </div>
      </div>

    <br>
     <button id="addButton"  style="margin-top:50px;" @click="updateCottage">Potvrdi izmene</button>
      <br><br>
      <button id="cancelButton" @click="back">Odustani</button><br>
      <br>
    </div>

    <div class="column">
      <label> Uslovi otkazivanja rezervacije</label>
      <br>

      <div class="p-2" style="border-style: solid; border-width: medium;">

        <p>Za odustanak od rezervacije u roku <b>manje od 5</b> dana plaća se <input type="number"
                                                                                     v-model="cottage.p1"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 5</b> dana plaća se <input type="number"
                                                                                    v-model="cottage.p2"
                                                                                    size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 10</b> dana plaća se <input type="number"
                                                                                     v-model="cottage.p3"
                                                                                     size="15"/> %
          ukupnog iznosa</p>
        <p>Za odustanak od rezervacije u roku <b>više od 15</b> dana plaća se <input type="number"
                                                                                     v-model="cottage.p4"
                                                                                     size="15"/> %
          ukupnog iznosa</p>

      </div>
    </div>
    <br>


  </div>
  </div>
</template>

<script>

import CottageService from "@/servieces/cottage_owner/CottageService";
import OpenMaps from "@/components/VueMaps";
export default {
  name: "addCottage",
  components:{
    OpenMaps
  },
  created:
  function() {
    let id = this.$route.params.id
    CottageService.getCottage(id).then((response) => {
      this.cottage = response.data;
      console.log(this.cottage);
    })
  },

  methods:{
    updateCoordinats(lon, lat){
      this.cottage.longitude = lon;
      this.cottage.latitude = lat;
      console.log(lon, lat)
    },
    updateCottage(){
      CottageService.updateCottage(this.cottage).then((response)=>
          {console.log(response.data);}
      )
      ;
      document.getElementById("successChange").style.visibility = 'visible';
    },
    back(){
      this.$router.push('/owner/cottages');
    },

  },
  data(){
    return{
      eq1:"bazen",
      eq2:"parking",
      eq3:"kablovska TV",
      eq4:"WiFi",
      eq5:"društvene igre",
      eq6:"besplatne pešačke ture",
      eq7:"bickili",
      br1:"zabranjeno pušenje",
      br2:"dozvoljeni kućni ljubimci",
      cottage: {
        id:2,
        name: "",
        longitude:0,
        latitude:0,
        description: "",
        behavioralRules: [],
        images: [],
        cancelConditions: [],
        price: "",
        bedQuantity: "",
        roomQuantity:"",
        additionalEquipment: [],
        days: ['5', '10', '15', '20'],
        p1:"",
        p2:"",
        p3:"",
        p4:"",
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
  height: 80%;
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


}
#cancelButton{
  width:200px;

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