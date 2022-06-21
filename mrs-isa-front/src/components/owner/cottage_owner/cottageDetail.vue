<template>
<cottageOwnerHeader></cottageOwnerHeader>
  <div class="container" >
    <div class="row mt-4" >
      <div class="col-sm-3 align-content-start">
        <br><br><br>
        <star-rating :rating="rating" :round-start-rating="false" style="width: 15px;"></star-rating>
    <!--    <star-rating :rating="4" :starWidth="20" :star-style="config "></star-rating><br>    -->
       <experience-reviews-modal :key="reviewKey" :header="experienceReviewsHeader" :index="experienceReviewsIndex" :cottage="this.cottage"></experience-reviews-modal><br><br>

      </div>
      <div class="col-sm-7 align-content-center" >
        <div >
        <p style="font-size:30px;display: inline-block">{{cottage.name}}   </p>
       <edit-offer-name-modal :index="editName" :header="editNameHeader" :name="cottage.name" @edit-name="editOfferName"></edit-offer-name-modal>
        </div>

      <div style="text-align: left"><img :src="cottage.images[0]" /><br></div>
       <!--<offer-carousel :images="cottage.images" style="width:500px;"></offer-carousel>-->
       <!-- <upload-images-modal :header="uploadHeader" :index="uploadIndex"></upload-images-modal>-->

      <div class="row pt-2 mt-3">
        <div class="col-5" style="text-align: left">
         <p style="font-size:20px;display: inline-block;">{{cottage.description}}</p>
        </div>
          <div class="col-1">
         <edit-offer-description :header="editDescHeader" :index="editDesc" :description="cottage.description" @edit-description="editDescription"></edit-offer-description>
          </div>

        <div class="col-6">
          <bird-vue-map :index="cottage.id" :lon="cottage.longitude" :lat="cottage.latitude" style="width: 200px;height:150px;"></bird-vue-map>
        </div>
      </div>
        <br>
        <div style="text-align: left;font-size: 20px;text-decoration: underline;"><b>Dodatne usluge</b>
          <edit-offer-services-modal :servicesList="services" :header="editServHeader" :index="editServ" @edit-services="editServices"></edit-offer-services-modal>
          <br>
            <div class="" style="display: inline-block;" v-for="(s,i) in this.cottage.additionalServices" :key="i">
              <i
                  class=" mr-1 text-primary"
                  :class="icons[s]"
              ></i>&nbsp;<span class="text-muted">{{s}}&nbsp;&nbsp;&nbsp;</span>
            </div>
        </div><br>
        <div style="text-align: left;font-size: 20px;"><b>Kućni red</b>
          <edit-behavior-rules-modal :behaviorList="behavior" :header="behaviorHeader" :index="editBeh" @edit-rules="editRules"></edit-behavior-rules-modal>
          <br>
          <div class="" style="display: inline-block;" v-for="(s,i) in this.cottage.behavioralRules" :key="i">
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
        <div style="font-size: 36px;"><i class="fa fa-users"></i> {{cottage.bedQuantity}}
          <edit-number-modal :header="editPeopleHeader" :index="editPeople" :number="cottage.bedQuantity" @edit-number="editCapacity"></edit-number-modal>
        </div>
        <div style="font-size: 36px;"> {{cottage.roomQuantity}} sobe
        <edit-number-modal :header="editRoomHeader" :index="editRoom" :number="cottage.roomQuantity" @edit-number="editRooms" ></edit-number-modal>
        </div>


        <div style="font-size: 28px;"> {{cottage.price}} din/noć
        <edit-price-modal :header="editPriceHeader" :index="editPriceIndex" :price="cottage.price" @edit-price="editPrice" ></edit-price-modal>
        </div>
          <div class="row mt-5 justify-content-center" style="border:2px solid">

        <div class="col-8" style="font-weight: bold;color:red;">Uslovi otkazivanja rezervacije</div>
            <div class="col-4">
            <edit-cancel-conditions-modal :key="ccKey" :header="editCCHeader" :index="editCCIndex" :offer="cottage" @edit-cancel-condition="editCancelConditions" ></edit-cancel-conditions-modal></div>
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


import CottageService from "@/servieces/cottage_owner/CottageService";
import ReviewServce from "@/servieces/ReviewServce";

//import StarRating from 'vue-dynamic-star-rating'

//import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
// import { Splide, SplideSlide } from '@splidejs/vue-splide';
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import BirdVueMap from "@/components/main_site/birdVueMap";
import EditOfferNameModal from "@/components/owner/cottage_owner/editOfferNameModal";
import swal from "sweetalert2";
import EditOfferDescription from "@/components/owner/cottage_owner/editOfferDescriptionModal";
import EditOfferServicesModal from "@/components/owner/cottage_owner/editOfferServicesModal";
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
import AdditionalServicesService from "@/servieces/AdditionalServicesService";
import BehaviorRulesService from "@/servieces/BehaviorRulesService";

export default {
  name: "cottageDetail",
  components: {
    //OfferCarousel,
  //  UploadImagesModal,
    StarRating,
    EditCancelConditionsModal,
    ExperienceReviewsModal,
    EditNumberModal,
    EditBehaviorRulesModal,
    EditOfferServicesModal,
    EditOfferDescription,
    EditOfferNameModal,
    EditPriceModal,
    BirdVueMap,
    cottageOwnerHeader,

    //StarRating,

    //lightbox
    //FontAwesomeIcon
    // VueGallerySlideshow
  },
  created:
      function () {
        let id = this.$route.params.id
        CottageService.getCottage(id).then((response) => {
          this.cottage = response.data;
          this.reviewKey++;
          this.ccKey++;
          for (let i = 0; i < this.cottage.images.length; i++) {

            var img = "@/assets/" + this.cottage.images[i];

            this.imagesCottage.push(img);
          }})
          ReviewServce.getRating(id).then((response) =>
          {
            this.rating = response.data;
          })
          console.log(this.cottage);
          console.log(this.cottage.additionalServices);
          this.p1=this.cottage.percents[0];
          this.p2=this.cottage.percents[1];
          this.p3=this.cottage.percents[2];
          this.p4=this.cottage.percents[3];
          this.d1=this.cottage.days[0];
          this.d2=this.cottage.days[1];
          this.d3=this.cottage.days[2];
          this.d4=this.cottage.days[3];

        AdditionalServicesService.getAll().then((response)=>
            {
              this.services = response.data;
              console.log(this.addServices);
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

      cottage: {

        bedQuantity: "",
        roomQuantity: "",
        additionalServices: [],
        days: [],
        percents:[],
        experienceReviews: [],
        id: "",
        name: "",
        longitude: 0,
        latitude: 0,
        description: "",
        behavioralRules: [],
        images: [],
        price: "",
        priceListId:"",
        ownerId:""

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
      services:[],
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
      uploadIndex:"uploadIndex"

    }
  },
  methods: {
    onClick(i) {
      this.index = i;
    },
    editOfferName(name){

      if(name==""){
        swal.fire({title:'Nemoguće izmeniti! Ponuda mora imati naziv!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else if(name == this.cottage.name){
        swal.fire({title:'Naziv je isti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else{
        this.cottage.name=name;
        CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=> {
          if (this.cottage.name === response.data.name) {
            swal.fire({
              title: 'Uspešno izmenjen naziv!',
              background: 'white',
              color: '#687864',
              confirmButtonColor: '#687864'
            });
          } else {
            swal.fire({
              title: 'Naziv nije izmenjen!',
              background: 'white',
              color: '#687864',
              confirmButtonColor: '#687864'
            });
          }
        })
      }

    },
    editDescription(description){
      if (description === this.cottage.description) {
        swal.fire({title: 'Opis je isti!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else {
        this.cottage.description = description;
        CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{
          if(this.cottage.description === response.data.description){
            swal.fire({title:'Uspešno izmenjen opis!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
          else{
            swal.fire({title:'Opis nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          }
        })
      }
    },
    editServices(checked_services){
      this.cottage.additionalServices = checked_services;
      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{
        console.log(response.data);
        if(JSON.stringify(this.cottage.additionalServices) == JSON.stringify(response.data.additionalServices)){
          swal.fire({title:'Uspešno izmenjene dodatne usluge!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Dodatne usluge nisu izmenjene!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editRules(checked_behavior){
      this.cottage.behavioralRules = checked_behavior;
      console.log(checked_behavior);
      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{
        if(JSON.stringify(this.cottage.behavioralRules) == JSON.stringify(response.data.behavioralRules)){
          swal.fire({title:'Uspešno izmenjena pravila ponašanja!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Pravila ponašanja nisu izmenjena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editCapacity(number){
      this.cottage.bedQuantity = number;

      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{
      console.log(response.data);

        if(this.cottage.bedQuantity == response.data.bedQuantity){

          swal.fire({title:'Uspešno izmenjen kapacitet!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Kapacitet nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
    },
    editRooms(number){
      this.cottage.roomQuantity = number;
      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{

        if(this.cottage.roomQuantity == response.data.roomQuantity){

          swal.fire({title:'Uspešno izmenjen broj soba!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Broj soba nije izmenjen!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })

    },
    editPrice(price){
      this.cottage.price = price;

      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>{
        if(this.cottage.price == response.data.price){
          swal.fire({title:'Uspešno izmenjena cena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
        else{
          swal.fire({title:'Cena nije izmenjena!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })

    },
    editCancelConditions(percents){
      this.cottage.percents = percents;
      console.log(this.cottage.percents);
      this.p1=percents[0];
      this.p2=percents[1];
      this.p3=percents[2];
      this.p4=percents[3];
      CottageService.updateCottage(this.cottage,this.cottage.id).then((response)=>
      {if(JSON.stringify(this.cottage.percents) == JSON.stringify(response.data.percents)){
        swal.fire({title:'Uspešno izmenjeni uslovi otkazivanja rezervacije!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }
      else{
        swal.fire({title:'Uslovi otkazivanaja rezervacije nisu izmenjeni!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
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