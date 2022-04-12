Vue.component('owner-edit-profile',
	{
		template:
		`<div><div class="header">
			Moj profil
		
		</div>
	  
		  <!--  Loyality  -->
		  <div class="loyality">
			<div class="headerLoyality">Loyality program</div>
		    <div class="input-box">
				<span class="loyalityDetails">Broj poena</span><br>
				<input type="text" id="name" />
			</div>
			
			<div class="input-box">
				<span class="loyalityDetails">Kategorija</span><br>
				<input type="text" id="name" />
			</div>
			<br>
			<div id="benefits">
				<p>Pogodnosti</p>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				
			</div>
		  </div>
		  
		  <!--  Edit profile  -->
		  <div class="editProfile">
			
			<form action="">
				<div class="formdetails">
					<div class="input-box">
						<span class="details">Ime</span><br>
						<input type="text" id="name" />
					</div>
					<div class="input-box">
						<span class="details">Adresa</span><br>
						<input type="text" id="address" />
					</div>
					<div class="input-box">
						<span class="details">Prezime</span><br>
						<input type="text" id="surname" />
					</div>
					<div class="input-box">
						<span class="details">Grad</span><br>
						<input type="text" id="city" />
					</div>
					<div class="input-box">
						<span class="details">Email</span><br>
						<input type="text" id="email" />
					</div>
					<div class="input-box">
						<span class="details">DrĹľava</span><br>
						<input type="text" id="country" />
					</div>
					<div class="input-box">
						<span class="details">Lozinka</span><br>
						<input type="text" id="password" />
					</div>
					<div class="input-box">
						<span class="details">Broj telefona</span><br>
						<input type="text" id="telNumber" />
					</div>
					<div id="buttonSubmit">
						 <input type="submit" value="Potvrdi izmene" id="editButton">
					</div>
					
				</div>	
			</form>
		  </div>
		</div>`,
		data(){
			return{}
		},
	    methods:{}
	}



);