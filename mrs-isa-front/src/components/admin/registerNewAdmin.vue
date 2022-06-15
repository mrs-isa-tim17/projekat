<template>
  <div>
    <admin-header></admin-header>
    <section class="signup-view">
      <div>
        <div id="left">
          <NameField v-model="user.name"/>
          <br>
          <LastNameField v-model="user.surname"/>
          <br>
          <EmailField v-model="user.email"/>
          <br>
          <PasswordField v-model="user.password"/>
          <br>
          <PasswordAgainField v-model="user.password"/>
          <br>
          <PhoneField v-model="user.phoneNumber"/>
          <br>

        </div>
        <div id="right" style="margin-left:700px; margin-bottom:280px;">
          <label> Izaberite adresu</label>
          <open-maps :lon="user.longitude" :lat="user.latitude" @coordinate-changed="updateCoordinats"
                     style="width: 400px; height: 400px;"></open-maps>
        </div>
        <div class="footer">
          <button id="register" class="ui button red fluid big"
                  @click="signUpButtonPressed"
                  style="float:right;width:200px;background-color: #31708E;margin-bottom: 60px;">
            Registruj
          </button>
        </div>

      </div>
    </section>
  </div>
</template>

<script>
import {reactive} from "vue";


import useFormValidation from "@/validations/useFormValidation";
import useSubmitButton from "@/validations/useSubmitButton";

import NameField from "@/components/registration_components/NameField";
import EmailField from "@/components/registration_components/EmailField";
import PhoneField from "@/components/registration_components/PhoneField";
import PasswordField from "@/components/registration_components/PasswordField";
import LastNameField from "@/components/registration_components/LastNameField";
import PasswordAgainField from "@/components/registration_components/PasswordAgainField";
import OpenMaps from "@/components/VueMaps";
import loginServce from "@/servieces/LoginServce";
import adminHeader from "@/components/admin/adminHeader";
import swal from "sweetalert2";

export default {
  components: {
    OpenMaps,
    PasswordAgainField,
    NameField,
    EmailField,
    PhoneField,
    PasswordField,
    LastNameField,
    adminHeader
  },

  setup() {
    let user = reactive({
      name: "",
      email: "",
      phoneNumber: "",
      password: "",
      userRole: 2,
      longitude: 0,
      latitude: 0
    });

    const {errors} = useFormValidation();
    const {isSignupButtonDisabled} = useSubmitButton(user, errors);

    const signUpButtonPressed = () => {
      console.log("presssss");
      console.log(user);
      loginServce.registration(user).then((response) => {
        console.log("responseeee");
        console.log(response.data);
        if (response.data === null) {
          swal.fire({
            title: "Neuspešno",
            text: "Korisnik već postoji",
            background: 'white',
            color: 'black',
            confirmButtonColor: '#FECDA6'
          });
        } else {
          swal.fire({
            title: "Uspešno",
            text: "Registrovali ste novog administratora",
            background: 'white',
            color: 'black',
            confirmButtonColor: '#8DF172'
          });
          document.getElementById("register").style.visibility = "hidden";

        }
      });
    };
    return {user, signUpButtonPressed, isSignupButtonDisabled};
  },
  methods: {
    updateCoordinats(lon, lat) {
      this.user.longitude = lon;
      this.user.latitude = lat;
      console.log(lon, lat)
    },
    changeUserType() {
      this.user.userRole = document.getElementById("roles").value;
    }
  }
}
</script>

<style scoped>
.signup-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#left {
  border: 1px solid black;
  text-align: center;
  background-color: #687864;
  font-size: 20px;
  padding: 40px;
  width: 40%;
  height: 70%;
  position: absolute;
  left: 30%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

#right {

}

.footer {
  bottom: 50px;
  position: absolute;
  width: 80%;
  left: 60px;

}

#cancel {
  float: left;
  left: 30px;
  width: 150px;
  color: white;
  background-color: red;
}

p {
  font-size: 30px;
  font-weight: bold;
  color: white;
  top: 0px;
}

select {
  width: 300px;
  height: 35px;
}

i {
  padding: 7px;
  background: #5085A5;
  color: white;
  min-width: 30px;
  text-align: center;
}

</style>
