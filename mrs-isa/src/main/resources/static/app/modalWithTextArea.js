Vue.component('modal-with-textarea', {
    props: ['buttonText', 'buttonColor', "header", "index"],
    template: `
    <div>
        <button type="button" @click="openModel" class="btn" :style="buttonColor" data-bs-toggle="modal" :data-bs-target=index>
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

                    </div>
                
                    <div class="modal-footer">
                        <p id="emptyErrorModalWithTextArea" style="visibility: hidden;"> Ne može da se pošalje prazano. </p>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
                        <button type="button" class="btn btn-primary" @click.prevent="sendValue">Pošalji</button>
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
        sendValue(){
			if (this.text == ""){
                document.getElementById("emptyErrorModalWithTextArea").style.color = "red";
                document.getElementById("emptyErrorModalWithTextArea").style.visibility = "visible";
                return;
			}

			this.$emit('input-text', this.text);
            $("#"+this.index+"").modal('hide');
		}
    },
    data() {
        return {
            text: ""
        }
    }
});

