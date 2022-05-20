<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item">
        <button class="page-link" @click="makeFirst" aria-label="First">
          <span aria-hidden="true">&lt;&lt;</span>
        </button>
      </li>
      <li>
        <button class="page-link" @click="makePrevaius" aria-label="Previous">
          <span aria-hidden="true">&lt;</span>
        </button>
      </li>
      <li class="page-item"><p class="text-secondary pt-2 px-3" :key="textKey"> {{currentlyOn}}/{{numberOfElements}}</p></li>
      <li class="page-item">
        <button class="page-link" @click="makeNext" aria-label="Next">
          <span aria-hidden="true">&gt;</span>
        </button>
      </li>
      <li>
        <button class="page-link" @click="makeLast" aria-label="Last">
          <span aria-hidden="true">&gt;&gt;</span>
        </button>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "paginationComponent",
  props: ["numberOfElements", "fromElement", "numberOfElementsToDisplay"],
  mounted() {
    console.log(" num of elements:" + this.numberOfElements);
    this.currentlyOn = parseInt(this.fromElement) + parseInt(this.numberOfElementsToDisplay);
    if (this.currentlyOn > this.numberOfElements)
      this.currentlyOn = this.numberOfElements;
  },
  methods: {
    makeFirst(){
      this.$emit('pagination', 0);
    },
    makePrevaius(){
      let newFromElement = parseInt(this.fromElement) - parseInt(this.numberOfElementsToDisplay);
      if (newFromElement <= 0)
        newFromElement = 0
      this.$emit('pagination', newFromElement);
    },
    makeNext(){
      console.log(this.numberOfElements);
      let newFromElement = parseInt(this.fromElement) + parseInt(this.numberOfElementsToDisplay);
      if (newFromElement >= this.numberOfElements)
        newFromElement = this.fromElement;
      this.$emit('pagination', newFromElement);
    },
    makeLast(){
      let newFromElement = parseInt(this.numberOfElements) / parseInt(this.numberOfElementsToDisplay);
      newFromElement = parseInt(this.numberOfElements) - parseInt(newFromElement);// this.numberOfElements - this.fromElement;
      console.log(newFromElement);
      console.log(this.numberOfElements);
      this.$emit('pagination', newFromElement);
    }
  },
  data(){
    return{
      currentlyOn: 0
    }
  }
}
</script>

<style scoped>

</style>
