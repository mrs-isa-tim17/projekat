<template>
<div id="login">
  <form  method="post">
    <div class="imgcontainer">

    </div>

    <div class="container">
      <label for="uname"><b>Email</b></label><br>
      <input type="text" placeholder="Unesite email" name="uname" required><br>

      <label for="psw"><b>Lozinka</b></label><br>
      <input type="password" placeholder="Unesite lozinku" name="psw" required><br>

      <button type="submit">Prijavite se</button><br>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Zapamti lozinku
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