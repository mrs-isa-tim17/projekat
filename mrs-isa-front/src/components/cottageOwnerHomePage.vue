<template>
  <div>
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
        <a href="/owner/managment">
          <img src="@/assets/icons/cottage.png">
          <p>Vaše ponude</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/availability.png">
          <p>Period dostupnosti</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/istorijaRezervacija.png">
          <p>Istorija rezervacija</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/sale.png">
          <p>Brze rezervacije</p>
        </a>
      </div>
      <div>
        <a href="">
          <img src="@/assets/icons/reportRes.png">
          <p>Izveštaji o rezervacijama</p>
        </a>
      </div>
    </div>

    <div class="fun-red">
      <div>
        <a href="">
          <img src="@/assets/icons/reserve.png">
          <p>Nova rezervacija</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/calendar.png">
          <p>Kalendar zauzetosti</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/reportBusiness.png">
          <p>Izveštaji o poslovanju</p>
        </a>
      </div>

      <div>
        <a href="">
          <img src="@/assets/icons/deleteAcc.png">
          <p>Brisanje naloga</p>
        </a>
      </div>

      <div>
        <a href="/owner/profile">
          <img src="@/assets/icons/profile.png">
          <p>Izmena profila</p>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "cottage-owner-home"
}
</script>

<style scoped>
body{
  background-color:lightblue; /*promeni boju*/
}

#fun img{
  width: 150px;
  height: 100px;
}

.fun-red{
  display: flex;
  margin: 20px;
  justify-content: space-between; /*razmak izmedju blokova*/
}

.fun-red > div {
  text-align:center;
  position:relative;
  width: 200px; /*postavili smo visinu i sirinu pa nam flex grow nije potreban*/
  height: 150px;
  border: 1px solid white;
  margin-top: 40px;
}


.fun-red > div  p{
  position: absolute;
  left:0px;
  right:0px;
  bottom:0px;
  padding:10px;
  background-color: rgba(0,0,0,40%);
  margin-bottom:0px;
  color: white;
  font-weight:bold;
  border-top: 1px solid white;
  height: 50px;
}

.fun-red button {
  text-align:center;

}

#logo{
  width: 100px;
  height: 80px;
}


#buttonLogout{
  text-align:right;
  width: 100px;
  height: 80px;
  border:0;
  margin-right: 50px;
}

#logout{
  width: 150px;
  height: 60px;
  font-size: 20px;


}

</style>