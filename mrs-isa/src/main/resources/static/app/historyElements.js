Vue.component('history-element', {
    props: ['cottage'],
    template: `
        <div class="d-flex justify-content-center">
            <div class="card mb-3 " style="max-width: 800px;">
                <div class="row g-0" style="background-color: #e6fff2;">
                    <div class="col-md-4">
                        <img :src=cottage.images[0] class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8"  >
                        <div class="card-body">
                            <h5 class="card-title">{{cottage.name}}</h5>
							<h6 class="card-title">{{cottage.address.country}}, {{cottage.address.city}}, {{cottage.address.address}}</h6>
                            <p class="card-text">
                                <div class="row">
                                    <div class="col-5">
                                        {{cottage.description}}
                                    </div>
                                    <div class="col-4">
                                        Od: {{cottage.startDate}}
                                        <br>
                                        Do: {{cottage.endDate}}
                                        <br>
                                        Cena: {{cottage.price}}
                                        <br>
										<p v-if="cottage.canceled" class="text-danger">
                                            otkazano
                                        </p>
                                        <p v-if="cottage.quickReservation" class="text-success">
                                            na popustu
                                        </p>
                                    </div>
                                    <div class="col-2">
                                        <button type="button" class="btn btn-primary">Recenzija</button>
                                        <button type="button" class="btn btn-light">Žalba</button>
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

    },
    data() {
        return {
            
        }
    }
});

