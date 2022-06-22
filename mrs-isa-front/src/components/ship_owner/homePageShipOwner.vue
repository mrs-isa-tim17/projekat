<template>
  <shipOwnerHeader></shipOwnerHeader>
  <div class="container p-2">
    <ship-list></ship-list>
  </div>
</template>

<script>
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
import ShipList from "@/components/owner/ship_owner/shipList";
export default {
  name: "shipOwnerHome",
  components:{ShipList, shipOwnerHeader}
}
</script>

<style scoped>

</style>