<template>
<div id="login">
  <form  method="post">
    <div class="imgcontainer">

    </div>

    <div class="container">
      <label for="uname"><b>Email</b></label><br>
      <input type="text" placeholder="Unesite email" id="uname" required><br>

      <label for="psw"><b>Lozinka</b></label><br>
      <input type="password" placeholder="Unesite lozinku" id="psw" required><br>

      <button type="submit" @click="login">Prijavite se</button><br>
      <label>
        <input type="checkbox" checked="checked" id="remember"> Zapamti lozinku
      </label><br>
    </div>

    <div id="footer" >
      <button type="button" class="cancelbtn">Otkaži</button>
      <span class="psw"><a href="#">Zaboravili ste lozinku?</a></span>
    </div>
  </form>
</div>
</template>
<script>
import loginServce from "@/servieces/LoginServce";
export default {
  name: "loginUsers",
  components: {

  },
  data() {

  },
  computed: {
  },
  created() {

  },
  methods: {
    login(){
      let emailForm = document.getElementById("uname").value;
      let passwordForm = document.getElementById("psw").value;
      var user = {
        username: emailForm,
        password: passwordForm
      }
      loginServce.login(user)
          .then((response) => {
            console.log(response.data);
            var userToken = response.data;
            if (userToken.roleID === 1)
              this.$route.push = "/client/home";
            else if (userToken.roleID === 2)
              this.$route.push = "/client/home";//admin
            else if (userToken.roleID === 3)
              this.$route.push = "/cottage/home";
            else if (userToken.roleID === 4)
              this.$route.push = "/client/home";//ship owner
            else if (userToken.roleID === 5)
              this.$route.push = "/client/home";//fishing instructor
          })
          .catch(function (error) {
            console.log(error.toJSON());
            if (error.response) {
              // The request was made and the server responded with a status code
              // that falls out of the range of 2xx
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            } else if (error.request) {
              // The request was made but no response was received
              // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
              // http.ClientRequest in node.js
              console.log(error.request);
            } else {
              // Something happened in setting up the request that triggered an Error
              console.log('Error', error.message);
            }
            console.log(error.config);
          });
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
  height:80%;
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
