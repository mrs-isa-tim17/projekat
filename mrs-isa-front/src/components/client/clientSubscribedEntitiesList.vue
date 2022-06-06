<template>
  <div class="container" >
    <div class="p-2" v-for="(item) in offers"  :key="item.name">
      <client-subscribed-entities-element :offer="item" @rerender="emitRerender"> </client-subscribed-entities-element>
    </div>
  </div>
</template>

<script>
import ClientSubscribedEntitiesElement from "@/components/client/clientSubscribedEntitiesElement";
export default {
  name: "clientSubscribedEntitiesList",
  components: {ClientSubscribedEntitiesElement},
  props: ["offers"]
}
</script>

<style scoped>

</style>
