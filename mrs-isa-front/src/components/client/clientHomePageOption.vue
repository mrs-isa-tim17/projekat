<template>
  <div class="col-3 px-5" style="min-width: 200px;">
    <a :href=option_link >
      <div class="card-lg" style="min-width: 200px;">
        <div class="card-body">
          <img :src=image_path height="150">
        </div>
        <div class="card-footer text-center">
          {{text}}
        </div>
      </div>
    </a>
  </div>
</template>

<script>

export default {
  name: "client-option",
  props: ['option_link', 'image_path', 'text']
}
</script>

<style scoped>

</style>
