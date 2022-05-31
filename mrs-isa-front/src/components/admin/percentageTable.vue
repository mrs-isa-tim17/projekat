<template>
  <div class="d-flex justify-content-center">
    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Početni datum</th>
        <th scope="col">Krajnji datum</th>
        <th scope="col">Procenat zarade</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(percentageDTO, index) in percentages" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td> {{ getDateFormat(percentageDTO.startDate) }}</td>
        <td> {{ getDateFormat(percentageDTO.endDate) }}</td>
        <td>{{ percentageDTO.percentage }}</td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
export default {
  name: "percentageTable",
  props: ['percentages'],

  methods: {
    getDateFormat(date) {
      if (date == null) {
        return ""
      } else {

        let d = date[2] + "." + date[1] + "." + date[0] + "."
        return d;
      }
    }

  },
  data() {
    return {
      percentageDTO: {
        id: "",
        startDate: "",
        endDate: "",
        percentage: ""
      },
    }
  }

}
</script>

<style scoped>

</style>