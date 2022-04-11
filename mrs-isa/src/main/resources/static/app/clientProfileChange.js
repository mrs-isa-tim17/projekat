Vue.component('client-profile-change', {
	template: `
	<div>

		<client-header></client-header>
		<div class="row">
            <div class="col-4  d-flex justify-content-center" style="border-style: solid; border-width: medium; background-color: #778899;">
                <div>
                <disabled-input-field :label="numLoyaltyPointsLabel" :info="client.numLoyaltyPoints"> </disabled-input-field>
                <disabled-input-field :label="userCategoryLabel" :info="client.userCategory"> </disabled-input-field>
                <textarea disabled class="my-4" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti">
                    Pogodnosti &#13;&#10; {{client.benefits}}
                </textarea>
                <disabled-input-field :label="numPenaltiesLabel" :info="client.numPenalties"> </disabled-input-field>
                </div>
            </div>
            
            <div class="col-8 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
                <div class="row d-flex justify-content-center" style="">
                    <div class="col-4 d-flex justify-content-center">
						<div>
                        
                            <div class="p-2">
                                <label >{{nameLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.name" size="25" required>
                                <br>
                                <p id="nameEmpty" v-if="this.client.name == ''" style="color: red;"> Ime mora da postoji </p> 
                                <p v-if="this.client.name[0] == this.client.name[0].toLowerCase()" style="color: red;"> Ime mora da počinje velikom slovom </p> 
                                
                            </div>

                            <div class="p-2">
                                <label >{{surnameLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.surname" size="25" required>
                                <p v-if="this.client.surname == ''" style="color: red;"> Prezime mora da postoji</p> 
                                <p  v-if="this.client.surname[0] == this.client.surname[0].toLowerCase()" style="color: red;"> Prezime mora da počinje velikom slovom </p> 
                            </div>

                            
	                        <disabled-input-field :label="emailLabel" :info="client.email"> </disabled-input-field>

                            <div class="p-2">
                                <label >{{phoneNumberLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.phoneNumber" size="25" required>
                                <p v-if="this.client.surname == ''" style="color: red;"> Broj telefona mora da postoji</p> 
                            </div>
                            
                            <div class="p-2">
                                <label >{{passwordLabel}}</label>
                                <br>
                                <input type="password" ref="input" v-model="client.password" size="25">
                            </div>
						</div>
                    </div>
                </div>
                    <div class="col-4 d-flex justify-content-center">
						<div>
                            <div class="p-2">
                                <label >{{countryLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.country" size="25" required>
                                <p v-if="this.client.country == ''" style="color: red;"> Država mora da postoji</p> 
                                <p v-if="this.client.country[0] == this.client.country[0].toLowerCase()" style="color: red;"> Država mora da počinje velikom slovom </p> 
                            </div>

                            <div class="p-2">
                                <label >{{cityLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.city" size="25" required>
                                <p v-if="this.client.city == ''" style="color: red;"> Grad mora da postoji</p> 
                                <p v-if="this.client.city[0] == this.client.city[0].toLowerCase()" style="color: red;"> Grad mora da počinje velikom slovom </p> 
                            </div>

                            <div class="p-2">
                                <label >{{addressLabel}}</label>
                                <br>
                                <input type="text" ref="input" v-model="client.address" size="25" required>
                                <p v-if="this.client.address == ''" style="color: red;"> Adresa mora da postoji</p> 
                            </div>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <label id="emptyError" style="color: red; visibility: hidden;"> {{message}}</label>
                            <button type="button" class="btn" @click="cancel" style="background-color:#3399FF;">Odustani</button>
                            <button type="button" class="btn" @click="applyChanges" style="background-color:#3399FF;">Promeni</button>
						</div>
                    </div>

                </div>
            </div>
        </div>
    </div>
`, methods: {
		applyChanges(){
			if (this.client.name == "" || this.client.name[0] == this.client.name[0].toLowerCase()){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
            }
			else if (this.client.surname == "" || this.client.surname[0] == this.client.surname[0].toLowerCase()){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
			}
			else if (this.client.phoneNumber == ""){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
			}
            else if (this.client.country == "" || this.client.country[0] == this.client.country[0].toLowerCase()){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
			}
            else if (this.client.city == "" || this.client.city[0] == this.client.city[0].toLowerCase()){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
			}
            else if (this.client.address == ""){
                this.message = this.errorMessage;
                document.getElementById("emptyError").style.color = "red";
                document.getElementById("emptyError").style.visibility = "visible";
			}
			else{
				//axios
                this.message = this.successMessage;
                document.getElementById("emptyError").style.color = "green";
                document.getElementById("emptyError").style.visibility = "visible";
				this.backup[0] = this.client.name;
				this.backup[1] = this.client.surname;
				this.backup[2] = this.client.phoneNumber;
				this.backup[3] = this.client.password;
				this.backup[4] = this.client.country;
				this.backup[5] = this.client.city;
				this.backup[6] = this.client.address;
            }
                
		},cancel(){
			this.client.name = this.backup[0];
			this.client.surname = this.backup[1];
			this.client.phoneNumber = this.backup[2];
			this.client.password = this.backup[3];
			this.client.country = this.backup[4];
			this.client.city = this.backup[5];
			this.client.address = this.backup[6];
            document.getElementById("emptyError").style.visibility = "hidden";
		},
        
    }
    ,mounted() {
			this.backup = [this.client.name, this.client.surname, this.client.phoneNumber, this.client.password, this.client.country,
                    this.client.city, this.client.address];
	},
	data() {
        return {
            message: "Obavezne polje mora da postoje",
            errorMessage: "Obavezne polje mora da postoje",
            successMessage: "Uspešno sačuvano podaci",

            numLoyaltyPointsLabel: "Broj lojaltih bodova",
            userCategoryLabel: "Kategorija",
            benefitsLabel: "Pogodnosti",
            numPenaltiesLabel: "Penale",
            nameLabel: "Ime*",
            surnameLabel: "Prezime*",
            emailLabel: "Email*",
            phoneNumberLabel: "Broj telefona*",
            passwordLabel: "Lozinka",
            countryLabel: "Država*",
            cityLabel: "Grad*",
            addressLabel: "Adresa*",

            client: {
                numLoyaltyPoints: 20,
                userCategory: "REGULAR",
                benefits: "Kao svaki registrovani korisnik ima mogućnost rezervisanje ponuđenih entiteta",
                numPenalties: 1,
                name: "Milan",
                surname: "Milanovic",
                email: "milan.milanovic@example.com",
                phoneNumber: "+381256",
                password: "",
                country: "Serbia",
                city: "Novi Sad",
                address: "Cirila i Metodija 11"
            }

        }
    }
});