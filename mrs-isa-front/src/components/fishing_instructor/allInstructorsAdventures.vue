<template>

  <div class="container p-3">
    <div class="row d-flex justify-content-center">
      <div class="col-8 d-flex justify-content-center">
        <div class="input-group mb-3 input-icons">
          <i class="fa fa-search icon"></i><input placeholder="Pretraži po imenu..." type="search"
                                                  class="input-field form-control"
                                                  aria-label="Text input with dropdown button" v-model="search"
                                                  @keyup="filtered">
        </div>

        <button class="btn-sm " @click="goToAddAdventure"><i class="fa fa-mountain"></i> Dodaj novu avanturu</button>
      </div>
    </div>
    <div class="p-2" v-for="adventure  in adventures" :key="adventure">
      <simple-adventure :adventure="adventure" :path=getImage(adventure) :key="myKey"
                        @rerender="catchDeleteEvent()"></simple-adventure>
    </div>

  </div>
</template>

<script>
//import instructorHeader from "@/components/insrtuctorHeader"
import simpleAdventure from "@/components/fishing_instructor/simpleAdventure";
import AdventureService from "@/services/AdventureService";


export default {
  name: "adventure-all",
  components: {
    //instructorHeader,
    simpleAdventure,
  },

  created() {
    //console.log("bar je usao u funkciju");

    /*  AdventureService.getAllAdventures().then((response) => {
        this.adventures = response.data;
        console.log(this.adventures);
      })*/
    this.filtered();
  }
  ,
  methods: {
    forceRerendering() {
      window.location.reload()
      this.myKey += 1;
    },

    catchDeleteEvent() {
      setTimeout(this.forceRerendering, 500);
    },

    goToAddAdventure() {
      this.$router.push('/adventure/add');
    },
    getImage(adventure) {
      if (adventure.images.length === 0) {
        return '/img/instructor/adventurer.png';
      } else {
        return adventure.images[0];
      }

    },
    filtered() {
      this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
      console.log(this.coID);
      AdventureService.getAdventuresByOwner(this.coID)
          .then((response) => {
            if (!this.search) {
              this.adventures = response.data;
              console.log(this.adventures);
            } else {
              console.log(response.data);
              this.adventures = response.data.filter(adventure =>
                  adventure.name.toLowerCase().includes(this.search.toLowerCase())
              );
              console.log(this.adventures);
            }
          })

    }
  },

  data() {
    return {
      adventures: [],
      adventure: {
        id: 2,
        name: "",
        streetName: "",
        serialNumber: "",
        country: "",
        description: "",
        city: "",
        behavioralRules: [],
        images: [],
        fishingEquipment: [],
        cancelConditions: [],
        price: "",
        capacity: "",
        instructorBiography: "",
        additionalEquipment: [],
        days: ['5', '10', '15', '20'],
        percentage: ['0', '0', '0', '0'],
        experienceReviews: [],
        priceListId:"",
        instructorId: ""
      },
      myKey: 1,
      search: ""
    }
  }
}


</script>

<style scoped>

.input-icons {
  width: 100%;
  margin-bottom: 10px;
  float: left;
}

.icon {
  padding: 10px;
  min-width: 40px;
}

input {
  margin-right: 20px;
}

button {
  width: 250px;
  height: 38px;
}
</style>
