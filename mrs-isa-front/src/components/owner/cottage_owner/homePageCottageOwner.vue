<template>
  <ownerHeader></ownerHeader>
<div class="container p-3">
<cottage-list></cottage-list>
</div>
</template>

<script>
import ownerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import CottageList from "@/components/owner/cottage_owner/cottageList";
export default {
  name: "cottageOwnerHome",
  components:{CottageList, ownerHeader}
}
</script>

<style scoped>

</style>