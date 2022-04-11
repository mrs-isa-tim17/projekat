Vue.component('adventure-home', {
    template: `
        <div id="adventure-home-page">
            
            <instructor-header></instructor-header>

            <div class="container">

                <div class="row">
                    <div class="col-8">             
                        <h1>{{adventure.name}}</h1>
                        <h3 class="px-5">adresa: {{adventure.address.streetName}} {{adventure.address.serialNumber}}, {{adventure.address.place.name}}, {{adventure.address.place.country}}</h3>
                        <h3 class="px-5" fo> <em>{{adventure.description}} </em></h3>

                        <div class="px-5">
                            <img-option :image_paths=adventure.images ></img-option>
                        </div>  
                    
                        
                            <h4 class="px-5"> cena: {{adventure.priceList[0].price}} </h4>
                            <h4 class="px-5" > maksimalan broj osoba: {{adventure.capacity}} </h4>
                            <hr>
                        <h4 class="px-5"> Pravila ponašanja </h4>
                        <ul class="px-5">
                            <li class="px-10" v-for="rule in adventure.behavioralRule">{{rule}}</li>
                        </ul>
                        <hr>

                        <h4 class="px-5"> Pecaroška oprema </h4>
                        <ul class="px-5">
                            <li class="px-10" v-for="equipment in adventure.fishingEquipment">{{equipment.name}} ima {{equipment.quantity}} {{equipment.units}}</li>
                        </ul>
                        <hr>
                        <h4 class="px-5"> Uslovi otkaza </h4>
                        <ul class="px-5">
                            <li class="px-10" v-for="cancel in adventure.cancelCondition">Za otkazni rok od {{cancel.days}} dana pre početka događaja, naplaćuje se {{cancel.double}}% od ukupne cene rezervacije.</li>
                        </ul>
                        <hr>

                        <h4 class="px-5">Biografija instruktora</h4>
                        <p class="px-5">{{adventure.instructorBiography}}</p>
                        <hr>
                        <h4 class="px-5"> Iskustva avanturista </h4>
                        <div class="px-5" >
                            <div border-style="solid" v-for="review in adventure.experienceReview">
                                <em> <p class="px-10" >{{review.client.name}} {{review.client.surname}} - {{review.text}}</p> </em>
                                <br/>
                            </div>      
                        </div>
                        
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button class="btn btn-secondary me-md-2" type="button">Izmeni</button>
                        </div>

                    </div>

                    <div class="col">
                        <p>ovde kalendar</p>
                    </div>

                </div>
                
            </div>
        </div>
      `,
data() {
        return {
        adventure:{
            name : "Rafting Drinom",
            address: {streetName:"Gogoljeva", serialNumber:"22", place:{postNumber: 22240, name:"Bajina Bašta", country:"Srbija" }},
            description: 'Uzbudljvo putovanje za porodicu i prijatelje',

            images:[{path:"./img/Drina1.jpg"}, {path: "./img/Drina2.jpg"}, {path:"./img/Drina3.jpg"}, {path: "./img/Drina4.jpg"}],
            priceList: [{startDate:"start", endDate:"end", price:12000}],
            behavioralRule: ["dozvoljeno pecanje", "zabranjeno kupanje", "pet friendly"],
            cancelCondition : [{days:5, double:30},{days:30, double:5}],
            additionalServices :["prevoz do plaze", "dorucak u kampu", "baterijska lampa"],

            instructorBiography:"Avanturisticki duh, zastitom reke Drine se bavi od 2008. godine...",
            capacity:5,
            fishingEquipment:[{name:"stap", units: "komada", quantity:10}, {name:"crvi", units :"kg", quantity:"2"}],
            averageRaiting : 4.35,
            experienceReview:[{text:"odlicno   Pravila ponašanja dozvoljeno pecanje zabranjeno kupanjepet friendlyPecaroška opremastap ima 10 komadacrvi ima 2 kglovi otkaza otkažete u roku od 5 dana pre početka događaja, plaćate 30% od ukupne cene rezervacije.roku od 30 dana pre početka događaja, plaćate 5% od ukupne cene rezervacijeBiografija instruktora Avanturisticki duh, zastitom reke Drine se bavi od 2008. godine...", client:{name:"Milan", surname:"Milic"}}, {text:"onako", client:{name:"Jelena", surname:"Jankovic"}}, {text:"prelepo",client:{name:"Pera", surname:"Peric"}}]
            }
        }
    },
})

var base = new Vue({
    el: "#root",
    data: {

    }
})