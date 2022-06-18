<template>
  <div class="card" style="width: 18rem;">
    <div class="card-body" v-if="this.scale1.userType==='REGULAR'">
      <label class="card-title">{{scale1.userType}}</label> <img :src="this.getImage()" style="max-width: 50px; max-height: 50px">
      <br>
      <label class="card-text m-2" >{{discountLabel}} </label> <input type="number" v-model="scale1.discount" style="max-width: 50px"> <label>%</label>
      <label class="card-text m-2">{{trasholdLabel}} </label> <input type="number" :disabled="true" v-model="scale1.trashold" style="max-width: 50px">
      <p class="card-text m-2" >{{fromDateLabel}}  </p> <Datepicker v-model="scale1.startDate"></Datepicker>
      <p class="card-text m-2">{{userRole}} {{roleClientSerbian(scale1.role)}}</p>

      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-primary me-md-2" type="button" @click="defineNewScale">Izmeni</button>
      </div>


    </div>


    <div class="card-body"  v-if="this.scale1.userType!=='REGULAR'">

      <label class="card-title">{{scale1.userType}}</label> <img :src="this.getImage()" style="max-width: 50px; max-height: 50px">
      <br>
      <label class="card-text m-2" >{{discountLabel}} </label> <input type="number" v-model="scale1.discount" style="max-width: 50px"> <label>%</label>
      <label class="card-text m-2">{{trasholdLabel}} </label> <input type="number" v-model="scale1.trashold" style="max-width: 50px">
      <p class="card-text m-2" >{{fromDateLabel}}  </p> <Datepicker v-model="scale1.startDate"></Datepicker>
      <p class="card-text m-2">{{userRole}} {{roleClientSerbian(scale1.role)}}</p>

      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-primary me-md-2" type="button" @click="defineNewScale">Izmeni</button>
      </div>


    </div>
  </div>
</template>

<script>
import Datepicker from "@vuepic/vue-datepicker";
import LoyaltyProgramService from "@/servieces/LoyaltyProgramService";

export default {
  name: "loyaltyCard",
  props:['scale'],
  components:{
    Datepicker,
  },

  created :function () {
    console.log(this.scale)
    this.scale1=this.scale;
  },

  methods:{
    defineNewScale(){
        LoyaltyProgramService.defineNewScale(this.scale1.id, this.scale1).then((response)=>{
          this.scale1 = response.data;
          console.log(response);
        }).catch(function (error) {
          console.log(error.toJSON());
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            // The request was made but no response was received
            // error.request is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.log(error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
          }
          console.log(error.config);

        });

    },

    getImage(){
      if (this.scale1.userType==='REGULAR')
        return this.regularImgPath;
      else if (this.scale1.userType==='SILVER')
        return this.silverImgPath;
      else
        return this.goldImgPath
    },

    roleClientSerbian(role){
      if (role==='ROLE_CLIENT')
        return 'klijent';
      else if (role === 'ROLE_COTTAGE_OWNER')
        return 'vlasnik vikendice';
      else if (role === 'ROLE_SHIP_OWNER')
        return 'vlasnik broda';
      else
        return 'instruktor pecanja';

    }

  },

  data() {
    return {
      discountLabel:"popust: ",
      trasholdLabel: "minimalan broj poena: ",
      fromDateLabel: "loyalty program važi od: ",
      userRole:"uloga korisnika: ",

      scale1:{
        Id:"",
        discount:"",
        startDate:"",
        endDate:"",
        trashold:"",
        userType:"",
        role:""
      },

      regularImgPath: require("@/assets/icons/bronze.png"),
      silverImgPath: require("@/assets/icons/silver.png"),
      goldImgPath: require("@/assets/icons/gold.png")
  }
  }
}
</script>

<style scoped>

</style>