Vue.component('client-home', {
    template: `
        <div id="client-home-page">


            <div class="container">
                
                <div class="row justify-content-md-center ">
                    <client-option :image_path="cottageImgPath" :option_link="cottageLink" :text="cottageText"></client-option>
                    
                    <client-option :image_path="shipImgPath" :option_link="shipLink" :text="shipText"></client-option>

                    <client-option :image_path="instructorImgPath" :option_link="instructorLink" :text="instructorText"></client-option>
                </div>
                    
                <div class="row justify-content-md-center ">
                    <client-option :image_path="upcomingImgPath" :option_link="upcomingLink" :text="upcomingText"></client-option>
                    
                    <client-option :image_path="historyOfReservationImgPath" :option_link="historyOfReservationLink" 
                                :text="historyOfReservationText"></client-option>

                    <client-option :image_path="subscriptionImgPath" :option_link="subscriptionLink" :text="subscriptionText"></client-option>
                </div>

                <div class="row justify-content-md-center ">
                    <client-option :image_path="complaintImgPath" :option_link="complaintLink" :text="complaintText"></client-option>
                    
                    <client-option :image_path="deleteAccImgPath" :option_link="deleteAccLink" 
                                :text="deleteAccText"></client-option>

                    <client-option :image_path="profileImgPath" :option_link="profileLink" :text="profileText"></client-option>
                </div>
            </div>

        </div>
    `,
    data() {
        return {cottageImgPath : "./img/wood-house-color.png",
        cottageText: 'Vikendice',
        cottageLink: '...',

        shipImgPath : "./img/ship.png",
        shipText: 'Brodovi',
        shipLink: '...',

        instructorImgPath: "./img/fishing.png",
        instructorText: 'Instrukture',
        instructorLink: '...',

        upcomingImgPath: "./img/booking.png",
        upcomingLink: "...",
        upcomingText: "Upcoming reservations",

        historyOfReservationImgPath: "./img/istorijaRezervacija.png",
        historyOfReservationLink: "...",
        historyOfReservationText: "Istorija rezervacija",

        subscriptionImgPath: "./img/subscription.png",
        subscriptionLink: "...",
        subscriptionText: "Subsribed",

        complaintImgPath: "./img/angry.png",
        complaintLink: "...",
        complaintText: "žalbe",

        deleteAccImgPath: "./img/deleteAcc.png",
        deleteAccLink:"...",
        deleteAccText: "Brisanje naloga",

        profileImgPath: "./img/profile.png",
        profileLink: "...",
        profileText: "Profil"

        }
    }
})

var base = new Vue({
    el: "#root",
    data: {

    }
})