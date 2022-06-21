<template>
    <basic-header></basic-header>
  <div class="container">
  <div class="row mt-3">
    <p style="text-align: center;color:#31708E">Registracija korisnika</p>
      <div class="col-6">
        <NameField v-model="user.name" /><br>
        <LastNameField v-model="user.surname"/><br>
        <EmailField v-model="user.email" /><br>
        <PasswordField v-model="user.password" /><br>
        <PasswordAgainField id="passwordAgain" :password="user.password"/><br>
        <PhoneField v-model="user.phoneNumber" /><br>

        <i class="fa fa-users"></i>
        <select name="role" id="roles" @change="changeUserType" style="width:300px;height:30px;">
          <option style="color:grey;" value="" disabled selected hidden>Vrsta korisnika</option>
          <option value="1">Klijent</option>
          <option value="3" >Vlasnik vikendice</option>
          <option value="4">Vlasnik broda</option>
          <option value="5">Instruktor</option>
        </select><br><br>
        <textarea id="request" placeholder="Obrazloženje registracije"  style="width:300px;height:100px;" disabled></textarea>
</div>
      <div class="col-6" >
        <p style="text-align: left;font-size: 15px;color:#31708E;">Izaberite Vašu adresu</p>
        <open-maps :lon="user.longitude" :lat="user.latitude" @coordinate-changed="updateCoordinats" style="width: 400px; height: 400px;"></open-maps>

      <button class="ui button red fluid big"
          @click="signUpButtonPressed"
          :disabled="isSignupButtonDisabled"
              style="float:right;max-width:300px;background-color: #31708E;font-size:20px;color:whitesmoke;margin-bottom: 60px;">
        Registruj se
      </button>
        </div>
      </div>
  </div>

  <!--  <div> <div class="modal fade"  :id=index role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Modal title</h4>
          </div>
          <div class="modal-body">
            <p>One fine body&hellip;</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>-->


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
    basicHeader
  },

  setup() {
    let user = reactive({
      name: "",
      surname:"",
      email: "",
      phoneNumber: "",
      password: "",
      userRole: "",
      longitude: 0,
      latitude: 0,
      requestMessage:"klijent"
    });

    const { errors } = useFormValidation();
    const { isSignupButtonDisabled } = useSubmitButton(user, errors);

    const signUpButtonPressed = () => {

      if (user.userRole === "1") {
        user.requestMessage = "";
      } else {
        user.requestMessage = document.getElementById("request").value;
      }
      if (user.name == "") {
        swal.fire({title: 'Unesite ime!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else if (user.surname == "") {
        swal.fire({title: 'Unesite prezime!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else if (user.email == "") {
        swal.fire({title: 'Unesite email!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else if (user.phoneNumber == "") {
        swal.fire({
          title: 'Unesite broj telefona!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (user.password == "") {
        swal.fire({
          title: 'Unesite lozinku ponovo!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (document.getElementById("passwordAgain").value == "") {
        swal.fire({
          title: 'Unesite lozinku ponovo!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (user.longitude == "" && user.latitude == "") {
        swal.fire({title: 'Unesite adresu!', background: 'white', color: '#687864', confirmButtonColor: '#687864'});
      } else if (user.userRole != 1 && user.requestMessage == "") {
        swal.fire({
          title: 'Unesite obrazloženje!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (user.userRole == "") {
        swal.fire({
          title: 'Izaberite vrstu korisnika!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (user.password == document.getElementById("passwordAgain").value) {
        console.log(document.getElementById("passwordAgain").value);
        swal.fire({
          title: 'Lozinke se ne poklapaju!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else if (!Number.isInteger(parseInt(user.phoneNumber))) {
        swal.fire({
          title: 'Polje broj telefona treba da sadrži samo brojeve!',
          background: 'white',
          color: '#687864',
          confirmButtonColor: '#687864'
        });
      } else {

        loginServce.registration(user)
            .then((response) => {
              console.log("REGISTER");
              console.log(user.userRole);
              console.log(response.data);
              if (response.data.successfull) {
                if (user.userRole === "1") {
                  swal.fire({
                    title: "Uspešno",
                    text: "Uspešno ste se registrovali, na vašu imejl adresu smo poslali mejl, molimo Vas verifikujte adresu.",
                    background: 'white',
                    color: 'black',
                    confirmButtonColor: '#8DF172',
                    timer: 3000,
                  });
                } else {
                  swal.fire({
                    title: "Uspešno",
                    text: "Uspešno ste se registrovali, vaš zahtev je poslat adminu, molimo Vas sačekate da on odobri vaš zahtev.",
                    background: 'white',
                    color: 'black',
                    confirmButtonColor: '#8DF172',
                    timer: 3000,
                  });
                }
                //uspesno
              } else {
                swal.fire({
                  title: "Neuspešno",
                  text: response.data.text,
                  background: 'white',
                  color: 'black',
                  confirmButtonColor: '#FECDA6',
                  timer: 1500,
                });
              }
            })
            .catch(console.log("PROBLEM"));
      }
    };
    return { user, signUpButtonPressed, isSignupButtonDisabled };
  },
  data(){
    return{
      text:"",
      index:""
    }
  },
  methods:{
    updateCoordinats(lon, lat){
      this.user.longitude = lon;
      this.user.latitude = lat;
      console.log(lon, lat)
    },
    changeUserType(){
      this.user.userRole = document.getElementById("roles").value;
      if(this.user.userRole != '1'){
        console.log("nije");
        console.log(document.getElementById("request").disabled);
        document.getElementById("request").disabled = false;
        console.log(document.getElementById("request").disabled);
      }
      else{
        document.getElementById("request").disabled = true;
      }
    }
  }
};
</script>

<style scoped>


p{
  font-size: 30px;
  font-weight: bold;
  color:white;
  top:0px;
}

i{
  padding: 7px;
  background: #5085A5;
  color: white;
  min-width: 30px;
  text-align: center;
}


</style>
