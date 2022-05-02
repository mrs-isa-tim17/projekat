<template>

  <div class="px-5">
    <homePageCarouselLeft :text="cottageTextBgColor" :offers="cottages" :carouselId="adventureId" :title="cottageTitle" :description="cottageDescription"></homePageCarouselLeft>

    <homePageCarouselRight :text="adventureTextBgColor" :offers="adventures" :title="adventureTitle" :description="adventureDescription"></homePageCarouselRight>

    <homePageCarouselLeft :text="shipTextBgColor" :offers="ships" :carousel-id="shipId" :title="shipTitle" :description="shipDescription"></homePageCarouselLeft>
  </div>
</template>

<script>
import homePageCarouselLeft from "@/components/homePageCarouselLeft";
import homePageCarouselRight from "@/components/homePageCarouselRight";

export default {
  name: "homePageOffers",
  components: {
    homePageCarouselLeft,
    homePageCarouselRight
  },
  data() {
    return {
      not_logged_in : false,
      client_logged_in: false,

      adventureTextBgColor: "background-color: #687864;",
      adventures:[
        {

          id: 1,
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          name: "HOPPPPAA",
          images: [require("@/assets/img/instructor/206076_pecanje_zalazak_sunca.jpg")],
          description:""
        },
        {

          id: 1,
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          name: "HOPPPPAA",
          images: [require("@/assets/img/instructor/images.jpg")],
          description:""
        },
        {

          id: 1,
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          name: "HOPPPPAA",
          images: [require("@/assets/img/instructor/17855129_192001737975970_3760474441259945910_o.jpg")],
          description:""
        },
        {

          id: 1,
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          name: "HOPPPPAA",
          images: [require("@/assets/img/instructor/906z513_PECANJE2310pog.jpg")],
          description:""
        }
      ],
      adventureId: "adventure",
      adventureTitle: "Najbolje adventure",
      adventureDescription: "Mnogo insktruktora, još više mogućnosti za pecanje, najbloji kvalitet,......",
      cottages: [
        {
          reviewed: false,
          id: 1,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:true,
          additionalServices: ["wifi", "parking"],
          canceled: false,
          images: [require("@/assets/img/cottage/1/331714665.jpg"), "./img/cottage/1/325185409.jpg",
            "./img/cottage/1/331713246.jpg", "./img/cottage/1/330214770.jpg"],
          name: "HOPPPPAA",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Najbolja zabava"
        },{
          reviewed: true,
          id: 2,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:false,
          additionalServices: ["wifi", "parking"],
          canceled: true,
          images: [require("@/assets/img/cottage/2/169294777.jpg"), "./img/cottage/2/228908947.jpg",
            "./img/cottage/2/242540483.jpg", "./img/cottage/2/169287671.jpg"],
          name: "Naslov 2",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Ovde nađeš šta ti treba"
        },{
          reviewed: true,
          id: 3,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:false,
          additionalServices: ["wifi", "parking"],
          canceled: false,
          images: [require("@/assets/img/cottage/3/132308681.jpg"), "./img/cottage/3/132308680.jpg",
            "./img/cottage/3/132308695.jpg", "./img/cottage/3/132308676.jpg"],
          name: "Glupi naslovi",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Tu smo za vas"
        },{
          reviewed: false,
          id: 4,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:true,
          additionalServices: ["wifi", "parking"],
          canceled: false,
          images: [require("@/assets/img/cottage/4/238068376.jpg"), "./img/cottage/4/238309022.jpg",
            "./img/cottage/4/308003572.jpg", "./img/cottage/4/308003917.jpg"],
          name: "Nešto",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Neki descr"
        },{
          reviewed: false,
          id: 5,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:true,
          additionalServices: ["wifi", "parking"],
          canceled: false,
          images: [require("@/assets/img/cottage/4/238068376.jpg"), "./img/cottage/4/238309022.jpg",
            "./img/cottage/4/308003572.jpg", "./img/cottage/4/308003917.jpg"],
          name: "Nešto",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Neki descr"
        },
      ],
      cottageTitle: "Najbolje vikendice",
      cottageDescription: "Mnogo vikendice, najbloji kvalitet,......",
      cottageId: "cottage",
      cottageTextBgColor: "background-color: #88BBD6;",
      ships: [
        {
          reviewed: false,
          id: 1,
          startDate:"09.05.2022",
          endDate:"11.05.2022",
          price:1500,
          quickReservation:true,
          additionalServices: ["prvi element", "drugi element"],
          canceled: false,
          images: [require("@/assets/img/ship/1/11.jpg"), "./img/ship/1/12.jpg",
            "./img/ship/1/13.jpg", "./img/ship/1/14.jpg"],
          name: "HOPPPPAA",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Najbolja zabava"
        },{
          reviewed: true,
          id: 2,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:false,
          additionalServices: ["wifi", "parking"],
          canceled: true,
          images: [require("@/assets/img/ship/2/21.jpg"), "./img/ship/2/22.jpg",
            "./img/ship/2/23.jpg", "./img/ship/2/24.jpg"],
          name: "Naslov 2",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Ovde nađeš šta ti treba"
        },{
          reviewed: true,
          id: 3,
          startDate:"01.04.2022",
          endDate:"8.04.2022",
          price:2000,
          quickReservation:false,
          additionalServices: ["wifi", "parking"],
          canceled: false,
          images: [require("@/assets/img/ship/3/31.jpg"), "./img/ship/3/32.jpg",
            "./img/ship/3/33.jpg", "./img/ship/3/34.jpg"],
          name: "Glupi naslovi",
          address:
              {
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija"
              },
          description: "Tu smo za vas"
        }
      ],
      shipId: "ship",
      shipTitle: "Najbolji brodovi",
      shipDescription: "Mnogo brodova, najbloji kvalitet,......",
      shipTextBgColor: "background-color: #31708E;",
    }
  }
}
</script>

<style scoped>

</style>
