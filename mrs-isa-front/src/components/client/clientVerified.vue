<template>
  <div>
    <div id="verificationAlart" class="container alert text-black" role="alert">
      {{welcome_text}}
    </div>
    <home-page-client v-show="verifiedClient"></home-page-client>
    <home-page v-show="!verifiedClient"></home-page>
  </div>
</template>

<script>
//poruka da li uspešan
//poruka da verifikuje
//poruke!!!


import clientServce from "@/servieces/ClientServce";
import HomePageClient from "@/components/main_site/homePageClient";
import HomePage from "@/components/main_site/homePage";
export default {
  name: "clientVerified",
  components: {HomePage, HomePageClient},
  //components: { homePageClient, homePage},
  created:
      function () {
      let code = this.$route.params.code;
      clientServce.verify(code)
          .then((response) =>{
                  console.log(response);
                  if (JSON.parse(localStorage.user) == null){
                    this.welcome_text = "Nalog nije verifikovan";
                    this.verifiedClient = false;
                    document.getElementById("verificationAlart").style.backgroundColor = "#d9534f";
                  }else{
                    this.welcome_text = "Uspešno ste verifikovali vašu imejl adresu";
                    this.verifiedClient = true;
                    document.getElementById("verificationAlart").style.backgroundColor = "#5cb85c";
                  }

                });
  }
  ,
  data() {
    return {
      verifiedClient: false,
      welcome_text: "Uspešno ste verifikovali vašu imejl adresu"
    }
  }
}
</script>

<style scoped>

</style>
