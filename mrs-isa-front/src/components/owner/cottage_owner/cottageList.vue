<template>
  <div class="container" >

    <div class="row d-flex justify-content-center">
      <div class="col-8 d-flex justify-content-center">
    <div class="input-group mb-3 input-icons" >
      <i class="fa fa-search icon"></i><input placeholder="Pretraži po imenu..." type="search" class="input-field form-control" aria-label="Text input with dropdown button" v-model="search"  @keyup="filtered">
    </div>
        <button class="btn-sm" @click="addCottagePage" style="color: #31708E;border-color:#31708E;font-weight: bold;"><i class="fa fa-home" style="color: #31708E;"></i> Dodaj novu vikendicu</button>
      </div>
    </div>
    <p style="font-size: 30px">{{message}}</p>
    <div class="p-2" v-for="(c) in allCottages"  :key="c.id">
      <cottageElement  @rerender="forceRerendering" :cottage="c"></cottageElement>
    </div>
  </div>

</template>

<script>
import cottageElement from "@/components/owner/cottage_owner/cottageElement";
import CottageService from "@/servieces/cottage_owner/CottageService";

export default {
  name: "cottageList",
  components: {cottageElement},
  data() {

    return {
      allCottages: null,
      myKey: 1,
      addNewCottage: "/cottage/add",
      search:"",
      message:""
    }
  },

  created() {
  this.filtered();


  },
  mounted() {
    this.filtered();
  },


  methods: {
    addCottagePage() {
      this.$router.push('/cottage/add');
    },
    forceRerendering(){
      window.location.reload();
    },
    filtered(){
        this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
        console.log(this.coID);
        CottageService.getCottageByOwner(this.coID)
            .then((response) => {
              if(!this.search) {
                this.allCottages = response.data;
                if(this.allCottages.length==0){
                  this.message = "Nemate još uvek dodate vikendice";
                }
                console.log(this.allCottages);
              }
              else{
                console.log(response.data);
                this.allCottages = response.data.filter(cottage =>
                  cottage.name.toLowerCase().includes(this.search.toLowerCase())
                );
                console.log(this.allCottages);
              }
            })

      }
      }
    }
</script>

<style scoped>

.input-icons{
  width: 100%;
  margin-bottom: 10px;
  float:left;
}
.icon {
  padding: 10px;
  min-width: 40px;
}
input{
  margin-right:20px;
}
button{
  width:250px;
  height:38px;
}
</style>