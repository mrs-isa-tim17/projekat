<template>
  <div>
    <div id="verificationAlart" class="container alert text-black" role="alert">
      {{welcome_text}}
    </div>
    <home-page-client :key="clientKey" v-if="verifiedClient"></home-page-client>
    <home-page :key="basicKey" v-if="!verifiedClient"></home-page>
  </div>
</template>

<script>
//poruka da li uspešan
//poruka da verifikuje
//poruke!!!


import clientServce from "@/servieces/ClientServce";
import HomePageClient from "@/components/main_site/main_home_page/homePageClient";
import HomePage from "@/components/main_site/main_home_page/homePage";
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
                  if (response == null){
                    this.welcome_text = "Nalog nije verifikovan";
                    this.verifiedClient = false;
                    this.clientKey++;
                    this.basicKey++;
                    document.getElementById("verificationAlart").style.backgroundColor = "#d9534f";
                  }else{
                    this.welcome_text = "Uspešno ste verifikovali vašu imejl adresu";
                    this.verifiedClient = true;
                    this.clientKey++;
                    this.basicKey++;
                    document.getElementById("verificationAlart").style.backgroundColor = "#5cb85c";
                  }

                });
  }
  ,
  data() {
    return {
      verifiedClient: false,
      welcome_text: "Uspešno ste verifikovali vašu imejl adresu",
      clientKey: 0,
      basicKey: 0
    }
  }
}
</script>

<style scoped>

</style>
