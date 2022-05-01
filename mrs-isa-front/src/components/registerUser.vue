<template>
  <div>
  <p>Registracija novih korisnika</p>
  <section class="signup-view">

    <form class="ui form" @submit.prevent>
      <div id="left">
        <NameField v-model="user.name" /><br>
        <LastNameField v-model="user.surname"/><br>
        <EmailField v-model="user.email" /><br>
        <PasswordField v-model="user.password" /><br>
        <PasswordAgainField v-model="user.password" /><br>

      </div>
      <div id="right">
      <PhoneField v-model="user.phoneNumber" /><br>
      <AddressField v-model="user.address"/><br>
      <CityField v-model="user.city"/><br>
      <CountryField v-model="user.country"/><br>
        <i class="fa fa-users"></i>
        <select name="role" id="roles" v-model="user.role">
          <option style="color:grey;" value="" disabled selected>Vrsta korisnika</option>
          <option value="user">Klijent</option>
          <option value="cottage-owner">Vlasnik vikendice</option>
          <option value="ship-owner">Vlasnik broda</option>
          <option value="instructor">Instruktor</option>
        </select>
        <br>
        <br>
        <br>
      </div>
        <div class="footer">
      <button class="ui button red fluid big"
          @click="signUpButtonPressed"
          :disabled="isSignupButtonDisabled"
              style="float:right;width:200px;background-color: #5805A5;"
      >
        Registruj se
      </button>
          <button id="cancel">Otkaži</button>
        </div>

    </form>
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
import AddressField from "@/components/registration_components/AddressField";
import CityField from "@/components/registration_components/CityField";
import LastNameField from "@/components/registration_components/LastNameField";
import CountryField from "@/components/registration_components/CountryField";
import PasswordAgainField from "@/components/registration_components/PasswordAgainField";


export default {
  components: {
    PasswordAgainField,
    NameField,
    EmailField,
    PhoneField,
    PasswordField,
    AddressField,
    CityField,
    LastNameField,
    CountryField
  },

  setup() {
    let user = reactive({
      name: "",
      email: "",
      phone: "",
      password: "",
    });

    const { errors } = useFormValidation();
    const { isSignupButtonDisabled } = useSubmitButton(user, errors);

    const signUpButtonPressed = () => {
      console.log(user);
    };
    return { user, signUpButtonPressed, isSignupButtonDisabled };
  },
};
</script>

<style scoped>
.signup-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.form {
  border: 1px solid black;
  text-align:center;
  background-color:#687864;
  font-size: 20px;
  padding: 60px;
  width: 60%;
  height:75%;
  position: absolute;
  left: 50%;
  top: 55%;
  transform: translate(-50%, -50%);
  color: white;
}

#left{
  float:left;
}
#right{
  float:right;
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