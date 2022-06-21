<template>
  <button style="float:left;border:none;background:none;color:#31708E;font-weight: bold;"  @click="openForm" class="m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId>Pogledaj recenzije gostiju</button>
  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="scrollable modal-body" >
          <p id="noReview"></p>
          <div class=" p-2" v-for="(er,i) in allReviews"  :key="i">
         <div class="d-flex justify-content-center mw-90" >
            <div class="card mb-3 mw-90" style="width: 90%;">
              <div class=" row g-0" style="text-align:left; background-color: whitesmoke;color:#31708E;">
                  <p>Komentar: {{er.text}}</p>
                  <p>Ocena: {{er.rate}}</p>
                <p>Klijent: {{er.clientName}} {{er.clientSurname}}</p>
              </div>
              </div>
           </div>
            </div>


        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import ReviewServce from "@/servieces/ReviewServce";
export default {
  name: "experienceReviewsModal",
  props: ["cottage", "index", "header"],
  created(){
        console.log(this.index);
        console.log(this.cottage);
        this.offerId = this.cottage;
        this.modalId = "#" + this.index;
        console.log(this.offerId);
        ReviewServce.getReviews(this.offerId).then((response)=> {
              if (response.data.length == 0) {
                document.getElementById("noReview").innerText = "Trenutno nema recenzija gostiju!"
              } else {

                console.log(response.data);
                this.allReviews = response.data;
              }
            }
        )


      },
  methods: {
    openForm() {

      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    }
  },
  data(){
    return{
      offerId:"",
      allReviews:[]
    }
  }
}
</script>

<style scoped>
.scrollable{
  overflow-y: scroll;
}
</style>