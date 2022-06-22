<template>

    <cottageOwnerHeader></cottageOwnerHeader>

  <div class="container">
    <div class="row mt-5">
      <div class="col-3">

         <input id="name" name="name" type="text" placeholder="Naziv vikendice" style="width: 200px;margin-left:0px;" v-model="cottage.name" @blur="checkName"><br>
         <br>
         <label>Adresa vikendice</label><br>
         <open-maps :lon="cottage.longitude" :lat="cottage.latitude" @coordinate-changed="updateCoordinats" style="width: 200px; height: 200px; margin-left:50px;"></open-maps>
         <br>
         <textarea placeholder="Promotivni opis vikendice" id="description" name="description" type="text" rows="5" cols="26" v-model="cottage.description"></textarea> <br>
         <br>
       </div>
      <div class="col-3">
        <input id="type" name="type" type="text" style="max-width:200px;margin-left: 10px;" v-model="cottage.roomQuantity" @blur="checkRoomQuantity" placeholder="Broj soba"><br>
        <br>
        <input id="length" name="length" type="text" style="max-width:200px;margin-left: 10px;" v-model="cottage.bedQuantity" @blur="checkCapacity" placeholder="Kapacitet"><br>
        <br>
        <input id="engine_designation" name="engine_designation" type="text" style="max-width:200px;margin-left: 10px;" @blur="checkPrice" placeholder="Cena" v-model="cottage.price"><br>
        <br>
        <label for="formFile" class="form-label">Slike vikendice</label><br>
        <input class="form-control" type="file" :value="cottage.images" id="formFile" @change="onFileSelected">
      <!--  <upload-images-modal :index="imageIndex" :header="imageHeader"></upload-images-modal>-->
        </div>

      <div class="col-3" >

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

      </div>
      <div class="col-3">
        <label> <b>Uslovi otkazivanja rezervacije</b></label>
        <br>

        <div class="p-2" style="border-style: solid; border-width: medium;">

          <p>Za odustanak od rezervacije u roku <b>manje od 5</b> dana plaća se <input type="number"
                                                                                       v-model="cottage.percents[0]"
                                                                                       size="15"/> %
            ukupnog iznosa</p>
          <p>Za odustanak od rezervacije u roku <b>više od 5</b> dana plaća se <input type="number"
                                                                                      v-model="cottage.percents[1]"
                                                                                      size="15"/> %
            ukupnog iznosa</p>
          <p>Za odustanak od rezervacije u roku <b>više od 10</b> dana plaća se <input type="number"
                                                                                       v-model="cottage.percents[2]"
                                                                                       size="15"/> %
            ukupnog iznosa</p>
          <p>Za odustanak od rezervacije u roku <b>više od 15</b> dana plaća se <input type="number"
                                                                                       v-model="cottage.percents[3]"
                                                                                       size="15"/> %
            ukupnog iznosa</p>

        </div>
      </div>
      <br>

      <button id="cancelButton" @click="back">Odustani</button>
      <button id="addButton" @click="addCottage">Dodaj</button>
    </div>


   </div>


</template>

<script>
import OpenMaps from "@/components/VueMaps";

//import firstDataCottage from "@/components/addCottage/firstDataCottage";
//import secondDataCottage from "@/components/addCottage/secondDataCottage";

//import FormWizard from 'vue-step-wizard';
//import TabContent from 'vue-step-wizard';
import "vue-step-wizard/dist/vue-step-wizard.css";


import CottageService from "@/servieces/cottage_owner/CottageService";
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader"
import AdditionalServicesService from "@/servieces/AdditionalServicesService";

import BehaviorRulesService from "@/servieces/BehaviorRulesService";
import swal from "sweetalert2";

export default {
  name: "addCottage",
  components:{


    OpenMaps,
    cottageOwnerHeader,
   // firstDataCottage,secondDataCottage,
    //FormWizard,TabContent
  },
  methods:{
      updateCoordinats(lon, lat){
        this.cottage.longitude = lon;
        this.cottage.latitude = lat;
        console.log(lon, lat)
      },
    checkName(){
      if(this.cottage.name === ""){
        swal.fire({title:'Unesite naziv broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }},
    checkRoomQuantity(){
      if(this.cottage.roomQuantity === ""){
        swal.fire({title:'Unesite broj soba!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(!Number.isInteger(parseInt(this.cottage.roomQuantity))){
        swal.fire({title:'Polje Broj soba mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      },
    onFileSelected (event) {
      this.cottage.images.push(event.target.files[0].name);

      console.log(this.cottage.images);

    },
    checkCapacity(){
      if(this.cottage.bedQuantity === ""){
        swal.fire({title:'Unesite kapacitet!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(!Number.isInteger(parseInt(this.cottage.bedQuantity))){
        swal.fire({title:'Polje Kapacitet mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
    },
    checkPrice(){
      if(this.cottage.price === ""){
        swal.fire({title:'Unesite cenu!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(!Number.isInteger(parseInt(this.cottage.price))){
        swal.fire({title:'Polje Cena mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
    },
    addCottage(){
      this.cottage.ownerId =JSON.parse(localStorage.user).id;
        if(this.Validate()) {

          CottageService.saveCottage(this.cottage).then((response) => {
            console.log(response.data);
            if (this.cottage.name === response.data.name) { //bar ta provera
              swal.fire({
                title: 'Nova vikendica je uspešno dodata!',
                background: 'white',
                color: '#687864',
                confirmButtonColor: '#687864'
              });
              this.$router.push('/cottageOwner/home');
            } else {
              swal.fire({
                title: 'Nova vikendica nije dodata!',
                background: 'white',
                color: '#687864',
                confirmButtonColor: '#687864'
              });
            }
          })
        }
             },
    checkAddService(s){
      if(this.cottage.additionalServices.includes(s)){
        var i = this.cottage.additionalServices.indexOf(s);
        this.cottage.additionalServices.splice(i,1);

      }
      else{
        this.cottage.additionalServices.push(s);
      }
    },
    checkBehRule(s){
      if(this.cottage.behavioralRules.includes(s)){
        var i = this.cottage.behavioralRules.indexOf(s);
        this.cottage.behavioralRules.splice(i,1);

      }
      else{
        this.cottage.behavioralRules.push(s);
      }
    },
    Validate(){
      if(this.cottage.name === ""){
        swal.fire({title:'Unesite naziv broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.longitude==0 && this.cottage.latitude==0){
        swal.fire({title:'Izaberite adresu!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.type === ""){
        swal.fire({title:'Unesite tip broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.length ===""){
        swal.fire({title:'Unesite dužinu broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.engineDesignation===""){
        swal.fire({title:'Unesite oznaku motora!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.enginePower===""){
        swal.fire({title:'Unesite snagu motora!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.maxSpeed===""){
        swal.fire({title:'Unesite maksimalnu brzinu broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.capacity===""){
        swal.fire({title:'Unesite kapacitet broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.price===""){
        swal.fire({title:'Unesite cenu!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.behavioralRules.length == 0){
        swal.fire({title:'Pravila ponašanja nisu izabrana!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.additionalServices.length == 0){
        swal.fire({title:'Dodatne usluge nisu izabrane!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(this.cottage.description ==""){
        swal.fire({title:'Unesite opis broda!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        return false;
      }
      else if(!Number.isInteger(parseInt(this.cottage.roomQuantity))){
        swal.fire({title:'Polje Broj soba mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(!Number.isInteger(parseInt(this.cottage.bedQuantity))){
        swal.fire({title:'Polje Kapacitet mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(!Number.isInteger(parseInt(this.cottage.price))){
        swal.fire({title:'Polje Cena mora biti broj!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }

      else{
        return true;
      }
    },
    back(){
      this.$router.push('/cottageOwner/home');
    }
  },
  data(){
    return{
      addServices:null,
      behRules:null,
      imageHeader:"headerImg",
      imageIndex:"indexImg",
      cottage: {
        ownerId:"",
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
        additionalServices: [],
        days: ['5', '10', '15', '20'],
        percents: ['0', '0', '0', '0'],
        experienceReviews: [],
      }
    }
  },
  created() {
    AdditionalServicesService.getAll().then((response)=>
        {
          this.addServices = response.data;
          console.log(this.addServices);
        }

    );



    BehaviorRulesService.getAll().then((response)=>
        {
          this.behRules = response.data;
        }
    )
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
  top: 55%;
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
button{
  color:whitesmoke;
  background-color: #31708E;
  border-color: #31708E;
  font-size:20px;
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