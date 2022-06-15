<template>

  <div class="container justify-content-center">
    <br>
    <br>
    <div class="col justify-content-center" align="center">
      <div class="mb-3 justify-content-center" align="center" style="max-width: 500px ">
        <label class="form-label">Trenutna lozinka</label>
        <input type="password" class="form-control" id="password" name="password" v-model="newAdmin.old_password"/>
      </div>
      <div class="mb-3 justify-content-center" align="center" style="max-width: 500px">
        <label class="form-label">Nova lozinka</label>
        <input type="password" class="form-control" id="password_new" name="password_new"
               v-model="newAdmin.new_password"/>
      </div>
      <div class="mb-3 justify-content-center" align="center" style="max-width: 500px">
        <label class="form-label">Nova lozinka ponovo</label>
        <input type="password" class="form-control" id="password_new_again" v-model="newPasswordAgain"/>
      </div>
      <div class="modal-footer d-block">
        <button type="submit" class="btn btn-warning " @click="changePassword">Potvrdi novu lozinku</button>
      </div>
    </div>
  </div>
</template>

<script>
import PasswordService from "@/servieces/PasswordService";
import swal from "sweetalert2";

export default {
  name: "adminChangePassword",

  methods: {
    checkPasswords() {
      if (this.newAdmin.new_password === this.newPasswordAgain) {
        return true;
      }
      return false;
    },

    changePassword() {
      if (this.checkPasswords()) {
        // poziv za back
        this.coID = JSON.parse(localStorage.user).id;
        PasswordService.matchPassword(this.newAdmin, this.coID)
            .then((response) => {
                  if (response.data) {
                    this.$router.push("/admin/home");  //admin
                  } else {
                    swal.fire({
                      title: "Neuspešno",
                      text: "Niste uspeli da promenite lozinku",
                      background: 'white',
                      color: 'black',
                      confirmButtonColor: '#FECDA6'
                    });
                  }
                }
            )
        //  console.log(new_password);
      }
    }

  },

  data() {
    return {
      newAdmin: {
        old_password: "",
        new_password: ""
      },
      newPasswordAgain: ""

    }
  }
}
</script>

<style scoped>

</style>