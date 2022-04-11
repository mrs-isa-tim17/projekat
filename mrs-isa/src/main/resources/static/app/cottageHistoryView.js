Vue.component('cottage-history-reservation', {
    props: ['option_link', 'image_path', 'text'],
    template: `
        <div>
            <client-header></client-header>

            <div class="row">
                <div class="col-8">
                </div>
                <div class="col-3 justify-content-right" >
                    <select id="sortBy" @change="sortList" class="form-select" aria-label="Default select">
                        <option selected>Sortiraj po</option>
                        <option value="1">Datum</option>
                        <option value="2">Trajanja</option>
                        <option value="3">Naziv</option>
                    </select>
                </div>
            </div>


            <div>
                <cottage-history-list :key="myKey" :numToDisplay="numberOfElementsForDisplay" :from="fromElement" :cottagesHistory="cottageReservationHistory"> </cottage-history-list>
            </div>

            <nav class="d-flex justify-content-center" aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                    <button class="page-link" @click="makePreviousLink" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </button>
                    </li>

                    <li class="page-item"><button class="page-link" @click="makeFirstLink">1</button></li>
                    <li class="page-item"><button class="page-link" disabled>...</button></li>
                    <li class="page-item"><button class="page-link" @click="makeMiddleLink">{{makeMiddleNumber()}}</button></li>
                    <li class="page-item"><button class="page-link" disabled>...</button></li>
                    <li class="page-item"><button class="page-link" @click="makeLastLink">{{makeLastNumber()}}</button></li>

                    <li class="page-item">
                    <button class="page-link" @click="makeNextLink" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </button>
                    </li>
                </ul>
            </nav>

        </div>
    `,
    created: function() {
          this.fromElement = 0;
    },
    methods : {
        forceRemounting(){
            this.myKey -= 1;
        },
        sortList(){
            //axios
            this.fromElement = 0;
            this.forceRemounting();
            console.log(document.getElementById("sortBy").value);
        },
        makeMiddleNumber(){
            let numPages = this.cottageReservationHistory.length / this.numberOfElementsForDisplay;
            return Math.round(numPages % 2);
        },
        makeLastNumber(){
            let numPages = this.cottageReservationHistory.length / this.numberOfElementsForDisplay;
            return Math.round(numPages);
        },
        makeFirstLink(){
            this.fromElement = 0;
            this.forceRemounting();
        },
        makeMiddleLink(){
            this.fromElement = (this.makeMiddleNumber()-1) * this.numberOfElementsForDisplay;
            this.forceRemounting();
        },
        makeLastLink(){
            this.fromElement = (this.makeLastNumber()-1) * this.numberOfElementsForDisplay;
            this.forceRemounting();
        },
        makePreviousLink(){
            previousFrom = parseInt(this.fromElement) - parseInt(this.numberOfElementsForDisplay);
            if (previousFrom < 0)
                return
            this.fromElement = previousFrom;
            this.forceRemounting();
        },
        makeNextLink(){
            nextFrom = parseInt(this.fromElement) + parseInt(this.numberOfElementsForDisplay);
            if (nextFrom > parseInt(this.cottageReservationHistory.length))
                return
          	this.fromElement = nextFrom;
            this.forceRemounting();
        },
        checkIfNeedsToBeDisplayed(index){
            untilElement = parseInt(this.numberOfElementsForDisplay) + parseInt(this.fromElement);
            console.log(index);
            console.log(this.fromElement);
            console.log(untilElement);
            if (index >= this.fromElement && index < untilElement){
                return true;
            }
            return false;
        }
    },
    data() {
        return {
            myKey: 0,
            cottageReservationHistory:[
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
            numberOfElementsForDisplay: 2
        }
    }
});
