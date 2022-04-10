Vue.component('client-option', {
    props: ['option_link', 'image_path', 'text'],
    template: `
                <div class="col=4 px-5" >
                    <a :href=option_link>
                        <div class="card-lg">
                            <div class="card-body">
                                <img :src=image_path height="150">
                            </div>
                            <div class="card-footer text-center">
                                {{text}}
                            </div>
                        </div>
                    </a>
                </div>
    `,
    methods : {
        //getImgUrl(imgName) {
        //    var images = require.context('./img/', false, /\.png$/)
        //    return images('./' + imgName + ".png")
        //  }
    },
    data() {
        return {
        }
    }
})

/*
var clientHomePage = new Vue({
    el: "#client-home-page",
    data: {
        cottageImgPath : "./img/wood-house-color.png",
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
})*/