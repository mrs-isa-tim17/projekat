Vue.component('client-home', {
    template: `
        <div id="client-home-page">
			<client-header></client-header>
            <div class="container">
                <div class="alert alert-danger" role="alert" v-if="penalties == 3">
                    {{textThreePanelty}}
                </div>

                <div class="alert alert-warning  alert-dismissible fade show" role="alert" v-if="penalties == 2">
                    {{textTwoPanelty}}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <div class="alert alert-secondary  alert-dismissible fade show" role="alert" v-if="penalties == 1">
                    {{textOnePanelty}}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                
                <div class="row justify-content-center">
                    <client-option :image_path="cottageImgPath" :option_link="cottageLink" :text="cottageText"></client-option>
                    
                    <client-option :image_path="shipImgPath" :option_link="shipLink" :text="shipText"></client-option>

                    <client-option :image_path="instructorImgPath" :option_link="instructorLink" :text="instructorText"></client-option>
                </div>
                    
                <div class="row justify-content-center ">
                    <client-option :image_path="upcomingImgPath" :option_link="upcomingLink" :text="upcomingText"></client-option>
                    
                    <div class="col-3 px-5" >
                        <div class="btn-group">
                            <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                <div class="card-lg">
                                    <div class="card-body">
                                        <img :src=historyOfReservationImgPath height="150">
                                    </div>
                                    <div class="card-footer text-center text-primary">
                                        {{historyOfReservationText}}
                                    </div>
                                </div>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="#">Vikendice</a></li>
                                <li><a class="dropdown-item" href="#">Brodovi</a></li>
                                <li><a class="dropdown-item" href="#">Instruktori</a></li>
                            </ul>
                         </div>
                    </div>                    

                    <client-option :image_path="subscriptionImgPath" :option_link="subscriptionLink" :text="subscriptionText"></client-option>
                </div>

                <div class="row justify-content-center ">
                    <client-option :image_path="complaintImgPath" :option_link="complaintLink" :text="complaintText"></client-option>
                    
                    <client-option :image_path="deleteAccImgPath" :option_link="deleteAccLink" 
                                :text="deleteAccText"></client-option>

                    <client-option :image_path="profileImgPath" :option_link="profileLink" :text="profileText"></client-option>
                </div>
            </div>

        </div>
    `,
    data() {
        return {
        penalties: 3,
        textOnePanelty: "Ups, imate jedan penal.\nAko sakupite 3 panele, ne možete više da rezervišete u tom mesecu.",
        textTwoPanelty: "Budite oprezni imate 2 panele. \nAko dobijete treći panel, ne možete da koristite sajt do kraja meseca.",
        textThreePanelty: "Imate 3 panele, do kraja meseca ne možete da rezervišete entitete.",

        cottageImgPath : "./icons/wood-house-color.png",
        cottageText: 'Vikendice',
        cottageLink: '...',

        shipImgPath : "./icons/ship.png",
        shipText: 'Brodovi',
        shipLink: '...',

        instructorImgPath: "./icons/fishing.png",
        instructorText: 'Instrukture',
        instructorLink: '...',

        upcomingImgPath: "./icons/booking.png",
        upcomingLink: "...",
        upcomingText: "Zakazane rezervacije",

        historyOfReservationImgPath: "./icons/istorijaRezervacija.png",
        historyOfReservationLink: "...",
        historyOfReservationText: "Istorija rezervacija",

        subscriptionImgPath: "./icons/subscription.png",
        subscriptionLink: "...",
        subscriptionText: "Pretplaćeni entiteti",

        complaintImgPath: "./icons/angry.png",
        complaintLink: "...",
        complaintText: "Žalbe",

        deleteAccImgPath: "./icons/deleteAcc.png",
        deleteAccLink:"...",
        deleteAccText: "Brisanje naloga",

        profileImgPath: "./icons/profile.png",
        profileLink: "#/client/profile",
        profileText: "Profil"

        }
    }
});