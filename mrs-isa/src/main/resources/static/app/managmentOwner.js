Vue.component('owner-managment',
	{
		template:
		`<div>
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
						<input id="name" name="name" type="text"> <br>
						<label>Adresa</label><br>
						<input id="address" name="address" type="text"> <br>
						<label>Opis</label><br>
						<input id="description" name="description" type="text"> <br>
						<label>Broj soba</label><br>
						<input id="roomsNumber" name="roomsNumber" type="text"><br>
						<label>Pravila ponašanja</label><br>
						<input id="behavior" name="behavior" type="text"><br>
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
</div>`,
		data(){
			return{}
		},
	    methods:{}
	}

);