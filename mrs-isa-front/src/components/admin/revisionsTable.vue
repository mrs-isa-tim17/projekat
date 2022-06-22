<template>
  <div class="d-flex justify-content-center">

    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Klijent</th>
        <th scope="col">Email klijenta</th>
        <th scope="col">Vlasnik</th>
        <th scope="col">Email vlasnika</th>
        <th scope="col">Ocena</th>
        <th scope="col">Ponuda</th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(revision, index) in revisions" :key="index">
        <th scope="row">{{index+1}}</th>
        <td>{{revision.clientName}} {{revision.clientSurname}}</td>
        <td> {{revision.clientEmail}}</td>
        <td>{{revision.ownerName}} {{revision.ownerSurname}}</td>
        <td> {{revision.ownerEmail}}</td>
        <td>{{revision.rate}} </td>
        <td>  {{getSerbianOfferType(revision.offerType)}}</td>

        <td> <revisionModal :index="generateModalId(revision.id)" :header="this.modalHeader"
                                             :revisionId="revision.id" :revisionText="revision.text"
                                             :btnId="generateButtonId(revision.id)" btn-text="Detalji"
                                             @revision-accept-text="AcceptRevision"
                                             @revision-reject-text="RejectRevision"></revisionModal> </td>
        <td><h6 :id="revision.id"></h6></td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import revisionModal from "@/components/admin/revisionModal";
import ReviewServce from "@/servieces/ReviewServce";

export default {
  name: "revisionsTable",
  props:['revisions'],
  components:{
    revisionModal,
  },
  methods:{
    generateModalId(id) {
      return "ex" + id;
    },
    generateButtonId(id) {
      return "experience" + id;
    },
    getSerbianOfferType(type){
      if (type==='ADVENTURE') {
        return 'avantura';
      }else if(type==='SHIP'){
        return 'brod';
      }else{
        return 'vikendica';
      }
    },
    AcceptRevision(id){
      ReviewServce.acceptReview(id).then((response)=>{
        this.answer = response.data;
        console.log('accept');
        console.log(this.answer);
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

    //  if(this.answer) {
        document.getElementById(id).innerText = "Prihvatili ste reviziju.";
        document.getElementById("experience" + id).style.visibility = "hidden";
     // }else{
      //  document.getElementById(id).innerText = "Došlo je do greške tokom prihvatanja revizije.";
      //}
    },

    RejectRevision(id){
      ReviewServce.rejectReview(id).then((response)=>{
        this.answer = response.data;
        console.log('reject');
        console.log(this.answer);
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

    //  if(this.answer) {
        document.getElementById(id).innerText = "Odbili ste reviziju.";
        document.getElementById("experience" + id).style.visibility = "hidden";
    //  }else{
      //  document.getElementById(id).innerText = "Došlo je do greške tokom odbijanja revizije.";
     // }
    }

  },

  data(){
    return{
      modalHeader: "Revizije",
      answer: false,

      revision : {
        id:"",
        clientName:"",
        clientSurname:"",
        clientEmail:"",
        ownerName:"",
        ownerSurname:"",
        ownerEmail:"",
        rate:"",
        offerType:"",
        text:""
      }
    }
  }
}
</script>

<style scoped>

</style>