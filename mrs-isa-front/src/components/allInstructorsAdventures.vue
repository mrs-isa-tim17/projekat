<template>

  <div class="container">
    <instructor-header></instructor-header>
    <div class="row d-flex justify-content-center">
      <div class="col-8 d-flex justify-content-center">
        <div>
          <div class="input-group mb-3">
            <input type="text" class="form-control" aria-label="Text input with dropdown button">
            <button class="btn btn-primary me-md-2" type="button" aria-expanded="false">Search</button>
            <button class="btn btn-secondary  btn-sm  me-md-2" @click="goToAddAdventure"> Dodaj novu avanturu</button>
          </div>
          <div v-for="adventure  in adventures" :key="adventure">
            <simple-adventure :adventure="adventure" :path=getImage(adventure) :key="myKey" @rerender="forceRerendering(adventure)"></simple-adventure>
          </div>

          <nav aria-label="...">

            <nav aria-label="Page navigation example">
              <ul class="pagination d-flex justify-content-center">
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import instructorHeader from "@/components/insrtuctorHeader"
import simpleAdventure from "@/components/simpleAdventure";
import AdventureService from "@/services/AdventureService";

export default {
  name: "adventure-all",
  components: {
    instructorHeader,
    simpleAdventure,
  },

  mounted() {
    console.log("bar je usao u funkciju");

    AdventureService.getAllAdventures().then((response) => {
      this.adventures = response.data;
      console.log(this.adventures);
    })
  }
  ,
  methods: {
    forceRerendering(){
      this.myKey += 1;
    },
    goToAddAdventure() {
      this.$router.push('/adventure/add');
    },
    getImage(adventure){
      if (adventure.images.length === 0){
        return "logo.png";
      }else{
        return adventure.images[0];
      }

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
      },
      myKey:1
    }
  }
}


</script>

<style scoped>

</style>