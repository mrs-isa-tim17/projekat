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
	                        <input-field :label="nameLabel" :info="client.name"> </input-field>
	                        <input-field :label="surnameLabel" :info="client.surname"> </input-field>
	                        <disabled-input-field :label="emailLabel" :info="client.email"> </disabled-input-field>
	                        <input-field :label="phoneNumberLabel" :info="client.phoneNumber"> </input-field>
                            
                            <div class="p-2">
                                <label >{{passwordLabel}}</label>
                                <br>
                                <input type="text" ref="input" :value=info size="25">
                            </div>
						</div>
                    </div>
                </div>
                    <div class="col-4 d-flex justify-content-center">
						<div>
	                        <input-field :label="countryLabel" :info="client.country"> </input-field>
	                        <input-field :label="cityLabel" :info="client.city"> </input-field>
	                        <input-field :label="addressLabel" :info="client.address"> </input-field>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <button type="button" class="btn" style="background-color:#3399FF;">Promeni</button>
						</div>
                    </div>

                </div>
            </div>
        </div>
    </div>
`,data() {
    return {
        numLoyaltyPointsLabel: "Broj lojaltih bodova",
        userCategoryLabel: "Kategorija",
        benefitsLabel: "Pogodnosti",
        numPenaltiesLabel: "Penale",
        nameLabel: "Ime",
        surnameLabel: "Prezime",
        emailLabel: "Email",
        phoneNumberLabel: "Broj telefona",
        passwordLabel: "Lozinka",
        countryLabel: "Država",
        cityLabel: "Grad",
        addressLabel: "Adresa",

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
}});