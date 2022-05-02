Vue.component('owner-home-page',
	{
		template:
		`<div>
			<div class="fun-red">
				<div id="logo">
					logo
				</div>
				<div id="buttonLogout">
					<button id="logout" onclick="">Odjavite se</button>
				</div>
			</div>
			<div class="fun-red">
				<div>
					<a href="#/owner/managment">
						<img src="./icons/cottage.png">
						<p>Vaše ponude</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/availability.png">
						<p>Odredite period dostupnosti</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/list.png">
						<p>Istorija rezervacija</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/sale.png">
						<p>Brze rezervacije</p>
					</a>
				</div>
				<div>
					<a href="">
						<img src="./icons/reportRes.png">
						<p>Izveštaji o rezervacijama</p>
					</a>
				</div>
			</div>
			
			<div class="fun-red">
				<div>
					<a href="">
						<img src="./icons/reserve.png">
						<p>Nova rezervacija</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/calendar.png">
						<p>Kalendar zauzetosti</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/reportBusiness.png">
						<p>Izveštaji o poslovanju</p>
					</a>
				</div>
				
				<div>
					<a href="">
						<img src="./icons/deleteAcc.png">
						<p>Brisanje naloga</p>
					</a>
				</div>
				
				<div>
					<a href="#/owner/editprofile">
						<img src="./icons/profile.png">
						<p>Izmena profila</p>
					</a>
				</div>
				</div>
				</div>`,
		data(){
			return{}
		},
	    methods:{}
	}



);