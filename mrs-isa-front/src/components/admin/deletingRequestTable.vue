<template>
  <div class="d-flex justify-content-center">
    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Korisnik</th>
        <th scope="col">Uloga korisnika</th>
        <th scope="col">Tip korisnika</th>

        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(deleteRequest, index) in deleteRequests" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td>{{ deleteRequest.userName }} {{ deleteRequest.userSurname }}</td>
        <td> {{ returnStringRole(deleteRequest.role) }}</td>
        <td>{{ returnStringUserType(deleteRequest.userType) }}</td>
        <td>
          <delete-request-modal :index="generateModalId(deleteRequest.id)" :header="this.modalHeader"
                                :deleteRequestId="deleteRequest.id" :deleteRequestText="deleteRequest.text"
                                :btnId="generateButtonId(deleteRequest.id)" btn-text="Detalji"
                                @delete-accept-text="AcceptDeleteRequest"
                                @delete-reject-text="RejectDeleteRequest"></delete-request-modal>
        </td>
        <td><h6 :id="deleteRequest.id"></h6></td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import DeleteRequestModal from "@/components/admin/deleteRequestModal";
import DeleteRequestServce from "@/servieces/DeleteRequestServce";

export default {
  name: "deletingRequestTable",
  components: {DeleteRequestModal},
  props: ['deleteRequests'],

  methods: {

    returnStringUserType(type) {
      if (type === 'REGULAR')
        return 'Regular'
      else if (type === 'SILVER')
        return 'Silver'
      else
        return 'Gold';
    },

    returnStringRole(role) {
      if (role === 'ROLE_CLIENT')
        return 'Klijent'
      else if (role === 'ROLE_ADMIN')
        return 'Administrator'
      else if (role === 'ROLE_COTTAGE_OWNER')
        return 'Vlasnik vikendice'
      else if (role === 'ROLE_SHIP_OWNER')
        return 'Vlasnik broda'
      else
        return 'Instruktor pecanja'
    },

    generateModalId(id) {
      return "d" + id;
    },
    generateButtonId(id) {
      return "delete" + id;
    },
    AcceptDeleteRequest(id, text) {
      console.log(id, text);
      DeleteRequestServce.acceptDeleteRequest(id, text).then((response) => {
        this.answer = response.data;

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
      document.getElementById(id).innerText = "Uspešno ste prihvatili zahtev za brisanje naloga.";
      document.getElementById("delete"+id).style.visibility="hidden";


    },

    RejectDeleteRequest(id, text) {
      console.log(id, text);
      DeleteRequestServce.rejectDeleteRequest(id, text).then((response) => {
        this.answer = response.data;



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
      document.getElementById(id).innerText = "Uspešno ste odbili zahtev za brisanje naloga.";
      document.getElementById(id).style.color = "red";
      document.getElementById("delete"+id).style.visibility="hidden";

    }

  },

  data() {
    return {
      modalHeader: "Zahtev za brisanje naloga",
      deleteRequest: {
        id: "",
        userName: "",
        userSurname: "",
        text: "",
        userType: "",
        role: ""
      },
      answer: false

    }
  }
}
</script>

<style scoped>

</style>