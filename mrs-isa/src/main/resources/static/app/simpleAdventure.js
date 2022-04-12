Vue.component('simple-adventure', {
    props: ['adventure', 'path'],
    template: `
    <div class="card mb-3 d-flex justify-content-center" style="max-width: 800px;" >
        <div class="row g-0">
            <div class="col-md-4">
                <img :src=path class="img-fluid rounded-start" alt="...">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                <div class="row">
                    <div class="col">
                        <a href="./adventurePageFishingInstructor.html"><h5 class="card-title"> {{adventure.name}} </h5></a>
                        <p class="card-text"> {{adventure.address.place.name}}</p>
                        <p class="card-text"><small class="text-muted"> {{adventure.address.streetName}} {{adventure.address.serialNumber}} </small></p> 
                        <button class="btn btn-secondary  btn-sm  me-md-2"> Obriši </button>
                    </div>

                    <div class="col">
                        <p class="card-text"> {{adventure.description}}</p>
                    </div>
                </div> 

                </div> 
            </div>
        </div>
    </div>
    `,
    data() {
        return {
        }
    }
});