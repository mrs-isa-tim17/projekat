Vue.component('cottage-history-reservation', {
    props: ['option_link', 'image_path', 'text'],
    template: `
        <div>
		    <client-header></client-header>
            <div class="p-2" v-for="(item, index) in cottageHistory"  :key="item.name"
                                    v-if="index >= fromElement && index < untilElement">
                <history-element :cottage="item"> </history-element>
            </div>
        </div>
    `,
    methods : {

    },
    data() {
        return {
            cottageHistory:[
                {
                    startDate:"01.04.2022",
                    endDate:"8.04.2022",
                    price:2000,
                    quickReservation:true,
                    additionalServices: ["wifi", "parking"],
                    canceled: false,
                    images: ["./img/cottage/1/331714665.jpg", "./img/cottage/1/325185409.jpg",
                    "./img/cottage/1/331713246.jpg", "./img/cottage/1/330214770.jpg"],
                    name: "HOPPPPAA",
                    address: 
                    {
                        country: "Serbia",
                        city: "Novi Sad",
                        address: "Cirila i Metodija"
                    },
                    description: "Najbolja zabava"
                },{
                    startDate:"01.04.2022",
                    endDate:"8.04.2022",
                    price:2000,
                    quickReservation:false,
                    additionalServices: ["wifi", "parking"],
                    canceled: true,
                    images: ["./img/cottage/2/169294777.jpg", "./img/cottage/2/228908947.jpg",
                    "./img/cottage/2/242540483.jpg", "./img/cottage/2/169287671.jpg"],
                    name: "Naslov 2",
                    address: 
                    {
                        country: "Serbia",
                        city: "Novi Sad",
                        address: "Cirila i Metodija"
                    },
                    description: "Ovde nađeš šta ti treba"
                },{
                    startDate:"01.04.2022",
                    endDate:"8.04.2022",
                    price:2000,
                    quickReservation:false,
                    additionalServices: ["wifi", "parking"],
                    canceled: false,
                    images: ["./img/cottage/3/132308681.jpg", "./img/cottage/3/132308680.jpg",
                    "./img/cottage/3/132308695.jpg", "./img/cottage/3/132308676.jpg"],
                    name: "Glupi naslovi",
                    address: 
                    {
                        country: "Serbia",
                        city: "Novi Sad",
                        address: "Cirila i Metodija"
                    },
                    description: "Tu smo za vas"
                },{
                    startDate:"01.04.2022",
                    endDate:"8.04.2022",
                    price:2000,
                    quickReservation:true,
                    additionalServices: ["wifi", "parking"],
                    canceled: false,
                    images: ["./img/cottage/4/238068376.jpg", "./img/cottage/4/238309022.jpg",
                    "./img/cottage/4/308003572.jpg", "./img/cottage/4/308003917.jpg"],
                    name: "Nešto",
                    address: 
                    {
                        country: "Serbia",
                        city: "Novi Sad",
                        address: "Cirila i Metodija"
                    },
                    description: "Neki descr"
                },
            ],
            fromElement: 0,
            untilElement: 5
        }
    }
});

