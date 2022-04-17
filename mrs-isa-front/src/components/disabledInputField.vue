<template>
  <div class="p-2">
    <label >{{label}}</label>
    <br>
    <input type="text" ref="input" :value=info size="25" disabled>
  </div>
</template>

<script>
export default {
  name: "disabledInputField",
  props: ["label", "info"],
}
</script>

<style scoped>

</style>