Vue.component('modal-with-textarea-and-rating', {
    props: ['buttonText', 'buttonColor', "header", "index"],
    template: `
    <div>
        <button type="button" class="btn" @click="openModel" :style="buttonColor" data-bs-toggle="modal" :data-bs-target=index>
            {{buttonText}}
        </button>
  
        <!-- Modal -->
        <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">{{header}}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                    
                        <textarea class="my-4" rows="5" cols="58" name="text" v-model="text">
                        
                        </textarea>

                        <div class="col-md-6">
                            <div class="box box-orange box-example-1to10">
                                <div class="box-body">
                                        <p > Ocena: </p>
                                        <select @change="changeRating" style="width: 70px" id="ratingId" name="rating" class="form-select" aria-label="Default select">
                                            <option selected>*</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select> 
                                </div>
                            </div>
                        </div>
                    </div>
                
                    <div class="modal-footer">
                        <p id="emptyErrorModalWithTextAreaAndRating" style="visibility: hidden;"> Ne može da se pošalje prazano. </p>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
                        <button type="button" class="btn btn-primary" @click.prevent="emitValue">Pošalji</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
    `,
    methods : {
        openModel(){
            $("#"+this.index+"").modal('show');
        },
        emitValue(){
			if (this.text == "" && this.rating=="*"){
                document.getElementById("emptyErrorModalWithTextAreaAndRating").style.color = "red";
                document.getElementById("emptyErrorModalWithTextAreaAndRating").style.visibility = "visible";
                return;
			}

			this.$emit('input-text-rating', this.text, this.rating);
            $("#"+this.index+"").modal('hide');
		},
        changeRating(e){
            this.rating = e.target.value;
        }
    },
    data() {
        return {
            text: "",
            rating: "*"
        }
    }
});

