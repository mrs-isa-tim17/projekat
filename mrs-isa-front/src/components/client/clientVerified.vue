<template>
  <div>
    <div v-if="verifiedClient" :key="verificationAlartKey" id="verificationAlart" style="background-color: #5cb85c;" class="container alert text-black" role="alert">
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
                  console.log("VERIFIKATION:");
                  console.log(response.id);
                  if (response.id === undefined){
                    this.welcome_text = "Nalog nije verifikovan";
                    this.verifiedClient = false;
                    this.clientKey++;
                    this.basicKey++;
                    this.verificationAlartKey++;
                    document.getElementById("verificationAlart").style.backgroundColor = "#d9534f";
                  }else{
                    this.welcome_text = "Uspešno ste verifikovali vašu imejl adresu";
                    this.verifiedClient = true;
                    document.getElementById("verificationAlart").style.backgroundColor = "#5cb85c";
                    this.clientKey++;
                    this.basicKey++;
                    this.verificationAlartKey++;
                  }

                });
  }
  ,
  data() {
    return {
      verifiedClient: false,
      welcome_text: "No text",
      clientKey: 0,
      basicKey: 0,
      verificationAlartKey: 0
    }
  }
}
</script>

<style scoped>

</style>
