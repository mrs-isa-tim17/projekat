<template>
  <div class="card m-1" style="background-color: #FFFAF6">
    <div class="card-body">
      <div class="row">
        <div class="col">
          {{ review.clientName }}
        </div>
        <div class="col d-flex justify-content-end">
          <div v-for="i in review.rating" :key="i">
            <img style="width: 20px;height: 20px;" src="/Star_icon_stylized.svg.png">
          </div>
        </div>
      </div>

      <br>
      &emsp; {{review.text}}
    </div>
  </div>
</template>

<script>
export default {
  name: "clientComment",
  props: ["review"]
}
</script>

<style scoped>

</style>
