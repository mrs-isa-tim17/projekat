<template>

    <basic-header></basic-header>



      <div class="container mt-5">
        <p style="font-size: 30px;color:#31708E;font-weight: bold">Prijava</p>
        <!--<div class="">

          <img src="../assets/icons/user.png">
          <br>
          <br>
        </div>-->
        <i class="fa fa-envelope icon"></i>
        <input type="text" placeholder="Unesite email" id="uname" required><br>
        <br>
        <i class="fa fa-key icon"></i>
        <input type="password" placeholder="Unesite lozinku" id="psw" required><br>
        <br>
        <button type="submit" @click="login">Prijavite se</button>
        <br>

      </div>



</template>
<script>
import loginServce from "@/servieces/LoginServce";
import basicHeader from "@/components/main_site/main_home_page/basicHeader";
import swal from "sweetalert2";

export default {
  name: "loginUsers",
  components: {
    basicHeader
  },
  data() {

  },
  computed: {},
  created() {
    if (this.loggedIn) {
      this.$router.push('client/home');
    }
  },
  methods: {
    login() {
      let emailForm = document.getElementById("uname").value;
      let passwordForm = document.getElementById("psw").value;
      var user = {
        username: emailForm,
        password: passwordForm
      }
      if (user.username === "" || user.password === "") {
          swal.fire({
            title: "Neuspešna prijava",
            text: "Netačni korisničko ime ili lozinka",
            background: 'white',
            color: 'black',
            confirmButtonColor: '#FECDA6'
          });
      } else {

        loginServce.login(user).then((response) => {
          console.log("loooogin");
          console.log(response);
          if (response === "") {
            swal.fire({
              title: "Neuspešna prijava",
              text: "Netačni korisničko ime ili lozinka",
              background: 'white',
              color: 'black',
              confirmButtonColor: '#FECDA6'
            });
            return;
          }

          if (response.roleID === 1) {
            this.$router.push("/client/home");
          } else if (response.roleID === 2 && response.changePassword === false) {
            this.$router.push("/admin/home");//admin
          } else if (response.roleID === 2 && response.changePassword === true) {
            this.$router.push("/admin/change/password");     // promena lozinke
          } else if (response.roleID === 3) {
            this.$router.push("/cottageOwner/home");
          } else if (response.roleID === 4) {
            this.$router.push("/shipOwner/home");//ship owner
          } else if (response.roleID === 5) {
            this.$router.push("/instructor/home");//fishing instructor
          }
        }
        )}

    }
  },
};
</script>
<style scoped>
#login {
  text-align: center;
  border: 1px solid black;
  background-color: #687864;
  font-size: 20px;
  padding: 20px;
  width: 50%;
  height: 60%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;

}

form {

}

label {


  text-align: center;
}

img {
  border-radius: 50%;
  width: 10px;
  height: 10px;
}

.imgcontainer {
  width: 80px;
  height: 80px;
  border: 1px solid white;
  display: inline-block;
  border-radius: 50%;
}

/* Full-width inputs */
input[type=text], input[type=password] {
  width: 400pt;
  padding: 12px 10px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 400pt;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

a {
  color: #31708E;
}

/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
  float: left;

}

img {
  width: 90px;
  height: 90px;
}

/* Center the avatar image inside this container */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

/* Avatar image */
img.avatar {
  width: 40%;
  border-radius: 50%;
}

/* Add padding to containers */
.container {


}

i{
  padding:16px;
  background: #5085A5;
  color: white;
  width: 40px;
  text-align: center;

}

#footer {

  width: 100%;
  height: 15%;
  position: absolute;
  bottom: 0px;
  left: 0px;
  padding-top: 10px;
  padding-left: 30px;
  padding-right: 30px;
  background-color: rgba(255, 255, 255, 60%)
}

/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }

  .cancelbtn {
    width: 100%;
  }
}
</style>
