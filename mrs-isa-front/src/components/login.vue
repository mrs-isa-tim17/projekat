<template>
  <div>
    <basic-header></basic-header>
<div id="login">
  <br>
    <div class="">
      <img src="../assets/icons/user.png">
      <br>
      <br>
    </div>

    <div class="container">

      <input type="text" placeholder="Unesite email" id="uname" required><br>
      <br>
      <input type="password" placeholder="Unesite lozinku" id="psw" required><br>
      <br>
      <button type="submit" @click="login">Prijavite se</button><br>

    </div>


</div>
  </div>
</template>
<script>
import loginServce from "@/servieces/LoginServce";
import basicHeader from "@/components/main_site/basicHeader";
export default {
  name: "loginUsers",
  components: {
    basicHeader
  },
  data() {

  },
  computed: {
  },
  created() {
    if (this.loggedIn){
      this.$router.push('client/home');
    }
  },
  methods: {
    login(){
      let emailForm = document.getElementById("uname").value;
      let passwordForm = document.getElementById("psw").value;
      var user = {
        username: emailForm,
        password: passwordForm
      }
      loginServce.login(user).then((response) => {
        console.log(response);
        if (response.roleID === 1){
          this.$router.push("/client/home");
        }
        else if (response.roleID === 2){
          this.$router.push("/admin/home");//admin
        }
        else if (response.roleID === 3){
          this.$router.push("/cottageOwner/home");
        }
        else if (response.roleID === 4){
          this.$router.push("/shipOwner/home");//ship owner
        }
        else if (response.roleID === 5){
          this.$router.push("/instructor/home");//fishing instructor
        }
      }
      )

    }
  },
};
</script>
<style scoped>
#login{
  text-align:center;
  border: 1px solid black;
  background-color:#687864;
  font-size: 20px;
  padding: 20px;
  width: 50%;
  height:70%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;

}
form {

}
label{


  text-align: center;
}
img{
  border-radius: 50%;
  width:10px;
  height:10px;
}
.imgcontainer{
  width:80px;
  height:80px;
  border:1px solid white;
  display: inline-block;
  border-radius: 50%;
}
/* Full-width inputs */
input[type=text], input[type=password] {
  width: 60%;
  padding: 12px 20px;
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
  width: 60%;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}
a{
  color:#31708E;
}
/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
  float: left;

}
img{
  width:90px;
  height:90px;
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
#footer{

  width:100%;
  height:15%;
  position: absolute;
  bottom: 0px;
  left:0px;
  padding-top: 10px;
  padding-left: 30px;
  padding-right: 30px;
  background-color: rgba(255,255,255,60%)
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
