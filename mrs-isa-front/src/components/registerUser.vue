<template>
  <div>
    <basic-header></basic-header>
  <section class="signup-view">
  <div>
      <div id="left">
        <NameField v-model="user.name" /><br>
        <LastNameField v-model="user.surname"/><br>
        <EmailField v-model="user.email" /><br>
        <PasswordField v-model="user.password" /><br>
        <PasswordAgainField v-model="user.password" /><br>
        <PhoneField v-model="user.phoneNumber" /><br>
        <i class="fa fa-users"></i>
        <select name="role" id="roles" @change="changeUserType">
          <option style="color:grey;" value="" disabled selected>Vrsta korisnika</option>
          <option value="1">Klijent</option>
          <option value="3">Vlasnik vikendice</option>
          <option value="4">Vlasnik broda</option>
          <option value="5">Instruktor</option>
        </select>

      </div>
      <div id="right" style="margin-left:700px; margin-bottom:280px;">
        <label> Izaberite Vašu adresu</label>
        <open-maps :lon="user.longitude" :lat="user.latitude" @coordinate-changed="updateCoordinats" style="width: 400px; height: 400px;"></open-maps>
      </div>
        <div class="footer">
      <button class="ui button red fluid big"
          @click="signUpButtonPressed"
          :disabled="isSignupButtonDisabled"
              style="float:right;width:200px;background-color: #31708E;margin-bottom: 60px;">
        Registruj se
      </button>
        </div>

  </div>
  </section>
  </div>
</template>

<script>
import { reactive } from "vue";



import useFormValidation from "@/validations/useFormValidation";
import useSubmitButton from "@/validations/useSubmitButton";

import NameField from "@/components/registration_components/NameField";
import EmailField from "@/components/registration_components/EmailField";
import PhoneField from "@/components/registration_components/PhoneField";
import PasswordField from "@/components/registration_components/PasswordField";
import LastNameField from "@/components/registration_components/LastNameField";
import PasswordAgainField from "@/components/registration_components/PasswordAgainField";
import basicHeader from "@/components/main_site/main_home_page/basicHeader";
import OpenMaps from "@/components/VueMaps";
import loginServce from "@/servieces/LoginServce";

export default {
  components: {
    OpenMaps,
    PasswordAgainField,
    NameField,
    EmailField,
    PhoneField,
    PasswordField,
    LastNameField,
    basicHeader
  },

  setup() {
    let user = reactive({
      name: "",
      email: "",
      phoneNumber: "",
      password: "",
      userRole: "",
      longitude: 0,
      latitude: 0
    });

    const { errors } = useFormValidation();
    const { isSignupButtonDisabled } = useSubmitButton(user, errors);

    const signUpButtonPressed = () => {
      console.log(user);
      loginServce.registration(user);
    };
    return { user, signUpButtonPressed, isSignupButtonDisabled };
  },
  methods:{
    updateCoordinats(lon, lat){
      this.user.longitude = lon;
      this.user.latitude = lat;
      console.log(lon, lat)
    },
    changeUserType(){
      this.user.userRole = document.getElementById("roles").value;
    }
  }
};
</script>

<style scoped>
.signup-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#left{
  border: 1px solid black;
  text-align:center;
  background-color:#687864;
  font-size: 20px;
  padding: 40px;
  width: 40%;
  height:70%;
  position: absolute;
  left: 30%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

#right{

}
.footer{
  bottom:50px;
  position:absolute;
  width:80%;
  left:60px;

}
#cancel{
  float:left;
  left:30px;
  width:150px;
  color:white;
  background-color:red;
}

p{
  font-size: 30px;
  font-weight: bold;
  color:white;
  top:0px;
}
select{
  width:300px;
  height:35px;
}
i{
  padding: 7px;
  background: #5085A5;
  color: white;
  min-width: 30px;
  text-align: center;
}

</style>
