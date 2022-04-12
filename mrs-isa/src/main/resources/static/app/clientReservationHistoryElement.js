Vue.component('history-element', {
    props: ['cottage'],
    template: `
        <div class="d-flex justify-content-center mw-90">
            <div class="card mb-3 mw-90" style="width: 80%;">

                <div class="row g-0" style="background-color: #e6fff2;">

                    <div class="col-md-4">
                          <img :src=cottage.images[0] class="img-fluid rounded-start" @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                    </div>

                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.name}}</h5>
							              <h6 class="card-title" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">{{cottage.address.country}}, {{cottage.address.city}}, {{cottage.address.address}}</h6>
                            
                            <p class="card-text">
                                <div class="row">
                                    <div class="col-5" @click="goToOffer" data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
                                        {{cottage.description}}
                                    </div>

                                    <div class="col-4">
                                        Od: {{cottage.startDate}}
                                        <br>
                                        Do: {{cottage.endDate}}
                                        <br>
                                        Cena: {{cottage.price}} din
                                        <br>
										                    <p v-if="cottage.canceled" class="text-danger">
                                            otkazano
                                        </p>
                                        <p v-if="cottage.quickReservation" class="text-success">
                                            na popustu
                                        </p>
                                    </div>

                                    <div class="col-3">

                                      <modal-with-textarea-and-rating :buttonText="firstButtonText" v-show="!cottage.reviewed && !cottage.canceled" 
                                          :buttonColor="firstButtonColor" :header="firstButtonHeader" @input-text-rating="sendReview"
                                          :index="firstButtonId"> 
                                      </modal-with-textarea-and-rating>

                                      <modal-with-textarea :buttonText="secondButtonText"  v-show="!cottage.canceled" 
                                          :buttonColor="secondButtonColor" :header="secondButtonHeader" @input-text="sendComplaint"
                                          :index="secondButtonId"> 
                                      </modal-with-textarea>
                                    
                                    </div>
                                </div>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,
    methods : {
          doNothing(){

          },
          goToOffer(){
            console.log('/'+this.cottage.id);
            this.$router.push('/'+this.cottage.id);
        },
        sendReview(value, rating){
          this.cottage.reviewed = true;
          //axios
          //review
          this.$emit('rerender');
        },
        sendComplaint(value){
          //axios
          //complaint
        }
    },
    data() {
        return {
          firstButtonText: "Komentariši",
          firstButtonColor: "background-color: #6699ff; width: 125px",
          firstButtonHeader: "Unosi tvoje mišljenje:",
          firstButtonId: this.cottage.name + "review",

          secondButtonText: "Podnesi žalbu",
          secondButtonColor: "background-color: #ccddff; width: 125px",
          secondButtonHeader: "Unosi tvoju žalbu",
          secondButtonId: this.cottage.name + "complaint",
        }
    }
});

