<template>
<cottageOwnerHeader></cottageOwnerHeader>
  <div class="container" >
    <div class="row mt-4" >
      <div class="col-sm-2 align-content-start">
        <br><br><br>
        <star-rating :rating="4" :starWidth="20" :star-style="config "></star-rating><br><!--vidi za double vrednosti-->
       <experience-reviews-modal :key="reviewKey" :header="experienceReviewsHeader" :index="experienceReviewsIndex" :cottage="this.cottage"></experience-reviews-modal><br><br>

      </div>
      <div class="col-sm-8 align-content-center " >
        <div >
        <p style="font-size:30px;display: inline-block">{{cottage.name}}   </p>
       <edit-offer-name-modal :index="editName" :header="editNameHeader" :name="cottage.name" @edit-name="editOfferName"></edit-offer-name-modal>
        </div>

      <div  id="images">
       <!-- <lightbox
            :images="images"
            title="Feed Gallery"
        ></lightbox>-->
        <upload-images-modal :header="uploadHeader" :index="uploadIndex"></upload-images-modal>
      </div>
      <div class="row">
        <div class="col-5 mt-3" style="text-align: left">
         <p style="font-size:20px;display: inline-block;">{{cottage.description}}</p>
         <edit-offer-description :header="editDescHeader" :index="editDesc" :description="cottage.description" @edit-description="editDescription"></edit-offer-description>
        </div>
        <div class="col-sm-2 mt-3">
          <bird-vue-map :index="cottage.id" :lon="cottage.longitude" :lat="cottage.latitude" style="width: 200px;height:150px;"></bird-vue-map>
        </div>
      </div>
        <br>
        <br>
        <div style="text-align: left;font-size: 20px;"><b>Dodatne usluge</b>
          <edit-offer-services-modal :servicesList="this.cottage.additionalServices" :header="editServHeader" :index="editServ" @edit-services="editServices"></edit-offer-services-modal>
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
        <div style="text-align: left;font-size: 20px;"><b>Raspoloživi datumi</b><br>
        Kalendar
        </div>
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
          <div class="mt-5 justify-content-center" style="border:2px solid">
        <p style="font-weight: bold;color:red;">Uslovi otkazivanja rezervacije</p>
        <edit-cancel-conditions-modal :key="ccKey" :header="editCCHeader" :index="editCCIndex" :offer="cottage" @edit-cancel-condition="editCancelConditions" ></edit-cancel-conditions-modal>
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
import StarRating from 'vue-dynamic-star-rating'

//import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
// import { Splide, SplideSlide } from '@splidejs/vue-splide';
import cottageOwnerHeader from "@/components/cottage_owner/cottageOwnerHeader";
import BirdVueMap from "@/components/main_site/birdVueMap";
import EditOfferNameModal from "@/components/cottage_owner/editOfferNameModal";
import swal from "sweetalert2";
import EditOfferDescription from "@/components/cottage_owner/editOfferDescriptionModal";
import EditOfferServicesModal from "@/components/cottage_owner/editOfferServicesModal";
import EditBehaviorRulesModal from "@/components/cottage_owner/editBehaviorRulesModal";
import EditNumberModal from "@/components/cottage_owner/editNumberModal";
import ExperienceReviewsModal from "@/components/cottage_owner/experienceReviewsModal";
import EditPriceModal from "@/components/cottage_owner/editPriceModal"
import EditCancelConditionsModal from "@/components/cottage_owner/editCancelConditionsModal";
import UploadImagesModal from "@/components/cottage_owner/uploadImagesModal";
//import lightbox from "vlightbox"
// import VueGallerySlideshow from 'vue-gallery-slideshow'
export default {
  name: "cottageDetail",
  components: {
    UploadImagesModal,
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
    StarRating,

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
          }
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
        })
      },
  data() {
    return {
      reviewKey: 0,
      ccKey:0,
      d1:"",d2:"",d3:"",d4:"",
      p1:"",p2:"",p3:"",p4:"",
      cottage: {
        id: "",
        name: "",
        longitude: 0,
        latitude: 0,
        description: "",
        behavioralRules: [],
        images: [],
        price: "",
        priceStartDate:"",
        bedQuantity: "",
        roomQuantity: "",
        additionalServices: [],
        days: [],
        percents:[],
        experienceReviews: [],
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
      behavior:["zabranjeno pušenje"],
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
        CottageService.updateCottage(this.cottage);
        swal.fire({title:'Uspešno izmenjeno ime!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      }

    },
    editDescription(description){
      this.cottage.description = description;
      CottageService.updateCottage(this.cottage);
      swal.fire({title:'Uspešno izmenjen opis!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
    },
    editServices(checked_services){
      this.cottage.additionalServices = checked_services;
      this.services = checked_services;
      CottageService.updateCottage(this.cottage);
    },
    editRules(checked_behavior){
      this.cottage.behavioralRules = checked_behavior;
      this.behavior = checked_behavior;
      CottageService.updateCottage(this.cottage);
    },
    editCapacity(number){
      this.cottage.bedQuantity = number;
      CottageService.updateCottage(this.cottage);
      swal.fire({title:'Uspešno promenjen kapacitet vikendice!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
    },
    editRooms(number){
      this.cottage.roomQuantity = number;
      CottageService.updateCottage(this.cottage);
      swal.fire({title:'Uspešno promenjen broj soba vikendice!',background:'white',color:'#687864',confirmButtonColor:'#687864'});

    },
    editPrice(price,startDate){
      this.cottage.price = price;
      console.log(startDate);
      this.cottage.priceStartDate = startDate;
      CottageService.updateCottage(this.cottage);
      swal.fire({title:'Uspešno promenjen cenovnik!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
    },
    editCancelConditions(percents){
      this.cottage.percents = percents;
      console.log(this.cottage.percents);
      this.p1=percents[0];
      this.p2=percents[1];
      this.p3=percents[2];
      this.p4=percents[3];
      CottageService.updateCottage(this.cottage);
      swal.fire({title:'Uspešno promenjeni uslovi otkazivanja rezervacije!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
    }
  }
}
</script>

<style scoped>
#images{
  border:1px solid black;
  width:600px;
  height:400px;
}


</style>