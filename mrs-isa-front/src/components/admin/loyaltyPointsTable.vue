<template>
  <div class="d-flex justify-content-center">
    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Početni datum</th>
        <th scope="col">Krajnji datum</th>
        <th scope="col">Broj poena</th>
        <th scope="col">Korisnik</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(pointsDTO, index) in allPoints" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td> {{ getDateFormat(pointsDTO.startDate) }}</td>
        <td> {{ getDateFormat(pointsDTO.endDate) }}</td>
        <td>{{ pointsDTO.points }}</td>
        <td>{{ getUser(pointsDTO.type) }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "loyaltyPointsTable",
  props: ['allPoints'],

  methods: {
    getDateFormat(date) {
      if (date == null) {
        return ""
      } else {

        let d = date[2] + "." + date[1] + "." + date[0] + "."
        return d;
      }
    },

    getUser(type) {
      console.log(this.allPoints);
      if (type === 'CLIENT') {
        return 'klijent';
      } else {
        return 'vlasnik';
      }


    }

  },

  data() {
    return {
      pointsDTO: {
        id: "",
        startDate: "",
        endDate: "",
        points: "",
        type: ""
      }
    }
  }
}
</script>

<style scoped>

</style>