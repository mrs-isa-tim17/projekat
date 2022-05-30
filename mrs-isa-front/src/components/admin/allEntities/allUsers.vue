<template>
  <admin-header></admin-header>

  <div align="center">
    <button class="btn btn-primary  btn-sm  me-md-2 " @click="goToDeletedUsers"> Obrisani korisnici</button>
    <div v-for="user in this.users" :key="user">
      <simple-user :user=user @delete-user="Deleted"></simple-user>
    </div>
  </div>


</template>

<script>
import SimpleUser from "@/components/admin/simpleUser";
import UserService from "@/servieces/UserService";
import AdminHeader from "@/components/admin/adminHeader";

export default {
  name: "allUsers",
  components: {
    SimpleUser,
    AdminHeader,
  },

  created: function () {
    UserService.getAllUsers().then((response) => {
      this.users = response.data;
      console.log(this.users);
    }).catch(function (error) {
      console.log(error.toJSON());
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // error.request is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
      console.log(error.config);
    });
  },

  methods:{
    Deleted(){
      this.$router.go();
    },

    goToDeletedUsers(){
      this.$router.push('/admin/users/deleted');
    }


  },
  data() {
    return {
      users: [],
      user: {
        id:"",
        name: "",
        surname: "",
        phoneNumber: "",
        email: "",
        role: "",
        loyaltyPoints: "",
        userType: "",
        penalties: "",
        deleted:""
      },
    }
  }
}
</script>

<style scoped>

</style>