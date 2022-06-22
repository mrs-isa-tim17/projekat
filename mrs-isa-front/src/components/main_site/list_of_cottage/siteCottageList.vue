<template>


  <div>
    <div class="p-2" v-for="(item) in cottages"  :key="item.name">
      <site-cottage-element :cottage="item"></site-cottage-element>
    </div>
  </div>


  <pagination-component :key="paginationKey" :numberOfElementsToDisplay="numberOfElementsForDisplay"
                        :fromElement="fromElement"
                        :numberOfElements="listLength" @pagination="fromUntilElement"
                        class="d-flex justify-content-center"></pagination-component>


</template>

<script>
import SiteCottageElement from "@/components/main_site/list_of_cottage/siteCottageElement";
import PaginationComponent from "@/components/paginationComponent";

export default {
  name: "site-cottage-list",
  components: {PaginationComponent, SiteCottageElement},
  props: ["cottages", "numberOfElementsForDisplay", "listLength", "fromElement"],
  mounted() {

  },
  methods: {
    fromUntilElement(from) {
      this.$emit("get-list-from", from);
    },
    forceRemounting() {
      this.paginationKey += 1;
    }
  },

  data() {
    return {
      paginationKey: 0,
    }
  }
}
</script>

<style scoped>

</style>
