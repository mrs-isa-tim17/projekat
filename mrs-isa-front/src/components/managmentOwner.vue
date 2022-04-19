<template>
  <div>
    <input type="radio" name="tabs" id="tab1" checked>
    <label for="tab1">Dodavanje</label>
    <input type="radio" name="tabs" id="tab2">
    <label for="tab2">Izmena</label>
    <input type="radio" name="tabs" id="tab3">
    <label for="tab3">Brisanje</label>
    <div class="tab content1">
      <div id="dataForm">
        <p>Unesite podatke za dodavanje nove ponude</p>
        <label>Naziv</label><br>
        <input class="name" type="text"><br>
        <label>Adresa</label><br>
        <input id="address" name="address" type="text"> <br>
        <label>Opis</label><br>
        <textarea id="description" name="description" rows="3" cols="20"></textarea><br>
        <label>Broj soba</label><br>
        <input id="roomsNumber" name="roomsNumber" type="text"><br>
        <label>Pravila ponašanja</label><br>
        <textarea id="behavior" name="behavior" rows="3" cols="20"></textarea><br>
        <label>Cena</label><br>
        <input id="price" name="price" type="text"><br>
        <br>
        <button id="addOffer">Dodaj</button>
      </div>

      <div id="tableOffers">
        <p>Sve Vaše ponude</p>
        <table class="offers">
          <tr bgcolor="lightgrey">
            <th>ID</th>
            <th>Naziv</th>
            <th>Adresa</th>
            <th>Opis</th>
            <th>Broj soba</th>
            <th>Slike</th>
            <th>Brze rezervacije</th>
            <th>Pravila ponašanja</th>
            <th>Cena</th>
          </tr>

          <tr>
            <td>1</td>
            <td>Lopatnica</td>
            <td>Bogutovačka 55</td>
            <td><button>Pogledaj</button></td>
            <td>2</td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td>4000</td>
          </tr>
        </table>
      </div>
    </div>

    <div class="tab content2">
      <div id="dataForm">
        <p> Izaberite ponudu koju želite da izmenite</p>
        <label>Naziv</label><br>
        <input id="name" name="name" type="text" v-model="selectedOffer.name" v-bind:disabled="mode=='BROWSE'" > <br>
        <label>Adresa</label><br>
        <input id="address" name="address" type="text"  v-model="selectedOffer.address" v-bind:disabled="mode=='BROWSE'" > <br>
        <label>Opis</label><br>
        <input id="description" name="description" type="text"  v-model="selectedOffer.description" v-bind:disabled="mode=='BROWSE'" > <br>
        <label>Broj soba</label><br>
        <input id="roomsNumber" name="roomsNumber" type="text" ><br>
        <label>Pravila ponašanja</label><br>
        <input id="behavior" name="behavior" type="text" ><br>
        <label>Cena</label><br>
        <input id="price" name="price" type="text" ><br>
        <br>
        <button  v-on:click="updateOffer(selectedOffer)" v-bind:disabled="mode=='BROWSE'" id="addOffer" >Izmeni</button>
      </div>
      <div id="tableOffers">
        <p>Sve Vaše ponude</p>
        <table class="offers">
          <tr bgcolor="lightgrey">
            <th>ID</th>
            <th>Naziv</th>
            <th>Adresa</th>
            <th>Opis</th>
            <th>Broj soba</th>
            <th>Slike</th>
            <th>Brze rezervacije</th>
            <th>Pravila ponašanja</th>
            <th>Cena</th>
          </tr>

          <tr v-for="o in allOffers" :key="o" v-on:click="selectOffer(o)" v-bind:class="{selected : selectedOffer.name===s.name}">
            <td>1</td>
            <td>pera</td>
            <td>misa</td>
            <td><button>Pogledaj</button></td>
            <td>{{o.roomQuantity}}</td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td>300</td>
          </tr>
        </table>
      </div>
    </div>
    <div class="tab content3">
      <div id="dataForm">
        <p>Izaberite ponudu koju želite da obrišete</p>
        <button id="removeOffer">Obriši</button>
      </div>

      <div id="tableOffers">
        <p>Sve Vaše ponude</p>
        <table class="offers">
          <tr bgcolor="lightgrey">
            <th>ID</th>
            <th>Naziv</th>
            <th>Adresa</th>
            <th>Opis</th>
            <th>Broj soba</th>
            <th>Slike</th>
            <th>Brze rezervacije</th>
            <th>Pravila ponašanja</th>
            <th>Cena</th>
          </tr>

          <tr>
            <td>1</td>
            <td>Lopatnica</td>
            <td>Bogutovačka 55</td>
            <td><button>Pogledaj</button></td>
            <td>2</td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td><button>Pogledaj</button></td>
            <td>4000</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "managment-owner",
  data() {
    return {
      allOffers: null

    }
  },
  mounted() {
    axios
        .get('/api/cottages/all')
        .then(response => {

          (this.allOffers = response.data);
          console.log(this.allOffers);
          for (var o in this.allOffers) {
            console.log(o.id);
          }
        })
  },
  methods: {
    selectOffer: function (offer) {
      if (this.mode == 'BROWSE') {
        this.selectedOffer = offer;
      }
    },
    updateOffer: function (offer){
      axios
          .post("api/cottages/update", offer)
      this.mode = 'BROWSE';
    }

  }
}

</script>

<style scoped>
body, html {
  height: 100%;
  margin: 0;
  font-family: Arial;
  background-color: #E8E8E8;
}

#tab1{display:none;}
#tab2{display:none;}
#tab3{display:none;}
input + label { display: inline-block }
#tab1 ~ .tab { display: none }
#tab2 ~ .tab { display: none }
#tab3 ~ .tab { display: none }
/* show contents only for selected tab */
#tab1:checked ~ .tab.content1,
#tab2:checked ~ .tab.content2,
#tab3:checked ~ .tab.content3 { display: block; }

input + label {             /* box with rounded corner */
  border: 1px solid #999;
  background: #EEE;
  padding: 4px 12px;
  border-radius: 4px 4px 0 0;
  position: relative;
  top: 1px;
  width: 33.33%;
}
input:checked + label {     /* white background for selected tab */
  background: #FFF;
  border-bottom: 1px solid transparent;
}
input ~ .tab {          /* grey line between tab and contents */
  border-top: 1px solid #999;
  padding: 12px;
}

/*add tab*/
#dataForm{
  text-align:center;
  border: 2px solid black;
  background-color:#E8E8E8;
  font-size: 17px;
  padding: 20px;
  width: 35%;
  height:85%;
  position: absolute;
  left: 75%;
  top: 50%;
  transform: translate(-35%, -50%);
  color: #5F9F9F;

}

#tableOffers{

  text-align:center;
  border: 2px solid black;
  background-color:#5F9F9F;
  font-size: 20px;
  padding: 20px;
  width: 60%;
  height:85%;
  position: absolute;
  left: 35%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: #E8E8E8;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid black;
  text-align: left;
  padding: 8px;
}

#addOffer{
  font-size:20px;
  color: white;
  background-color:#5F9F9F;
}


#description{
  height: 80px;
  width: 70%;
}

#behavior{
  height: 80px;
  width: 70%;
}

#dataForm input{
  width: 70%;
}

#dataForm label{
  font-weight: bold;
}



table button{
  font-size: 20px;

}

#removeOffer{
  margin-top: 180px;
  font-size:24px;
  background-color:#5F9F9F;
}

p{
  font-weight:bold;
  text-decoration: underline;
}
</style>