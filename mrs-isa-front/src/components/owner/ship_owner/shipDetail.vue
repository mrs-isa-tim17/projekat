<template>
  <shipOwnerHeader></shipOwnerHeader>
  <div class="container" >
    <div class="row mt-4" >
      <div class="col-sm-3 align-content-start">
        <br><br><br>
        <star-rating :rating="rating" :round-start-rating="false" style="width: 15px;"></star-rating>
        <!--    <star-rating :rating="4" :starWidth="20" :star-style="config "></star-rating><br>    -->
        <experience-reviews-modal :key="reviewKey" :header="experienceReviewsHeader" :index="experienceReviewsIndex" :cottage="this.ship"></experience-reviews-modal><br><br>
        <br>
        <br>
        <br>
        <div style="text-align: left;font-size: 15pt;border:1px solid #31708E;padding:2pt;">
        <label><b>Dužina broda:&nbsp;</b>{{ship.length}}&nbsp;m</label><br>
        <label><b>Snaga motora:&nbsp;</b>{{ship.enginePower}}&nbsp;W</label><br>
        <label><b>Oznaka motora:&nbsp;</b>{{ship.engineDesignation}}</label><br>
        <label><b>Maksimalna brzina:&nbsp;</b>{{ship.maxSpeed}}&nbsp;m/s</label><br>
          <label><b>Navigaciona oprema:&nbsp;</b>{{ship.navigationEquipment}}</label><br>
        </div>
      </div>
      <div class="col-sm-7 align-content-center" >
        <div >
          <p style="font-size:30px;display: inline-block">{{ ship.name }} </p>
          <edit-offer-name-modal :index="editName" :header="editNameHeader" :name="ship.name" @edit-name="editOfferName"></edit-offer-name-modal>
        </div>

        <div style="text-align: left"><img :src="ship.images[0]" /><br></div>
        <!--<offer-carousel :images="cottage.images" style="width:500px;"></offer-carousel>-->
        <!-- <upload-images-modal :header="uploadHeader" :index="uploadIndex"></upload-images-modal>-->

        <div class="row pt-2 mt-3">
          <div class="col-5" style="text-align: left">
            <p style="font-size:20px;display: inline-block;">{{ ship.description }}</p>
          </div>
          <div class="col-1">
            <edit-offer-description :header="editDescHeader" :index="editDesc" :description="ship.description" @edit-description="editDescription"></edit-offer-description>
          </div>

          <div class="col-6">
            <bird-vue-map :index="ship.id" :lon="ship.longitude" :lat="ship.latitude" style="width: 200px;height:150px;"></bird-vue-map>
          </div>
        </div>
        <br>
        <div style="text-align: left;font-size: 20px;text-decoration: underline;"><b>Dodatne usluge</b>
          <edit-offer-services-modal :servicesList="this.addServices" :header="editServHeader" :index="editServ" @edit-services="editServices"></edit-offer-services-modal>
          <br>
          <div class="" style="display: inline-block;" v-for="(s,i) in this.ship.additionalServices" :key="i">
            <i
                class=" mr-1 text-primary"
                :class="icons[s]"
            ></i>&nbsp;<span class="text-muted">{{s}}&nbsp;&nbsp;&nbsp;</span>
          </div>
        </div><br>
        <div style="text-align: left;font-size: 20px;"><b>Pecaroška oprema</b>
          <edit-fishing-equipment :eqList="this.fishEquipment" :header="headerEq" :index="indexEq" @edit-eq="editEquipment"></edit-fishing-equipment>
          <br>
          <div class="" style="display: inline-block;" v-for="(s,i) in this.ship.fishingEquipment" :key="i">
            <i
                class=" mr-1 text-primary"
                :class="behavior_icons[s]"
            ></i>&nbsp;<span class="text-muted">{{s}}&nbsp;&nbsp;&nbsp;</span>
          </div>
        </div>
        <div style="text-align: left;font-size: 20px;"><b>Kućni red</b>
          <edit-behavior-rules-modal :behaviorList="this.behavior" :header="behaviorHeader" :index="editBeh" @edit-rules="editRules"></edit-behavior-rules-modal>
          <br>
          <div class="" style="display: inline-block;" v-for="(s,i) in ship.behavioralRules" :key="i">
            <i
                class=" mr-1 text-primary"
                :class="behavior_icons[s]"
            ></i>&nbsp;<span class="text-muted">{{s}}&nbsp;&nbsp;&nbsp;</span>
          </div>
        </div>
        <br>

        <div >

        </div>

      </div>
      <div class="col-md-2 " style="text-align: left;">
        <br><br><br>
        <div style="font-size: 36px;"><i class="fa fa-users"></i> {{ ship.capacity }}
          <edit-number-modal :header="editPeopleHeader" :index="editPeople" :number="ship.capacity" @edit-number="editCapacity"></edit-number-modal>
        </div>

        <div style="font-size: 28px;"> {{ ship.price }} din/noć
          <edit-price-modal :header="editPriceHeader" :index="editPriceIndex" :price="ship.price" @edit-price="editPrice"  ></edit-price-modal>
        </div>
        <div class="row mt-5 justify-content-center" style="border:2px solid">

          <div class="col-8" style="font-weight: bold;color:red;">Uslovi otkazivanja rezervacije</div>
          <div class="col-4">
            <edit-cancel-conditions-modal :key="ccKey" :header="editCCHeader" :index="editCCIndex" :offer="ship" @edit-cancel-condition="editCancelConditions" ></edit-cancel-conditions-modal></div>
          <br>
          <p><b>*</b>Ukoliko se rezervacija otkaže {{this.d1}} dana pre početka, mora se uplatiti {{this.p1}}% od ukupne cene rezervacije.</p>
          <p><b>*</b>Ukoliko se rezervacija otkaže {{this.d2}} dana pre početka, mora se uplatiti {{this.p2}}% od ukupne cene rezervacije.</p>
          <p><b>*</b>Ukoliko se rezervacija otkaže {{this.d3}} dana pre početka, mora se uplatiti {{this.p3}}% od ukupne cene rezervacije.</p>
          <p><b>*</b>Ukoliko se rezervacija otkaže {{this.d4}} dana pre početka, mora se uplatiti {{this.p4}}% od ukupne cene rezervacije.</p>
        </div>
      </div>
    </div>
  </div>

</template>

<script>



import ReviewServce from "@/servieces/ReviewServce";

//import StarRating from 'vue-dynamic-star-rating'

//import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
// import { Splide, SplideSlide } from '@splidejs/vue-splide';
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
import BirdVueMap from "@/components/main_site/birdVueMap";
import EditOfferNameModal from "@/components/owner/cottage_owner/editOfferNameModal";
import swal from "sweetalert2";
import EditOfferDescription from "@/components/owner/cottage_owner/editOfferDescriptionModal";
import EditBehaviorRulesModal from "@/components/owner/cottage_owner/editBehaviorRulesModal";
import EditNumberModal from "@/components/owner/cottage_owner/editNumberModal";
import ExperienceReviewsModal from "@/components/owner/cottage_owner/experienceReviewsModal";
import EditPriceModal from "@/components/owner/cottage_owner/editPriceModal"
import EditCancelConditionsModal from "@/components/owner/cottage_owner/editCancelConditionsModal";
//import UploadImagesModal from "@/components/owner/cottage_owner/uploadImagesModal";
//import OfferCarousel from "@/components/main_site/offer_profile/offerCarousel";
//import lightbox from "vlightbox"
// import VueGallerySlideshow from 'vue-gallery-slideshow'
import StarRating from 'vue-star-rating'
import ShipService from "@/servieces/ship_owner/ShipService";
import EditOfferServicesModal from "@/components/owner/cottage_owner/editOfferServicesModal";
import AdditionalServicesService from "@/servieces/AdditionalServicesService";
import EditFishingEquipment from "@/components/ship_owner/editFishingEquipment";
import FishingEquipmentService from "@/servieces/FishingEquipmentService";
import BehaviorRulesService from "@/servieces/BehaviorRulesService";
export default {
  name: "shipDetail",
  components: {
    EditFishingEquipment,
    EditOfferServicesModal,
    //OfferCarousel,
    //  UploadImagesModal,
    StarRating,
    EditCancelConditionsModal,
    ExperienceReviewsModal,
    EditNumberModal,
    EditBehaviorRulesModal,
    EditOfferDescription,
    EditOfferNameModal,
    EditPriceModal,
    BirdVueMap,
    shipOwnerHeader,

    //StarRating,

    //lightbox
    //FontAwesomeIcon
    // VueGallerySlideshow
  },
  created:
      function () {
        let id = this.$route.params.id;
        ShipService.getShip(id).then((response) => {
          console.log(response.data);
          this.ship = response.data;
          this.p1=this.ship.percents[0];
          this.p2=this.ship.percents[1];
          this.p3=this.ship.percents[2];
          this.p4=this.ship.percents[3];
          this.d1=this.ship.days[0];
          this.d2=this.ship.days[1];
          this.d3=this.ship.days[2];
          this.d4=this.ship.days[3];
          this.reviewKey++;
          this.ccKey++;
          for (let i = 0; i < this.ship.images.length; i++) {

            var img = "@/assets/" + this.ship.images[i];

            this.imagesCottage.push(img);
          }
        });
          ReviewServce.getRating(id).then((response) =>
          {
            this.rating = response.data;
          });
          console.log(this.ship);
        console.log(this.ship.percents);
        console.log(this.ship.days);
          console.log(this.ship.additionalServices);



        AdditionalServicesService.getAll().then((response)=>
            {
              this.addServices = response.data;
              console.log(this.addServices);
            }

        );

        FishingEquipmentService.getAll().then((response)=>
            {
              this.fishEquipment = response.data;
              console.log(response.data);
            }
        );

        BehaviorRulesService.getAll().then((response)=>
            {
              this.behavior = response.data;
            }
        )
      },
  data() {
    return {
      reviewKey: 0,
      ccKey:0,
      d1:"",d2:"",d3:"",d4:"",
      p1:"",p2:"",p3:"",p4:"",
      ship: {
        id: "",
        name: "",
        longitude: 0,
        latitude: 0,
        description: "",
        behavioralRules: [],
        images: [],
        price: "",
        priceStartDate:"",
        capacity:"",
        fishingEquipment:[],
        type:"",
        length:"",
        engineDesignation:"",
        enginePower:"",
        maxSpeed:"",
        navigationEquipment: [],
        days: [],
        percents:[],
        experienceReviews: []
      },
      width: 600,
      height: 400,
      rating:0,
      imagesCottage: [],
      images: [
        {
          src: 'https://unsplash.it/500',
          caption: 'Image 1',
        },
        {
          src: 'https://unsplash.it/501',
        },
      ],
      index: 0,
      icons:{
        "restoran": "fas fa-utensils",
        "doručak": "fas fa-bread-slice",
        "bar": "fas fa-glass-martini-alt",
        "wifi": "fas fa-wifi",
        "bazen": "fas fa-swimming-pool",
        "spa": "fas fa-spa",
        "teretana": "fas fa-dumbbell",
        "tv": "fa-solid fa-tv",
        "besplatne pešačke ture": "fa-solid fa-person-hiking"
      },
      behavior_icons:{
        "dozvoljeno pušenje": "fas fa-smoking",
        "zabranjeno pušenje": "fas fa-smoking-ban",
      },
      services:["bazen", "restoran","spa"],
      behavior:[],
      config: {
        fullStarColor: '#ed8a19',
        emptyStarColor: '#737373',
        starWidth: 25,
        starHeight: 25

      },
      editName:"editNameId",
      editNameHeader:"Izmena naziva ponude",
      editDesc:"editDescId",
      editDescHeader:"Izmena opisa ponude",
      editServHeader:"Izberite uslugu",
      editServ:"editServId",
      behaviorHeader:"Izaberite pravila ponašanja",
      editBeh:"editBehId",
      editRoomHeader:"Izmena broja soba",
      editRoom:"editRoomId",
      editPeopleHeader:"Izmena kapaciteta",
      editPeople:"editPeopleId",
      editPriceHeader:"Izmena cene (po noćenju)",
      editPriceIndex:"editPriceId",
      experienceReviewsHeader:"Recenzije gostiju",
      experienceReviewsIndex:"experienceReviewsId",
      editCCHeader:"Izmena uslova otkazivanja rezervacije",
      editCCIndex:"editCC",
      uploadHeader:"Upload slike",
      uploadIndex:"uploadIndex",
      addServices:"",
      fishEquipment:"",
      indexEq:"equipmentIndex",
      headerEq:"Izmena pecaroške opreme"

    }
  },
  methods: {
    onClick(i) {
      this.index = i;
    },
    editOfferName(name){

      if(name===""){
        swal.fire({title:'Nemoguće izmeniti! Ponuda mora imati naziv!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(name === this.ship.name){
        swal.fire({title:'Naziv je isti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else{
        this.ship.name=name;
        ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
          if(this.ship.name === response.data.name){
            swal.fire({title:'Uspešno izmenjen naziv!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
          else{
            swal.fire({title:'Naziv nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
        })
      }
    },
    editDescription(description) {
      if (description === this.ship.description) {
        swal.fire({title: 'Opis je isti!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else {
        this.ship.description = description;
        ShipService.updateShip(this.ship.id, this.ship).then((response)=>{
          if(this.ship.description === response.data.description){
            swal.fire({title:'Uspešno izmenjen opis!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
          else{
            swal.fire({title:'Opis nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
        })
      }
    },
    editServices(checked_services){
      this.ship.additionalServices = checked_services;
      this.services = checked_services;
      ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
        console.log(this.ship);
        console.log(this.ship.additionalServices);
        console.log(response.data);
        if(JSON.stringify(this.ship.additionalServices) == JSON.stringify(response.data.additionalServices)){
          swal.fire({title:'Uspešno izmenjene dodatne usluge!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Dodatne usluge nisu izmenjene!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editRules(checked_behavior){
      this.ship.behavioralRules = checked_behavior;
      this.behavior = checked_behavior;
      console.log(checked_behavior);
      ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
        if(JSON.stringify(this.ship.behavioralRules) == JSON.stringify(response.data.behavioralRules)){
          swal.fire({title:'Uspešno izmenjena pravila ponašanja!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Pravila ponašanja nisu izmenjena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editCapacity(number){

      this.ship.capacity = number;

     ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
       console.log(this.ship.capacity);
       console.log(response.data.capacity);
       if(this.ship.capacity == response.data.capacity){

         swal.fire({title:'Uspešno izmenjen kapacitet!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
       }
       else{
         swal.fire({title:'Kapacitet nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
       }
     })
    },
    editPrice(price){
      this.ship.price = price;
      console.log(this.ship);
      ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
        if(this.ship.price == response.data.price){
          swal.fire({title:'Uspešno izmenjena cena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Cena nije izmenjena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editCancelConditions(percents){
      this.ship.percents = percents;
      console.log(this.ship.percents);
      this.p1=percents[0];
      this.p2=percents[1];
      this.p3=percents[2];
      this.p4=percents[3];
      ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
        if(JSON.stringify(this.ship.percents) == JSON.stringify(response.data.percents)){
          swal.fire({title:'Uspešno izmenjeni uslovi otkazivanja rezervacije!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Uslovi otkazivanaja rezervacije nisu izmenjeni!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editEquipment(equipments){
      this.ship.fishingEquipment = equipments;
      ShipService.updateShip(this.ship.id,this.ship).then((response)=>{
        if(JSON.stringify(this.ship.fishingEquipment) == JSON.stringify(response.data.fishingEquipment)){
          swal.fire({title:'Uspešno izmenjena pecaroška oprema!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Pecaroška oprema nije izmenjena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })

    }
  }
}
</script>

<style scoped>
#images{
  border:1px solid black;
  width:650px;
  height:400px;
}


</style>