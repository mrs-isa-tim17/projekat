<template>
  <admin-header></admin-header>

  <div align="center">
    <h3>Izbrisani korisnici</h3><br>
    <div v-for="user in this.users" :key="user">
      <simple-user :user=user></simple-user>
    </div>
  </div>

</template>

<script>
import SimpleUser from "@/components/admin/simpleUser";
import AdminHeader from "@/components/admin/adminHeader";
import UserService from "@/servieces/UserService";

export default {
  name: "deletedUsers",
  components: {
    SimpleUser,
    AdminHeader,
  },
  created: function () {
      UserService.getDeletedUsers().then((response) => {
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
  data() {
    return {
      users: [],
      user: {
        id: "",
        name: "",
        surname: "",
        phoneNumber: "",
        email: "",
        role: "",
        loyaltyPoints: "",
        userType: "",
        penalties: "",
        deleted:""
      }
    }
  }
}
</script>

<style scoped>

</style>